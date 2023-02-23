package ca.mcgill.ecse428.moviejunkie.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Hashtag {
    @Id
    private String text;
    @ManyToMany(mappedBy = "hashtags")
    private Set<Movie> movies;

    public Hashtag(String text, Set<Movie> movies){
        this.text = text;
        this.movies = movies;
    }
    public Hashtag(String text) {
        this.text = text;
        this.movies = new HashSet<Movie>();
    }
    public Hashtag() {}
    public String getText() {
        return text;
    }
    public void setText(String text) { this.text = text; }
    public Set<Movie> getMovies() { return movies; }
    public void setMovies(Set<Movie> movies) { this.movies = movies; }
}