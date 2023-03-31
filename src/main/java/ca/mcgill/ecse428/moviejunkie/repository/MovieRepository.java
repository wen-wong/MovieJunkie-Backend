package ca.mcgill.ecse428.moviejunkie.repository;

import ca.mcgill.ecse428.moviejunkie.model.Movie;
import ca.mcgill.ecse428.moviejunkie.model.Playlist;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Integer> {
    Movie findMovieById(int id);
    List<Movie> findMoviesByHashtagsText(String text);
    List<Movie> findAll();
}
