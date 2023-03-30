package ca.mcgill.ecse428.moviejunkie.acceptance.stepDefinitions.playlist;

import ca.mcgill.ecse428.moviejunkie.model.Account;
import ca.mcgill.ecse428.moviejunkie.model.Playlist;
import ca.mcgill.ecse428.moviejunkie.repository.PlaylistRepository;
import ca.mcgill.ecse428.moviejunkie.service.AccountService;
import ca.mcgill.ecse428.moviejunkie.service.PlaylistService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ID0501_CreatePlaylist {

    @Autowired
    private PlaylistRepository playlistRepository;
    @Autowired
    private PlaylistService playlistService;
    @Autowired
    private AccountService accountService;
    private String playlistErrorMessage = "";
    private Account loggedIn;
    ResponseEntity<String> response;

    @Before
    public void setup() { playlistRepository.deleteAll(); }
    @After
    public void teardown() { playlistRepository.deleteAll(); }

    @Given("the following user is logged in:")
    public void user_is_logged_in(DataTable dataTable) throws Exception{
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row: rows) {
            String username = row.get("username");
            String email = row.get("email");
            String password = row.get("password");

            accountService.createAccount(username, email, password);
            loggedIn = accountService.getAccount(username);
        }
    }
    @Given("the following playlists already exist in the system for the user")
    public void there_are_X_playlists_in_the_system(DataTable dataTable) {
        List<String> playlistList = dataTable.asList();
        for (String playlist : playlistList) {
            playlistService.createPlaylist(loggedIn.getUsername(), playlist, "");
        }
    }
    @When("a playlist {string} is created")
    public void playlist_is_created(String playlistTitle) {
        try {
            playlistService.createPlaylist(loggedIn.getUsername(), playlistTitle, "");
        } catch (Exception e) {
            playlistErrorMessage = e.getMessage();
        }
    }
    @When("a playlist is created with no name")
    public void playlist_is_created_with_no_name() {
        try {
            playlistService.createPlaylist(loggedIn.getUsername(), null, "");
        } catch (Exception e) {
            playlistErrorMessage = e.getMessage();
        }
    }
    @Then("{int} playlist exists in the system for the user")
    public void there_shall_exist_X_playlist_for_user(int expectedNumPLaylists) {
        Set<Playlist> playlists = playlistService.getAllPlaylistByAccount(loggedIn.getUsername());
        assertEquals(expectedNumPLaylists, playlists.size());
    }
    @Then("there shall exist a playlist with name {string}")
    public void there_shall_exist_a_playlist_with_name(String playlistTitle) {
        List<Playlist> playlists = playlistRepository.findAll();
        Playlist foundPlaylist = null;
        for (Playlist playlist : playlists) {
            if (playlist.getTitle().equals(playlistTitle)) foundPlaylist = playlist;
        }
        assertNotNull(foundPlaylist);
    }
    @Then("{int} playlist shall exist in the system")
    public void there_shall_exist_X_playlist_in_the_system(int expectedNumPLaylists) {
        List<Playlist> playlists = playlistRepository.findAll();
        assertEquals(expectedNumPLaylists, playlists.size());
    }
    @Then("the system gives an error message")
    public void system_gives_an_error_message() {
        assertTrue(playlistErrorMessage.length() > 0);
    }
}
