package ca.mcgill.ecse428.moviejunkie.service;

import ca.mcgill.ecse428.moviejunkie.model.Account;
import ca.mcgill.ecse428.moviejunkie.model.Movie;
import ca.mcgill.ecse428.moviejunkie.model.Playlist;
import ca.mcgill.ecse428.moviejunkie.repository.AccountRepository;
import ca.mcgill.ecse428.moviejunkie.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class PlaylistService {

    @Autowired
    PlaylistRepository playlistRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountService accountService;
    @Autowired
    MovieService movieService;

    private void ifAccountExists(String username) {
        Account account = accountService.getAccount(username);
        if (account == null) throw new IllegalArgumentException("Account not found");
    }
    //create
    //create playlist
    public Playlist createPlaylist(String username, String title, String description) {
        Account account = accountService.getAccount(username);
        Playlist playlist = new Playlist(account, title, description);
        account.addPlaylist(playlist);
        accountRepository.save(account);
        return playlist;
    }
    //add movie to playlist
    public Playlist addMovieToPlaylist(Playlist playlist, int movId) throws IllegalArgumentException{
        Movie movie = movieService.getMovie(movId);
        if (movie == null) throw new IllegalArgumentException("Movie not found");
        playlist.addMovie(movie);
        playlistRepository.save(playlist);
        return playlist;
    }
    //add list of movies to playlist
    public Playlist addMovieListToPlaylist(String username, int id, int[] movIdList) throws IllegalArgumentException{
        ifAccountExists(username);
        Playlist playlist = playlistRepository.findPlaylistById(id);
        for (int movId : movIdList) {
            addMovieToPlaylist(playlist, movId);
        }
        return playlist;
    }
    //read
    //read specific playlist
    public Playlist getPlaylist(int id) throws IllegalArgumentException{
        Playlist playlist = playlistRepository.findPlaylistById(id);
        if (playlist == null) throw new IllegalArgumentException("Playlist does not exist");
        return playlist;
    }
    //read all playlist by account
    public Set<Playlist> getAllPlaylistByAccount(String username) {
        Account account = accountService.getAccount(username);
        Set<Playlist> playlists = account.getPlaylists();
        return playlists;
    }
    //update
    //update title
    public Playlist updateTitle(String username, int id, String title) {
        ifAccountExists(username);
        Playlist playlist = getPlaylist(id);
        playlist.setTitle(title);
        playlistRepository.save(playlist);
        return playlist;
    }
    //update description
    public Playlist updateDescription(String username, int id, String description) {
        ifAccountExists(username);
        Playlist playlist = getPlaylist(id);
        playlist.setDescription(description);
        playlistRepository.save(playlist);
        return playlist;
    }
    //delete
    //remove movie from playlist
    public Playlist removeMovie(String username, int id, int movId) {
        ifAccountExists(username);
        Playlist playlist = getPlaylist(id);
        playlist.removeMovie(movId);
        playlistRepository.save(playlist);
        return playlist;
    }
    //delete playlist
    public void deletePlaylist(String username, int id) {
        ifAccountExists(username);
        Playlist playlist = getPlaylist(id);
        Account account = playlist.getAccount();
        account.removePlaylist(id);
        accountRepository.save(account);
        playlistRepository.delete(playlist);
    }


    //modify playlist order
    public Playlist reorderPlaylist (String username, int id, int movId, int offset) {
        ifAccountExists(username);
        Playlist playlist = getPlaylist(id);
        List<Movie> listPlaylist = playlist.getMovies();    //Get list of movies
        Movie tempMov = null;
        int movIndex = -1;
        int pSize = listPlaylist.size();

        for (int i = 0; i < pSize; i++) {               //iterate through movie list to find movie based on movID
            if (movId==listPlaylist.get(i).getId()) {
                tempMov = listPlaylist.get(i);          //Get movie and place in temporary variable
                movIndex = i;
                listPlaylist.remove(i);                 //Remove movie from playlist
            }
        }

        int newIndex = movIndex + offset;;      //Calculate new index of movie in list
        if (movIndex == -1){                    //If movIndex still = -1 then movie was not found in movie playlist
            throw new IllegalArgumentException("Movie does not exist in playlist");
        } else {
            if (offset < 0) {                   //Offset less than 0 means decrement position in playlist
                if(newIndex < 0){
                    listPlaylist.add(0,tempMov);    //Case where movie placed at beginning of list
                } else {
                    listPlaylist.add(newIndex, tempMov); //Case where movie is between beginning and previous index
                }
            } else if (offset > 0) {            //Offset larger than 0 means increment position in playlist
                if(newIndex >= pSize){
                    listPlaylist.add(tempMov);      //Case where movie is placed at end of list
                } else {
                    listPlaylist.add(newIndex, tempMov);    //Case where movie is placed between end and previous index
                }
            } else {
                listPlaylist.add(movIndex, tempMov);    //Otherwise index is the same (i.e. offset = 0)
            }
            playlist.setMovies(listPlaylist);
            playlistRepository.save(playlist);
        }
        return playlist;
    }

    public Playlist reorderPlaylist (String username, int id, int movId, boolean offset) {
        ifAccountExists(username);
        Playlist playlist = getPlaylist(id);
        List<Movie> listPlaylist = playlist.getMovies();    //Get list of movies

        int pSize = listPlaylist.size();

        for (int i = 0; i < pSize; i++) {               //iterate through movie list to find movie based on movID
            if (movId==listPlaylist.get(i).getId()) {
                if (offset){
                    Collections.swap(listPlaylist,i,i+1);   //case where offset = true (increment position by 1)
                } else {
                    Collections.swap(listPlaylist,i,i-1);   //case where offset = false (decrement position by 1)
                }

            }
        }
        playlist.setMovies(listPlaylist);
        playlistRepository.save(playlist);
        return playlist;
    }
}
