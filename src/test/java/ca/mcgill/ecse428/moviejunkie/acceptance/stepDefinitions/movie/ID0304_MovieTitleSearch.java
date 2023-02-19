package ca.mcgill.ecse428.moviejunkie.acceptance.stepDefinitions.movie;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ID0304_MovieTitleSearch {

    @Given("the following movies exist in the system:")
    public void movies_exist_in_the_system(DataTable datatable) {
        List<String> movieTitles = datatable.asList();

        for (String movieTitle: movieTitles) {
            System.out.println(movieTitle);
            // Create movies
        }
    }

    @When("the user searches for {string}")
    public void user_searches_for_movie(String searchString) {
        // Search for movie
    }

    @Then("the system shall return a list of at least {int} movie\\(s)")
    public void system_shall_return_list_with_X_number_of_movies(int numOfMovies) {
        // Check length of returned list
        assertTrue(true); // TODO: Remove once method implemented
    }

    @Then("the list of movies shall contain {string}")
    public void list_of_movies_contains(String movieInList) {
        // Check if movie in list of returned movies
        assertTrue(true); // TODO: Remove once method implemented
    }
}
