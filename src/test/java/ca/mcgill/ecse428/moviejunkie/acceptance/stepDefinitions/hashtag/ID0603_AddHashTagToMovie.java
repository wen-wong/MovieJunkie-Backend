package ca.mcgill.ecse428.moviejunkie.acceptance.stepDefinitions.hashtag;

import ca.mcgill.ecse428.moviejunkie.model.Hashtag;
import ca.mcgill.ecse428.moviejunkie.model.Movie;
import ca.mcgill.ecse428.moviejunkie.repository.HashtagRepository;
import ca.mcgill.ecse428.moviejunkie.repository.MovieRepository;
import ca.mcgill.ecse428.moviejunkie.service.HashtagService;
import ca.mcgill.ecse428.moviejunkie.service.MovieService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ID0603_AddHashTagToMovie {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private HashtagRepository hashtagRepository;

    @Autowired
    private MovieService movieService;

    @Autowired
    private HashtagService hashtagService;

    @Before
    public void setup() {
        // Removing all hashtags from movies so that they can be safely deleted
        Iterable<Movie> movies = movieRepository.findAll();
        for (Movie movie: movies) {
            movie.setHashtags(null);
            movieRepository.save(movie);
        }

        hashtagRepository.deleteAll();
        movieRepository.deleteAll();
    }

    @After
    public void teardown() {
        // Removing all hashtags from movies so that they can be safely deleted
        Iterable<Movie> movies = movieRepository.findAll();
        for (Movie movie: movies) {
            movie.setHashtags(null);
            movieRepository.save(movie);
        }

        hashtagRepository.deleteAll();
        movieRepository.deleteAll();
    }

    @Given("the following movie ids exist in the system:")
    public void the_following_movie_ids_exist(DataTable dataTable) {
        List<Integer> movieIDList = dataTable.asList(Integer.class);

        for (Integer movieID: movieIDList) {
            movieService.createMovie(movieID, "");
        }
    }

    @Given("the {string} hashtag is associated to movie {int}")
    public void theHashtagIsAssociatedTMovie(String hashtagName, int movieID) {
        hashtagService.addHashtag(movieID, hashtagName);
    }

    @When("a hashtag {string} is added to movie {int}")
    public void a_hashtag_is_added_to_a_movie(String hashtag, int movieID) {
        testRestTemplate.postForEntity(
                String.format("/movie/%d/hashtag/add?text=%s", movieID, hashtag),
                null, null);
    }


    @Then("movie {int} shall have {int} hashtag\\(s) associated to it")
    public void movieMovieIDShallHaveHashtagSAssociatedToIt(int movieID, int numHashtagOnMovie) {
        Movie movie = movieRepository.findMovieById(movieID);
        assertEquals(numHashtagOnMovie, movie.getHashtags().size());
    }

    @And("movie {int} shall have {string} associated to it")
    public void movieMovieIDShallHaveAssociatedToIt(int movieID, String hashtagName) {
        Movie movie = movieRepository.findMovieById(movieID);
        for (Hashtag hashtag_from_movie: movie.getHashtags()) {
            if (hashtagName.equals(hashtag_from_movie.getText())) return;
        }
        fail("Expected hashtag could not be found in specified movies hashtags");
    }
}
