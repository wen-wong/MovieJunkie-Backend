package ca.mcgill.ecse428.moviejunkie.dto;

import ca.mcgill.ecse428.moviejunkie.model.Movie;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class MovieDTO {
    public int id;
    private String name;
    private Set<HashtagDTO> hashtags;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Set<HashtagDTO> getHashtags() { return hashtags; }
    public void setHashtags(Set<HashtagDTO> hashtags) { this.hashtags = hashtags; }
    public static MovieDTO convertToDTO(Movie movie){
        if (movie == null) {
            throw new IllegalArgumentException("Movie is null");
        }
        MovieDTO dto = new MovieDTO();
        dto.setId(movie.getId());
        dto.setName(movie.getName());
        dto.setHashtags(HashtagDTO.convertToDTO(movie.getHashtags()));
        return dto;
    }
    public static Set<MovieDTO> convertToDTO(Set<Movie> movieList) {
        Set<MovieDTO> movieDTOList = new HashSet<MovieDTO>();
        for (Movie movie : movieList) {
            movieDTOList.add(convertToDTO(movie));
        }
        return movieDTOList;
    }
    public static List<MovieDTO> convertToDTO(List<Movie> movieList) {
        List<MovieDTO> movieDTOList = new LinkedList<>();
        for (Movie movie : movieList) {
            movieDTOList.add(convertToDTO(movie));
        }
        return movieDTOList;
    }
}
