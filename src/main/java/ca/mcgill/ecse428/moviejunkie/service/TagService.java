package ca.mcgill.ecse428.moviejunkie.service;

import ca.mcgill.ecse428.moviejunkie.model.Movie;
import ca.mcgill.ecse428.moviejunkie.model.Tag;
import ca.mcgill.ecse428.moviejunkie.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagService {

    @Autowired
    TagRepository tagRepository;

    @Transactional
    public Tag createTag(String text) {
        Tag tag = tagRepository.findTagByText(text);    //check if tag already exists
        if (tag == null) {
            tag = new Tag(text);
            tagRepository.save(tag);
            return tag;
        } else {
            throw new IllegalArgumentException("Tag already exists.");
        }
    }
    @Transactional
    public Tag createTag(String text, List<Movie> movies) {
        Tag tag = tagRepository.findTagByText(text);    //check if tag already exists
        if (tag == null) {
            tag = new Tag(text, movies);
            tagRepository.save(tag);
            return tag;
        } else {
            throw new IllegalArgumentException("Tag already exists.");
        }
    }
    @Transactional
    public Tag getTag(String text) {
        Tag tag = tagRepository.findTagByText(text);
        if (tag == null) {
            throw new IllegalArgumentException("Tag does not exist.");
        } else {
            return tag;
        }
    }
    @Transactional
    public List<Movie> getMovieListByTag(String text) {
        List<Movie> movieList  = tagRepository.findTagByText(text).getMovies();
        return movieList;
    }
    //TODO getting a tag by movie title ??
    @Transactional
    public void deleteTag(String text) throws IllegalArgumentException {
        Tag tag = tagRepository.findTagByText(text);
        if (tag == null) {
            throw new IllegalArgumentException("Tag does not exist.");
        } else {
            tagRepository.delete(tag);
        }
    }
}
