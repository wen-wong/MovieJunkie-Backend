package ca.mcgill.ecse428.moviejunkie.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.Collections;
import java.util.List;

@Entity
public class Tag {
    @Id
    private String text;
    private List<Movie> movies;

    public Tag(String text, List<Movie> movies){
        this.text = text;
        this.movies = movies;
    }
    public Tag(String text) {
        this.text = text;
        this.movies = Collections.<Movie>emptyList();
    }
    public Tag() {}
    public String getText() {
        return text;
    }
    public void setText(String text) { this.text = text; }
    @ManyToMany
    public List<Movie> getMovies() { return movies; }
    public void setMovies(List<Movie> movies) { this.movies = movies; }
}