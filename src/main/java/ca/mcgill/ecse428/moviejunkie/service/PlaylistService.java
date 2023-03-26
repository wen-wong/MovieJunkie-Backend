package ca.mcgill.ecse428.moviejunkie.service;

import ca.mcgill.ecse428.moviejunkie.model.Account;
import ca.mcgill.ecse428.moviejunkie.model.Movie;
import ca.mcgill.ecse428.moviejunkie.model.Playlist;
import ca.mcgill.ecse428.moviejunkie.repository.AccountRepository;
import ca.mcgill.ecse428.moviejunkie.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
