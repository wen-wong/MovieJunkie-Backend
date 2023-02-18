package ca.mcgill.ecse428.moviejunkie.model;

import jakarta.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
public class Movie {
    private int id;
    private String name;
    private List<Tag> tags;

    public Movie(int id, String name, List<Tag> tags){
        this.id = id;
        this.name = name;
        this.tags = tags;
    }

    public Movie(int id, String name){
        this.id = id;
        this.name = name;
        this.tags = Collections.<Tag>emptyList();
    }

    public Movie() {}

    @Id
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

    @ManyToMany
    public List<Tag> getTags() { return tags; }

    public void setTags(List<Tag> tags) { this.tags = tags; }
}
