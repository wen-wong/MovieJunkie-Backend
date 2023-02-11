package ca.mcgill.ecse428.moviejunkie.repository;

import ca.mcgill.ecse428.moviejunkie.model.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Integer> {
    Movie findMovieById(int id);
}
