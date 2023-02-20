package ca.mcgill.ecse428.moviejunkie.controller;

import ca.mcgill.ecse428.moviejunkie.dto.MovieDTO;
import ca.mcgill.ecse428.moviejunkie.dto.HashtagDTO;
import ca.mcgill.ecse428.moviejunkie.model.Hashtag;
import ca.mcgill.ecse428.moviejunkie.model.Movie;
import ca.mcgill.ecse428.moviejunkie.service.HashtagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
public class HashtagController {
    @Autowired
    private HashtagService service;

    @GetMapping(value = {"/hashtags", "/hashtags/"})
    public Set<HashtagDTO> getAllHashtags() {
        return HashtagDTO.convertToDTO(service.getAllHashtags());
    }
    @GetMapping(value = {"/hashtag/{text}", "/hashtag/{text}/"})
    public HashtagDTO getHashtag(@PathVariable("text") String text) {
        Hashtag hashtag = service.getHashtag(text);
        return HashtagDTO.convertToDTO(hashtag);
    }
    @GetMapping(value = {"/movie/search/hashtags", "/movie/search/hashtags/"})
    public Set<MovieDTO> getMoviesByHashtagList(@RequestParam("hashtags") String[] hashtags) {
        return MovieDTO.convertToDTO(service.getMovieListByHashtagList(hashtags));
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
