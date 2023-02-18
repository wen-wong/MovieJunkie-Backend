package ca.mcgill.ecse428.moviejunkie.repository;

import ca.mcgill.ecse428.moviejunkie.model.Movie;
import ca.mcgill.ecse428.moviejunkie.model.Tag;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, String> {
    Tag findTagByText(String text);
}