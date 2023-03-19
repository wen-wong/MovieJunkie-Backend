package ca.mcgill.ecse428.moviejunkie.controller;

import ca.mcgill.ecse428.moviejunkie.dto.MovieDTO;
import ca.mcgill.ecse428.moviejunkie.dto.HashtagDTO;
import ca.mcgill.ecse428.moviejunkie.model.Hashtag;
import ca.mcgill.ecse428.moviejunkie.model.Movie;
import ca.mcgill.ecse428.moviejunkie.service.HashtagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    @GetMapping(value = {"/movie/{id}/hashtags", "/movie/{id}/hashtags/"})
    public Set<HashtagDTO> getHashtagsByMovie(@PathVariable("id") int id) {
        return HashtagDTO.convertToDTO(service.getHashtagsByMovieId(id));
    }

    @GetMapping(value = {"/movie/search/hashtags", "/movie/search/hashtags/"})
    public Set<MovieDTO> getMoviesByHashtagList(@RequestParam("hashtags") String[] hashtags) {
        return MovieDTO.convertToDTO(service.getMovieListByHashtagList(hashtags));
    }

    /*
    Note, the /movies/search/hashtags method above works in postman, but it's unclear how to input the JSON request associated with it
    To use the method below, the JSON body should look like this:
    {
    "hashtags": ["ht1", ...]
    }
    This method works by checking if any of the hashtags in our system contain the characters that have been searched for
    ie they can be incomplete (someone searching for "fu" can find movies associated with hashtag "fun")
     */
    //get a set of all movies that correspond to the search list of hashtags (they can be complete or incomplete)
    @GetMapping(value = {"/movie/search/incomplete/hashtags", "/movie/search/incomplete/hashtags/"})
    public Set<MovieDTO> getMoviesByIncompleteHashtags(@RequestParam("hashtags") String[] hashtags) {
        return MovieDTO.convertToDTO(service.getMovieListByIncompleteHashtagList(hashtags));
    }

    //get a set of all hashtags that correspond to the search list of hashtags (they can be complete or incomplete)
    @GetMapping(value = {"/hashtag/incomplete", "/hashtag/incomplete/"})
    public Set<HashtagDTO> getHashtagsByIncompleteHashtags(@RequestParam("hashtags") String[] hashtags){
        return HashtagDTO.convertToDTO(service.getHashtagByIncompleteHashtagList(hashtags));
    }


    @PostMapping(value = {"/hashtag/create", "/hashtag/create/"})
    public HashtagDTO createHashtag(@RequestParam("text") String text) {
        Hashtag hashtag = service.createHashtag(text);
        return HashtagDTO.convertToDTO(hashtag); // TODO: Add error handling
    }

    @PostMapping(value={"/movie/{id}/hashtag/add", "/movie/{id}/hashtag/add/"})
    public MovieDTO addHashtag(@PathVariable("id") int id, @RequestParam(value = "text") String text) {
        Movie movie = service.addHashtag(id, text);
        return MovieDTO.convertToDTO(movie);
    }

    @DeleteMapping(value={"/hashtag/{text}", "/hashtag/{text}/"})
    public void deleteHashtag(@PathVariable("text") String text) {
        service.deleteHashtag(text);
    }
}
