package ca.mcgill.ecse428.moviejunkie.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String description;
    @ManyToOne
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "account_username", insertable = false, updatable = false)
    private Account account;
    @ManyToMany
    private Set<Movie> movies;
    public Playlist(Account account, String title) {
        this.account = account;
        this.title = title;
        this.description = "";
        this.movies = new HashSet<>(Collections.EMPTY_SET);
    }
    public Playlist(Account account, String title, String description) {
        this.account = account;
        this.title = title;
        this.description = description;
        this.movies = new HashSet<>(Collections.EMPTY_SET);
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
    public void setMovies(Set<Movie> movies) {
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
