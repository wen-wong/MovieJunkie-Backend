package ca.mcgill.ecse428.moviejunkie.acceptance.stepDefinitions.playlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ca.mcgill.ecse428.moviejunkie.model.Account;
import ca.mcgill.ecse428.moviejunkie.model.Movie;
import ca.mcgill.ecse428.moviejunkie.model.Playlist;
import ca.mcgill.ecse428.moviejunkie.repository.MovieRepository;
import ca.mcgill.ecse428.moviejunkie.repository.PlaylistRepository;
import ca.mcgill.ecse428.moviejunkie.service.AccountService;
import ca.mcgill.ecse428.moviejunkie.service.MovieService;
import ca.mcgill.ecse428.moviejunkie.service.PlaylistService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

public class ID0503_ReorderMoviesInPlaylist {

    @Autowired
    private PlaylistRepository playlistRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieService movieService;
    @Autowired
    private PlaylistService playlistService;
    private Movie selectedMovie;
    private Playlist selectedPlaylist;

    int currentMoviePosition;

    HashMap<String, Boolean> directions;

    @Before
    public void setup() { playlistRepository.deleteAll(); }
    @After
    public void teardown() { playlistRepository.deleteAll(); }

    @And("the following movies exist in the system")
    public void theFollowingMoviesExistInTheSystem(DataTable dataTable) {
        List<Map<String, String>> movies = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> movie : movies) {
            int id = Integer.parseInt(movie.get("id"));
            String title = movie.get("movie");

            movieService.createMovie(id, title);
        }
    }

    @And("the Summer playlist contains the following movies in the following order")
    public void theSummerPlaylistContainsTheFollowingMoviesInTheFollowingOrder(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        List<Playlist> playlists = playlistRepository.findAll();

        for (Playlist playlist : playlists) {
            if (playlist.getTitle().equals("Summer")) {
                int ctr = 0;
                List<Movie> movies = movieRepository.findAll();
                int[] movieIds = new int[movies.size()];

                for (Map<String, String> row : rows) {

                    for (Movie movie : movies) {
                        if (row.get("movie").equals(movie.getName())) {
                            movieIds[ctr] = movie.getId();
                            ctr++;
                            break;
                        }
                    }

                }
                playlistService.addMovieListToPlaylist(ID0501_CreatePlaylist.getLoggedIn().getUsername(), playlist.getId(), movieIds);
            }
        }
    }

    @And("the movies can be reordered by one position at a time using the following directions")
    public void theMoviesCanBeReorderedByOnePositionAtATimeUsingTheFollowingDirections(DataTable dataTable) {
        List<String> directionList = dataTable.asList();

        directions = new HashMap<>();

        for (String direction : directionList) {
            if (direction.equals("Up")) {
                directions.put(direction, false);
            }

            else {
                directions.put(direction, true);
            }
        }
    }

    @When("the user is on playlist {string}")
    public void theUserIsOnPlaylist(String arg0) {
        for (Playlist playlist : playlistRepository.findAll()) {
            if (playlist.getTitle().equals(arg0)) {
                selectedPlaylist = playlist;
                break;
            }
        }
    }

    @And("the user selects to move movie {string} one position in the {string} direction")
    public void theUserSelectsToMoveMovieOnePositionInTheDirection(String arg0, String arg1) {
        int movieId = -1;

        for (Movie movie : selectedPlaylist.getMovies()) {
            if (movie.getName().equals(arg0)) {
                movieId = movie.getId();
                break;
            }
        }

        playlistService.reorderPlaylist(ID0501_CreatePlaylist.getLoggedIn().getUsername(), selectedPlaylist.getId(), movieId, directions.get(arg1));
    }

    @Then("the playlist {string} contains the movie {string} in position {string}")
    public void thePlaylistContainsTheMovieInPosition(String arg0, String arg1, String arg2) {
        int ctr = 1;

        for (Movie movie : selectedPlaylist.getMovies()) {
            if (movie.getName().equals(arg1)) {
                assertEquals(ctr, Integer.parseInt(arg2));
                break;
            }

            ctr++;
        }
    }

    @And("the user selects to move movie {string}")
    public void theUserSelectsToMoveMovie(String arg0) {
        for (Movie movie : selectedPlaylist.getMovies()) {
            if (movie.getName().equals(arg0)) {
                selectedMovie = movie;
                break;
            }
        }
    }

    @And("the movie selected is at position {string}")
    public void theMovieSelectedIsAtPosition(String arg0) {
        currentMoviePosition = Integer.parseInt(arg0);
    }

    @And("the user selects an invalid {string} direction")
    public void theUserSelectsAnInvalidDirection(String arg0) {
        playlistService.reorderPlaylist(ID0501_CreatePlaylist.getLoggedIn().getUsername(), selectedPlaylist.getId(), selectedMovie.getId(), directions.get(arg0));
    }

    @Then("the playlist {string} does not change order")
    public void thePlaylistDoesNotChangeOrder(String arg0) {
        int ctr = 1;

        for (Movie movie : selectedPlaylist.getMovies()) {
            if (movie.equals(selectedMovie)) {
                assertEquals(ctr, currentMoviePosition);
                break;
            }

            ctr++;
        }
    }
}
