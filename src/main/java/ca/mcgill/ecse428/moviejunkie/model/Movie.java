package ca.mcgill.ecse428.moviejunkie.model;

import jakarta.persistence.*;

@Entity
public class Movie {
    private int id;
    private String name;

    public Movie(int id, String name){
        this.id = id;
        this.name = name;
    }

    public Movie() {

    }

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
}
