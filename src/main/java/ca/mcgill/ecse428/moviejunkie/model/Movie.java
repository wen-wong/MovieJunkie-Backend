package ca.mcgill.ecse428.moviejunkie.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Movie {
    @Id
    private int id;
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "movie_hashtag",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name= "hashtag_text"))
    private Set<Hashtag> hashtags;

    public Movie(int id, String name, Set<Hashtag> hashtags){
        this.id = id;
        this.name = name;
        this.hashtags = hashtags;
    }
    public Movie(int id, String name){
        this.id = id;
        this.name = name;
        this.hashtags = new HashSet<Hashtag>();
    }
    public Movie() {}
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
    public Set<Hashtag> getHashtags() { return hashtags; }
    public void setHashtags(Set<Hashtag> hashtags) { this.hashtags = hashtags; }
    public void addHashtag(Hashtag hashtag) {
        this.hashtags.add(hashtag);
        hashtag.getMovies().add(this);
    }
    public void removeHashtag(String text) {
        Hashtag hashtag = null;
        for (Hashtag ht : this.hashtags) {
            if (text.equals(ht.getText())) {
                hashtag = ht;
            }
        }
        if (hashtag != null) {
            this.hashtags.remove(hashtag);
            hashtag.getMovies().remove(this);
        }
    }
}
