package ca.mcgill.ecse428.moviejunkie.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String description;
    @ManyToOne
    private Account account;
    @ManyToMany
    private Set<Movie> movies;
    public Playlist(String title) {
        this.title = title;
    }
    public Playlist(String title, String description) {
        this.title = title;
        this.description = description;
    }
    public Playlist(){}
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
    public Set<Movie> getMovies() {
        return movies;
    }
    public void setMovies() {
        this.movies = movies;
    }
    public void addMovie(Movie movie) {
        movies.add(movie);
    }
    public void removeMovie(int movieId) {
        Movie movie = null;
        for (Movie mov : this.movies) {
            if (movieId == mov.getId()) {
                movie = mov;
            }
        }
        if (movie != null) {
            this.movies.remove(movie);
        }
    }
}
