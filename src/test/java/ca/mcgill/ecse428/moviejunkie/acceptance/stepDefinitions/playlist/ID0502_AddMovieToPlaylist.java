package ca.mcgill.ecse428.moviejunkie.acceptance.stepDefinitions.playlist;

import ca.mcgill.ecse428.moviejunkie.model.Movie;
import ca.mcgill.ecse428.moviejunkie.model.Playlist;
import ca.mcgill.ecse428.moviejunkie.repository.MovieRepository;
import ca.mcgill.ecse428.moviejunkie.repository.PlaylistRepository;
import ca.mcgill.ecse428.moviejunkie.service.MovieService;
import ca.mcgill.ecse428.moviejunkie.service.PlaylistService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ID0502_AddMovieToPlaylist {

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

    @Before
    public void setup() {
        movieRepository.deleteAll();
        playlistRepository.deleteAll();
    }
    @After
    @Transactional
    public void teardown() {
        for(Playlist p : playlistRepository.findAll()){
            p.getMovies().removeAll(p.getMovies());
        }
        playlistRepository.deleteAll();
        movieRepository.deleteAll();
    }

    @Given("the following ids of movies exist in the system:")
    public void the_following_movie_ids_exist(DataTable dataTable) {
        List<Integer> movieIDList = dataTable.asList(Integer.class);

        for (Integer movieID: movieIDList) {
            movieService.createMovie(movieID, "");
        }
    }


    @When("a user selects searches movie and selects movie with id {string}")
    public void userSearchesAndSelectMovie(String movie){
        int movieId = Integer.parseInt(movie);
        selectedMovie = movieService.getMovie(movieId);
    }

    @And("the user selects add to playlist")
    public void userSelectsAddToPlaylist(){}

    @And("the user selects playlist {string} to add movie to")
    @Transactional
    public void userSelectAddMovieToPlaylist(String playlist){
        boolean found = false;
        for(Playlist p : playlistRepository.findAll()){
            if(p.getTitle().equals(playlist)){
                selectedPlaylist = p;
                found = true;
                break;
            }
        }
        assertTrue(found);

        playlistService.addMovieToPlaylist(selectedPlaylist, selectedMovie.getId());

    }

    @Then("the playlist {string} contains the movie with id {string}")
    @Transactional
    public void playlistContainsMovie(String playlist, String movieid){
        Movie movie = movieService.getMovie(Integer.parseInt(movieid));
        for(Playlist p : playlistRepository.findAll()){
            if(p.getTitle().equals(playlist)){
                assertTrue(p.getMovies().contains(movie));
                break;
            }
        }

    }

    @When("a user is on playlist {string}")
    public void userIsOnPlaylist(String playlist){
        boolean found = false;
        for(Playlist p : playlistRepository.findAll()){
            if(p.getTitle().equals(playlist)){
                selectedPlaylist = p;
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @And("the user selects add movie")
    public void userSelectsAddMovie(){}

    @And("the user searches and selects movie with id {string}")
    @Transactional
    public void userSearchesAndSelectsMovie(String movieid) {
        selectedMovie = movieService.getMovie(Integer.parseInt(movieid));
        playlistService.addMovieToPlaylist(selectedPlaylist, selectedMovie.getId());
    }

    @And("the user does not select a playlist to add movie to")
    public void doesNotSelectAPlaylist(){
        selectedPlaylist = null;
    }

    @Then("no playlist contains the movie with id {string}")
    @Transactional
    public void noPlaylistContainsMovie(String movieid){
        Movie movie = movieService.getMovie(Integer.parseInt(movieid));
        boolean found = false;
        for(Playlist p : playlistRepository.findAll()){
            if(p.getMovies().contains(movie)){
                found = true;
                break;
            }
        }
        assertFalse(found);
    }


}
