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

    @GetMapping(value={"/movie/{id}", "/movie/{id}/"})
    public MovieDTO getMovie(@PathVariable("id") int id) {
        return MovieDTO.convertToDTO(movieService.getMovie(id));
    }
    @PostMapping(value={"/movie/{id}","/movie/{id}/"})
    @ResponseBody
    public MovieDTO createMovie(@PathVariable("id") int id, @RequestParam(value = "name", required = false) String name){
        Movie movie = movieService.createMovie(id, name);
        return MovieDTO.convertToDTO(movie);
    }
}
