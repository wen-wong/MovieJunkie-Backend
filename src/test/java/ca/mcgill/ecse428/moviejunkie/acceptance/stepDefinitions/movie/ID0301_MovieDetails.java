package ca.mcgill.ecse428.moviejunkie.acceptance.stepDefinitions.movie;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ID0301_MovieDetails {

    @When("the user attempts to see detailed information on {string}")
    public void user_attempts_to_see_detailed_information(String movieName) {
        // Call to backed to get additional information
    }


    @Then("the database shall contain {int} movies")
    public void database_shall_contain_X_number_of_movies(int numberOfMovies) {
        // Get number of movies from DB
        assertTrue(true); // TODO: Remove once method implemented

    }

    @Then("the database shall contain {string}")
    public void database_shall_contain_X_movie(String movieName) {
        // Check there is an entry for named movie
        assertTrue(true); // TODO: Remove once method implemented
    }

    @Then("the returned information shall contain:")
    public void the_returned_information_shall_contain(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row: rows) {
            // Check that row.get("movieField") contains row.get("fieldData")
            assertTrue(true); // TODO: Remove once method implemented

        }
    }
}
