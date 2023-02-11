package ca.mcgill.ecse428.moviejunkie.controller;

import ca.mcgill.ecse428.moviejunkie.dto.MovieDTO;
import ca.mcgill.ecse428.moviejunkie.model.Movie;
import ca.mcgill.ecse428.moviejunkie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping(value={"/movie/{id}","/movie/{id}/"})
    @ResponseBody
    public MovieDTO createMovie(@PathVariable("id") int id, @RequestParam(value = "name", required = false) String name){
        Movie movie = movieService.createMovie(id, name);
        return convertToDTO(movie);
    }

    private MovieDTO convertToDTO(Movie movie){
        if (movie == null) {
            throw new IllegalArgumentException("Movie is null");
        }
        MovieDTO dto = new MovieDTO();
        dto.setId(movie.getId());
        dto.setName(movie.getName());
        return dto;
    }
}
