package ca.mcgill.ecse428.moviejunkie.repository;

import ca.mcgill.ecse428.moviejunkie.model.Hashtag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HashtagRepository extends CrudRepository<Hashtag, String> {
    Hashtag findHashtagByText(String text);
    List<Hashtag> findAll();
}