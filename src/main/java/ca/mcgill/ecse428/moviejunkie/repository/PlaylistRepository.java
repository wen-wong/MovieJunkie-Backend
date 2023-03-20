package ca.mcgill.ecse428.moviejunkie.repository;

import ca.mcgill.ecse428.moviejunkie.model.Playlist;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlaylistRepository extends CrudRepository<Playlist, Integer> {
    Playlist findPlaylistById(int id);
    List<Playlist> findPlaylistsByAccountUsername(String username);
}
