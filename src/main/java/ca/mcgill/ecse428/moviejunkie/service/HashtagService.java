package ca.mcgill.ecse428.moviejunkie.service;

import ca.mcgill.ecse428.moviejunkie.model.Movie;
import ca.mcgill.ecse428.moviejunkie.model.Hashtag;
import ca.mcgill.ecse428.moviejunkie.repository.HashtagRepository;
import ca.mcgill.ecse428.moviejunkie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class HashtagService {

    @Autowired
    HashtagRepository hashtagRepository;
    @Autowired
    MovieRepository movieRepository;
    @Transactional
    public Hashtag createHashtag(String text) {
        Hashtag hashtag = hashtagRepository.findHashtagByText(text);    //check if tag already exists
        if (hashtag == null) {
            hashtag = new Hashtag(text);
            hashtagRepository.save(hashtag);
            return hashtag;
        } else {
            throw new IllegalArgumentException("Hashtag already exists.");
        }
    }
    @Transactional
    public Hashtag createHashtag(String text, Set<Movie> movies) {
        Hashtag hashtag = hashtagRepository.findHashtagByText(text);
        if (hashtag == null) {
            hashtag = new Hashtag(text, movies);
            hashtagRepository.save(hashtag);
            return hashtag;
        } else {
            throw new IllegalArgumentException("Hashtag already exists.");
        }
    }
    @Transactional
    public Hashtag getHashtag(String text) {
        Hashtag hashtag = hashtagRepository.findHashtagByText(text);
        if (hashtag == null) {
            throw new IllegalArgumentException("Tag does not exist.");
        } else {
            return hashtag;
        }
    }
    @Transactional
    public Set<Movie> getMovieListByHashtag(String text) {
        Set<Movie> movieList  = hashtagRepository.findHashtagByText(text).getMovies();
        return movieList;
    }
    @Transactional
    public void deleteHashtag(String text) throws IllegalArgumentException {
        Hashtag hashtag = hashtagRepository.findHashtagByText(text);
        if (hashtag == null) {
            throw new IllegalArgumentException("Hashtag does not exist.");
        } else {
            hashtagRepository.delete(hashtag);
        }
    }
    @Transactional
    public Movie addHashtag(int id, String text) {
        Hashtag hashtag = hashtagRepository.findHashtagByText(text);
        Movie movie = movieRepository.findMovieById(id);
        if (movie == null) {
            throw new IllegalArgumentException("Movie does not exist.");
        }
        if (hashtag == null) {
            hashtag = new Hashtag(text);
            Set<Movie> movies = new HashSet<Movie>();
            movies.add(movie);
            hashtag.setMovies(movies);
            hashtagRepository.save(hashtag);
        }
        movie.addHashtag(hashtag);
        movieRepository.save(movie);
        return movie;
    }
}
