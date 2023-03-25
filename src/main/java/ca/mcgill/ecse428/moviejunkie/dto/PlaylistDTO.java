package ca.mcgill.ecse428.moviejunkie.dto;

import ca.mcgill.ecse428.moviejunkie.model.Playlist;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlaylistDTO {
    public int id;
    public String title;
    public String description;
    public AccountDTO accountDTO;
    public Set<MovieDTO> movieDTOSet;

    public PlaylistDTO(int id, String title, String description, AccountDTO accountDTO, Set<MovieDTO> movieDTOSet) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.accountDTO = accountDTO;
        this.movieDTOSet = movieDTOSet;
    }
    public static PlaylistDTO convertToDTO(Playlist playlist) {
        AccountDTO accountDTO = new AccountDTO(playlist.getAccount());
        Set<MovieDTO> movieDTOSet = MovieDTO.convertToDTO(playlist.getMovies());
        PlaylistDTO playlistDTO = new PlaylistDTO(playlist.getId(), playlist.getTitle(), playlist.getDescription(), accountDTO, movieDTOSet);
        return playlistDTO;
    }
    public static Set<PlaylistDTO> convertToDTO(Set<Playlist> playlists) {
        Set<PlaylistDTO> playlistDTOSet = new HashSet<PlaylistDTO>();
        for (Playlist pl : playlists) {
            playlistDTOSet.add(convertToDTO(pl));
        }
        return playlistDTOSet;
    }
}
