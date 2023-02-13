package ca.mcgill.ecse428.moviejunkie.service;

import ca.mcgill.ecse428.moviejunkie.model.Movie;
import ca.mcgill.ecse428.moviejunkie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public Movie createMovie(int id, String name){
        Movie movie = new Movie(id, name);
        movieRepository.save(movie);
        return movie;
    }
}
