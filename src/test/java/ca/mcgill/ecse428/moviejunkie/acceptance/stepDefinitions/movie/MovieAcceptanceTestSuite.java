package ca.mcgill.ecse428.moviejunkie.acceptance.stepDefinitions.movie;

import ca.mcgill.ecse428.moviejunkie.MovieJunkieApplication;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/movie/")
@SpringBootTest(classes = MovieJunkieApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieAcceptanceTestSuite {
}
