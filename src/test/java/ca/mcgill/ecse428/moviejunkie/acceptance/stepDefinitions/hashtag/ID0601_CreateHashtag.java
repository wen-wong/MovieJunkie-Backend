package ca.mcgill.ecse428.moviejunkie.acceptance.stepDefinitions.hashtag;

import ca.mcgill.ecse428.moviejunkie.repository.HashtagRepository;
import ca.mcgill.ecse428.moviejunkie.service.HashtagService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ID0601_CreateHashtag {

    @Autowired
    private HashtagRepository hashtagRepository;

    @Autowired
    private HashtagService hashtagService;

    private String hashtagErrorMessage;

    @Before
    public void setup() {
        hashtagRepository.deleteAll();
    }

    @After
    public void teardown() {
        hashtagRepository.deleteAll();
    }

    @Given("the following hashtags exist in the system:")
    public void there_are_X_hashtags_in_the_system(DataTable dataTable) {
        List<String> hashtagList = dataTable.asList();
        for (String hashtag: hashtagList) {
            hashtagService.createHashtag(hashtag);
        }
    }

    @When("a hashtag {string} is created")
    public void hashtag_is_created(String hashtagName) {
        try {
            hashtagService.createHashtag(hashtagName);
        } catch (Exception e) {
            hashtagErrorMessage = e.getMessage();
        }
    }

    @Then("there shall be {int} hashtag\\(s) in the system")
    public void there_shall_be_X_hashtags_in_the_system(int expectedNumHashtags) {
        assertEquals(expectedNumHashtags, hashtagRepository.count());
    }

    @Then("there shall exist a hashtag with name {string}")
    public void there_shall_exist_a_hashtag_with_name(String hashtagName) {
        hashtagRepository.findHashtagByText(hashtagName);
    }

    @And("the hashtag error message shall be {string}")
    public void the_hashtag_error_message_shall_be(String errorMessage) {
        assertEquals(hashtagErrorMessage, errorMessage);
    }
}
