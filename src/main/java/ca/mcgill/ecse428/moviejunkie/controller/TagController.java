package ca.mcgill.ecse428.moviejunkie.controller;

import ca.mcgill.ecse428.moviejunkie.dto.MovieDTO;
import ca.mcgill.ecse428.moviejunkie.dto.TagDTO;
import ca.mcgill.ecse428.moviejunkie.model.Movie;
import ca.mcgill.ecse428.moviejunkie.model.Tag;
import ca.mcgill.ecse428.moviejunkie.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class TagController {
    @Autowired
    private TagService service;

    @GetMapping(value = {"/tag/{text}", "/tag/{text}/"})
    public TagDTO getTag(@PathVariable("text") String text) {
        Tag tag = service.getTag(text);
        return convertToDTO(tag);
    }
    @GetMapping(value = {"/tag/{text}/movies", "/tag/{text}/movies/"})
    public List<MovieDTO> getMoviesByTag(@PathVariable("text") String text) {
        List<MovieDTO> movieDTOList = convertToDTO(service.getMovieListByTag(text));
        return movieDTOList;
    }
    @PostMapping(value = {"/tag/{text}", "/tag/{text}/"})
    public TagDTO createTag(@PathVariable("text") String text) {
        Tag tag = service.createTag(text);
        return convertToDTO(tag);
    }
    private TagDTO convertToDTO(Tag tag) {
        if (tag == null) {
            throw new IllegalArgumentException("Cannot create tag.");
        }
        TagDTO tagDTO = new TagDTO();
        tagDTO.setText(tag.getText());
        tagDTO.setMoviesList(convertToDTO(tag.getMovies()));
        return tagDTO;
    }
    private List<MovieDTO> convertToDTO(List<Movie> movieList) {
        List<MovieDTO> movieDTOList = Collections.emptyList();
        for (Movie movie : movieList) {
            movieDTOList.add(MovieDTO.convertToDTO(movie));
        }
        return movieDTOList;
    }
}
