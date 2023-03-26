package ca.mcgill.ecse428.moviejunkie.dto;

import ca.mcgill.ecse428.moviejunkie.model.Playlist;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class PlaylistDTO {
    public int id;
    public String title;
    public String description;
    public AccountDTO accountDTO;
    public List<MovieDTO> movieDTOList;

    public PlaylistDTO(int id, String title, String description, AccountDTO accountDTO, List<MovieDTO> movieDTOList) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.accountDTO = accountDTO;
        this.movieDTOList = movieDTOList;
    }
    public static PlaylistDTO convertToDTO(Playlist playlist) {
        AccountDTO accountDTO = new AccountDTO(playlist.getAccount());
        List<MovieDTO> movieDTOList = MovieDTO.convertToDTO(playlist.getMovies());
        PlaylistDTO playlistDTO = new PlaylistDTO(playlist.getId(), playlist.getTitle(), playlist.getDescription(), accountDTO, movieDTOList);
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
