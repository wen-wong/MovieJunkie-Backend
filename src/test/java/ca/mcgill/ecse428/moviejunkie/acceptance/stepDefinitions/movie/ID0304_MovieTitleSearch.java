package ca.mcgill.ecse428.moviejunkie.acceptance.stepDefinitions.movie;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ID0304_MovieTitleSearch {

    @Autowired
    private TestRestTemplate testRestTemplate;

    ResponseEntity<String> response;

    @When("the user searches for {string}")
    public void user_searches_for_movie(String searchString) {
        response = testRestTemplate.getForEntity("/search?query=" + searchString, String.class);
    }

    @Then("the system shall return a list of at least {int} movie\\(s)")
    public void system_shall_return_list_with_X_number_of_movies(int numOfMovies) throws JSONException {
        JSONObject jsonObject = new JSONObject(response.getBody());
        int size = jsonObject.getInt("total_pages");
        assert(size >= numOfMovies);
    }

    @Then("the list of movies shall contain {string}")
    public void list_of_movies_contains(String movieInList) throws JSONException {
        boolean isFound = false;
        JSONObject jsonObject = new JSONObject(response.getBody());
        JSONArray results = jsonObject.getJSONArray("results");
        for (int index = 0; index < results.length(); index++) {
            String title = results.getJSONObject(index).getString("original_title");
            if (title.equals(movieInList)) {
                isFound = true;
                break;
            }
        }
        assertTrue(isFound);
    }
}
