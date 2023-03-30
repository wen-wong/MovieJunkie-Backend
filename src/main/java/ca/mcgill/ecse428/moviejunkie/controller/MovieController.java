package ca.mcgill.ecse428.moviejunkie.controller;

import ca.mcgill.ecse428.moviejunkie.dto.MovieDTO;
import ca.mcgill.ecse428.moviejunkie.model.Movie;
import ca.mcgill.ecse428.moviejunkie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "*")
public class MovieController {
    @Autowired
    private MovieService movieService;

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping(value={"/movie/{id}", "/movie/{id}/"})
    public MovieDTO getMovie(@PathVariable("id") int id) {
        return MovieDTO.convertToDTO(movieService.getMovie(id));
    }

    @GetMapping(value={"/movie/tmdb/{id}", "movie/tmdb/{id}"})
    public String getTMDBMovie(@PathVariable("id") int id) {
        String url = "https://api.themoviedb.org/3/movie/" + id + "?api_key=f4a943efca00a3cd96ac56ff8ad1ea3c";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        System.out.println(response.getBody());
        String movieString = response.getBody();
        return movieString;
    }

    @PostMapping(value={"/movie/{id}","/movie/{id}/"})
    @ResponseBody
    public MovieDTO createMovie(@PathVariable("id") int id, @RequestParam(value = "name", required = false) String name){
        Movie movie = movieService.createMovie(id, name);
        return MovieDTO.convertToDTO(movie);
    }
}
