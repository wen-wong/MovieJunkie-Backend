package ca.mcgill.ecse428.moviejunkie.controller;

import ca.mcgill.ecse428.moviejunkie.dto.PlaylistDTO;
import ca.mcgill.ecse428.moviejunkie.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;
    //GET
    //get playlist
    @GetMapping(value = {"/playlist/{id}", "/playlist/{id}/"})
    public PlaylistDTO getPlaylist(@PathVariable("id") int id) {
        return PlaylistDTO.convertToDTO(playlistService.getPlaylist(id));
    }
    //get list of playlists from account
    @GetMapping(value = {"/{username}/playlists", "/{username}/playlists/"})
    public Set<PlaylistDTO> getAllPlaylists(@PathVariable("username") String username) {
        return PlaylistDTO.convertToDTO(playlistService.getAllPlaylistByAccount(username));
    }
    //POST
    //create playlist
    @PostMapping(value = {"/{username}/playlist/create", "/{username}/playlist/create/"})
    public PlaylistDTO createPlaylist(@PathVariable("username") String username,
                                      @RequestParam(value = "title", required = true) String title,
                                      @RequestParam(value = "description", required = false) String description) {
        return PlaylistDTO.convertToDTO(playlistService.createPlaylist(username, title, description));
    }
    //add movie to playlist
    @PutMapping(value = {"/{username}/playlist/{id}/add", "/{username}/playlist/{id}/add/"})
    public PlaylistDTO addMoviesToPlaylist(@PathVariable("username") String username,
                                           @PathVariable("id") int id,
                                           @RequestParam("movieIds") int[] movIds) {
        return PlaylistDTO.convertToDTO(playlistService.addMovieListToPlaylist(username, id, movIds));
    }
    //change movie order within playlist
    //returns updated playlist with new order
    @PutMapping(value = {"/{username}/playlist/{id}/{movieId}/{direction}/edit-order", "/{username}/playlist/{id}/{movieId}/{direction}/edit-order"})
    public PlaylistDTO editPlayListOrder(@PathVariable("username") String username,
                                           @PathVariable("id") int id,
                                           @PathVariable("movieId") int movId, @PathVariable("direction") boolean direction ) {
        return PlaylistDTO.convertToDTO(playlistService.reorderPlaylist(username, id, movId,direction));
    }
    //PUT
    //update title
    @PutMapping(value = {"/{username}/playlist/{id}/update-title", "/{username}/playlist/{id}/update-title/"})
    public PlaylistDTO updatePlaylistTitle(@PathVariable("username") String username,
                                           @PathVariable("id") int id,
                                           @RequestParam("title") String title) {
        return PlaylistDTO.convertToDTO(playlistService.updateTitle(username, id, title));
    }
    //update description
    @PutMapping(value = {"/{username}/playlist/{id}/update-description", "/{username}/playlist/{id}/update-description/"})
    public PlaylistDTO updatePlaylistDescription(@PathVariable("username") String username,
                                                 @PathVariable("id") int id,
                                                 @RequestParam("description") String description) {
        return PlaylistDTO.convertToDTO(playlistService.updateDescription(username, id, description));
    }
    //remove movie
    @PutMapping(value = {"/{username}/playlist/{id}/remove-movie", "/{username}/playlist/{id}/remove-movie/"})
    public PlaylistDTO removeMovieFromPlaylist(@PathVariable("username") String username,
                                               @PathVariable("id") int id,
                                               @RequestParam("movieId") int movId) {
        return PlaylistDTO.convertToDTO(playlistService.removeMovie(username, id, movId));
    }
    //DELETE
    //delete playlist
    @DeleteMapping(value = {"/{username}/playlist/{id}/delete", "/{username}/playlist/{id}/delete/"})
    public void deletePlaylist(@PathVariable("username") String username,
                               @PathVariable("id") int id) {
        playlistService.deletePlaylist(username, id);
    }
}
