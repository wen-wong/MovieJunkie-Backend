package ca.mcgill.ecse428.moviejunkie.controller;

import ca.mcgill.ecse428.moviejunkie.dto.MovieDTO;
import ca.mcgill.ecse428.moviejunkie.dto.HashtagDTO;
import ca.mcgill.ecse428.moviejunkie.model.Hashtag;
import ca.mcgill.ecse428.moviejunkie.model.Movie;
import ca.mcgill.ecse428.moviejunkie.service.HashtagService;
import ca.mcgill.ecse428.moviejunkie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
public class HashtagController {
    @Autowired
    private HashtagService service;
    @Autowired
    private MovieService movieService;

    @GetMapping(value = {"/hashtag/{text}", "/hashtag/{text}/"})
    public HashtagDTO getHashtag(@PathVariable("text") String text) {
        Hashtag hashtag = service.getHashtag(text);
        return HashtagDTO.convertToDTO(hashtag);
    }
    @GetMapping(value = {"/hashtag/{text}/movies", "/hashtag/{text}/movies/"})
    public Set<MovieDTO> getMoviesByHashtag(@PathVariable("text") String text) {
        Set<MovieDTO> movieDTOList = MovieDTO.convertToDTO(service.getMovieListByHashtag(text));
        return movieDTOList;
    }
    @PostMapping(value = {"/hashtag/create", "/hashtag/create/"})
    public HashtagDTO createHashtag(@RequestParam("text") String text) {
        Hashtag hashtag = service.createHashtag(text);
        return HashtagDTO.convertToDTO(hashtag);
    }
    @PostMapping(value={"/movie/{id}/hashtag/add", "/movie/{id}/hashtag/add/"})
    public MovieDTO addHashtag(@PathVariable("id") int id, @RequestParam(value = "text") String text) {
        Movie movie = service.addHashtag(id, text);
        return MovieDTO.convertToDTO(movie);
    }
}