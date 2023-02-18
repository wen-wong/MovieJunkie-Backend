package ca.mcgill.ecse428.moviejunkie.repository;

import ca.mcgill.ecse428.moviejunkie.model.Movie;
import ca.mcgill.ecse428.moviejunkie.model.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends CrudRepository<Tag, String> {
    Tag findTagByText(String text);
    List<Movie> findAllMoviesByTagText(String text);
}