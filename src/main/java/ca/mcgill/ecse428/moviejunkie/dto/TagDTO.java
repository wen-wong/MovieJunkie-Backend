package ca.mcgill.ecse428.moviejunkie.dto;
import java.util.List;

public class TagDTO {

    public String text;
    public List<MovieDTO> moviesList;

    public TagDTO() {}

    public TagDTO(String text) {
        this.text = text;
    }
    public TagDTO(String text, List<MovieDTO> movies) {
        this.text = text;
        this.moviesList = movies;
    }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public List<MovieDTO> getMoviesList() { return moviesList; }
    public void setMoviesList(List<MovieDTO> moviesList) {
        this.moviesList = moviesList;
    }
}