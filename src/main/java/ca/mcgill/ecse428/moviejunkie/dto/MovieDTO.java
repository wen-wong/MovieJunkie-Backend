package ca.mcgill.ecse428.moviejunkie.dto;

import ca.mcgill.ecse428.moviejunkie.model.Movie;

public class MovieDTO {
    public int id;
    private String name;

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

    public static MovieDTO convertToDTO(Movie movie){
        if (movie == null) {
            throw new IllegalArgumentException("Movie is null");
        }
        MovieDTO dto = new MovieDTO();
        dto.setId(movie.getId());
        dto.setName(movie.getName());
        return dto;
    }
}
