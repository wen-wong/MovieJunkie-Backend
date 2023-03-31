package ca.mcgill.ecse428.moviejunkie.acceptance.stepDefinitions.movie;

import ca.mcgill.ecse428.moviejunkie.controller.MovieController;
import ca.mcgill.ecse428.moviejunkie.model.Hashtag;
import ca.mcgill.ecse428.moviejunkie.repository.AccountRepository;
import ca.mcgill.ecse428.moviejunkie.repository.HashtagRepository;
import ca.mcgill.ecse428.moviejunkie.repository.MovieRepository;
import ca.mcgill.ecse428.moviejunkie.service.AccountService;
import ca.mcgill.ecse428.moviejunkie.service.HashtagService;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.minidev.json.parser.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ID0303_MovieHashtagSearch {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private HashtagRepository hashtagRepository;

    @Autowired
    private HashtagService hashtagService;

    @Autowired
    private MovieRepository movieRepository;

    ResponseEntity<String> response;

    @Before
    public void setUp() {
        movieRepository.deleteAll();
        hashtagRepository.deleteAll();
    }

    @After
    public void teardown() {
        movieRepository.deleteAll();
        hashtagRepository.deleteAll();
        response = null;
    }

    @Given("the following hashtags exist:")
    public void theFollowingHashtagsExist(DataTable table) {
        List<String> hashtags = table.asList(String.class);
        int flag = 0;
        for (String hashtag : hashtags) {
            if(flag++ == 0) continue;
            // Process each hashtag separately
            hashtagService.createHashtag(hashtag);
        }
    }

    @And("the following movies have been tagged with {string}")
    public void theFollowingMoviesHaveBeenTaggedWith(String hashtag, DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            String movieId = row.get("movieId");
            // Process each movie ID separately
            int id = Integer.valueOf(movieId);
            hashtagService.addHashtag(id, hashtag);
        }
    }

    @When("a user searches hashtag {string}")
    public void aUserSearchesHashtag(String hashtag) {
        String url = "/movie/search/incomplete/hashtags/?hashtags=" + hashtag;
        response = testRestTemplate.getForEntity(url, String.class);
    }

    @Then("the system shall return a list with {int} movies")
    public void theSystemShallReturnAListWithMovies(int size) throws JsonProcessingException {
        // Create an ObjectMapper instance
        ObjectMapper mapper = new ObjectMapper();

        // Convert the JSON response to a JsonNode object
        JsonNode jsonNode = mapper.readTree(response.getBody());

        // Get the number of elements in the JSON list
        int numElements = jsonNode.size();
        assertEquals(size, numElements);
    }

    @And("the list of movies shall contain movie ids <{int}>, <{int}>, and <{int}>")
    public void theListOfMoviesShallContainMovieIdsAnd(int id1, int id2, int id3) throws JsonProcessingException {
        String s1 = String.valueOf(id1);
        String s2 = String.valueOf(id2);
        String s3 = String.valueOf(id3);
        String resp = response.getBody();
        assertTrue(resp.contains(s1) && resp.contains(s2) && resp.contains(s3));
    }

    @Then("the system shall return no movies")
    public void theSystemShallReturnAnErrorMessage() {
        assertTrue(response.getBody().equals("[]"));
    }
}
