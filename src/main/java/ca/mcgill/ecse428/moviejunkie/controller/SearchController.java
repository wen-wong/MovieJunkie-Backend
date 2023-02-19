package ca.mcgill.ecse428.moviejunkie.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "*")
public class SearchController {

    private final String TMDB_URL = "https://api.themoviedb.org/3/";
    private final String API_KEY = "?api_key=f4a943efca00a3cd96ac56ff8ad1ea3c";

    @GetMapping(value={"/discover/{id}", "/discover/{id}/"})
    public String searchDiscoverMovies(@PathVariable("id") int id) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String uri = TMDB_URL + "discover/movie" + API_KEY + "&page=" + id;
            return restTemplate.getForObject(uri, String.class);
        } catch (Error error) {
            return error.getMessage();
        }
    }

    @GetMapping(value="/search")
    public String searchQueryMovies(@RequestParam String query) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String uri = TMDB_URL + "search/movie" + API_KEY + "&query=" + query;
            return restTemplate.getForObject(uri, String.class);
        } catch (Error error) {
            return error.getMessage();
        }
    }

}
