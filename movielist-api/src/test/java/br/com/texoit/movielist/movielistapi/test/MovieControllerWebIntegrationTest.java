package br.com.texoit.movielist.movielistapi.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.texoit.movielist.movie.MovieController;
import br.com.texoit.movielist.movie.MovieRepository;
import br.com.texoit.movielist.movie.domain.Movie;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(MovieController.class)
@ComponentScan("br.com.texoit.movielist")
public class MovieControllerWebIntegrationTest {
  
  @Autowired
  MockMvc mvc;

  @MockBean
  private MovieRepository movieRepository;


  @Test
  public void testShouldReturnWinnersIntervalsWithDifferentWinners() throws Exception {
    List<Movie> movies = new ArrayList<>();
    movies.add(new Movie(1981, "Born to Test 1", "Studios Teste", "Producer 1", true));
    movies.add(new Movie(1982, "Born to Test 2", "Studios Teste", "Producer 1", true));
    movies.add(new Movie(1985, "Born to Test 3", "Studios Teste", "Producer 2", true));
    movies.add(new Movie(1987, "Born to Test 0", "Studios Teste", "Producer 1", true));
    movies.add(new Movie(1987, "Born to Test 5", "Studios Teste", "Producer 3", true));
    movies.add(new Movie(1995, "Born to Test 8", "Studios Teste", "Producer 4", true));
    movies.add(new Movie(1997, "Born to Test 10", "Studios Teste", "Producer 5", true));
    movies.add(new Movie(1999, "Born to Test 4", "Studios Teste", "Producer 2", true));
    movies.add(new Movie(1999, "Born to Test 9", "Studios Teste", "Producer 5", true));
    movies.add(new Movie(2000, "Born to Test 6", "Studios Teste", "Producer 3", true));
    movies.add(new Movie(2022, "Born to Test 7", "Studios Teste", "Producer 4", true));

    when(movieRepository.getWinners()).thenReturn(movies);

    mvc.perform(MockMvcRequestBuilders
      .get("/movies/winners")
      .contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.min[0].producer").value("Producer 1"))
      .andExpect(jsonPath("$.min[0].interval").value("1"))
      .andExpect(jsonPath("$.min[0].previousWin").value("1981"))
      .andExpect(jsonPath("$.min[0].followingWin").value("1982"))
      .andExpect(jsonPath("$.max[0].producer").value("Producer 4"))
      .andExpect(jsonPath("$.max[0].interval").value("27"))
      .andExpect(jsonPath("$.max[0].previousWin").value("1995"))
      .andExpect(jsonPath("$.max[0].followingWin").value("2022"));
  }

  @Test
  public void testShouldReturnWinnersIntervalsWithSameWinners() throws Exception {
    List<Movie> movies = new ArrayList<>();
    movies.add(new Movie(1980, "Born to Test 1", "Studios Teste", "Producer 1", true));
    movies.add(new Movie(1981, "Born to Test 2", "Studios Teste", "Producer 1", true));
    movies.add(new Movie(1985, "Born to Test 3", "Studios Teste", "Producer 1", true));
    movies.add(new Movie(2024, "Born to Test 0", "Studios Teste", "Producer 1", true));
    movies.add(new Movie(1987, "Born to Test 5", "Studios Teste", "Producer 3", true));
    movies.add(new Movie(1995, "Born to Test 8", "Studios Teste", "Producer 4", true));
    movies.add(new Movie(1997, "Born to Test 10", "Studios Teste", "Producer 5", true));
    movies.add(new Movie(1999, "Born to Test 4", "Studios Teste", "Producer 2", true));
    movies.add(new Movie(1999, "Born to Test 9", "Studios Teste", "Producer 5", true));
    movies.add(new Movie(2000, "Born to Test 6", "Studios Teste", "Producer 3", true));
    movies.add(new Movie(2022, "Born to Test 7", "Studios Teste", "Producer 4", true));

    when(movieRepository.getWinners()).thenReturn(movies);

    mvc.perform(MockMvcRequestBuilders
      .get("/movies/winners")
      .contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.min[0].producer").value("Producer 1"))
      .andExpect(jsonPath("$.min[0].interval").value("1"))
      .andExpect(jsonPath("$.min[0].previousWin").value("1980"))
      .andExpect(jsonPath("$.min[0].followingWin").value("1981"))
      .andExpect(jsonPath("$.max[0].producer").value("Producer 1"))
      .andExpect(jsonPath("$.max[0].interval").value("39"))
      .andExpect(jsonPath("$.max[0].previousWin").value("1985"))
      .andExpect(jsonPath("$.max[0].followingWin").value("2024"));
  }

  @Test
  public void testShouldReturnWinnersIntervalsWithTwiceWinnerAtSameYear() throws Exception {
    List<Movie> movies = new ArrayList<>();
    movies.add(new Movie(1981, "Born to Test 1", "Studios Teste", "Producer 1", true));
    movies.add(new Movie(1981, "Born to Test 2", "Studios Teste", "Producer 1", true));
    movies.add(new Movie(1985, "Born to Test 3", "Studios Teste", "Producer 2", true));
    movies.add(new Movie(1987, "Born to Test 0", "Studios Teste", "Producer 1", true));
    movies.add(new Movie(1987, "Born to Test 5", "Studios Teste", "Producer 3", true));
    movies.add(new Movie(1995, "Born to Test 8", "Studios Teste", "Producer 4", true));
    movies.add(new Movie(1997, "Born to Test 10", "Studios Teste", "Producer 5", true));
    movies.add(new Movie(1999, "Born to Test 4", "Studios Teste", "Producer 2", true));
    movies.add(new Movie(1999, "Born to Test 9", "Studios Teste", "Producer 5", true));
    movies.add(new Movie(2000, "Born to Test 6", "Studios Teste", "Producer 3", true));
    movies.add(new Movie(2022, "Born to Test 7", "Studios Teste", "Producer 4", true));

    when(movieRepository.getWinners()).thenReturn(movies);

    mvc.perform(MockMvcRequestBuilders
      .get("/movies/winners")
      .contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.min[0].producer").value("Producer 1"))
      .andExpect(jsonPath("$.min[0].interval").value("0"))
      .andExpect(jsonPath("$.min[0].previousWin").value("1981"))
      .andExpect(jsonPath("$.min[0].followingWin").value("1981"))
      .andExpect(jsonPath("$.max[0].producer").value("Producer 4"))
      .andExpect(jsonPath("$.max[0].interval").value("27"))
      .andExpect(jsonPath("$.max[0].previousWin").value("1995"))
      .andExpect(jsonPath("$.max[0].followingWin").value("2022"));
  }

  @Test
  public void testShouldReturnWinnersIntervalsWithSameMinIntervalTwoDifferentWinners() throws Exception {
    List<Movie> movies = new ArrayList<>();
    movies.add(new Movie(1981, "Born to Test 1", "Studios Teste", "Producer 1", true));
    movies.add(new Movie(1981, "Born to Test 2", "Studios Teste", "Producer 1", true));
    movies.add(new Movie(1985, "Born to Test 3", "Studios Teste", "Producer 2", true));
    movies.add(new Movie(1985, "Born to Test 4", "Studios Teste", "Producer 2", true));
    movies.add(new Movie(1987, "Born to Test 0", "Studios Teste", "Producer 1", true));
    movies.add(new Movie(1987, "Born to Test 5", "Studios Teste", "Producer 3", true));
    movies.add(new Movie(1995, "Born to Test 8", "Studios Teste", "Producer 4", true));
    movies.add(new Movie(1997, "Born to Test 10", "Studios Teste", "Producer 5", true));
    movies.add(new Movie(1999, "Born to Test 9", "Studios Teste", "Producer 5", true));
    movies.add(new Movie(2000, "Born to Test 6", "Studios Teste", "Producer 3", true));
    movies.add(new Movie(2022, "Born to Test 7", "Studios Teste", "Producer 4", true));

    when(movieRepository.getWinners()).thenReturn(movies);

    mvc.perform(MockMvcRequestBuilders
      .get("/movies/winners")
      .contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.min[0].producer").value("Producer 2"))
      .andExpect(jsonPath("$.min[0].interval").value("0"))
      .andExpect(jsonPath("$.min[0].previousWin").value("1985"))
      .andExpect(jsonPath("$.min[0].followingWin").value("1985"))
      .andExpect(jsonPath("$.min[1].producer").value("Producer 1"))
      .andExpect(jsonPath("$.min[1].interval").value("0"))
      .andExpect(jsonPath("$.min[1].previousWin").value("1981"))
      .andExpect(jsonPath("$.min[1].followingWin").value("1981"))
      .andExpect(jsonPath("$.max[0].producer").value("Producer 4"))
      .andExpect(jsonPath("$.max[0].interval").value("27"))
      .andExpect(jsonPath("$.max[0].previousWin").value("1995"))
      .andExpect(jsonPath("$.max[0].followingWin").value("2022"));
  }

  @Test
  public void testShouldReturnWinnersIntervalsWithSameMaxIntervalTwoDifferentWinners() throws Exception {
    List<Movie> movies = new ArrayList<>();
    movies.add(new Movie(1981, "Born to Test 1", "Studios Teste", "Producer 1", true));
    movies.add(new Movie(1981, "Born to Test 2", "Studios Teste", "Producer 1", true));
    movies.add(new Movie(1985, "Born to Test 3", "Studios Teste", "Producer 2", true));
    movies.add(new Movie(1987, "Born to Test 0", "Studios Teste", "Producer 1", true));
    movies.add(new Movie(1987, "Born to Test 5", "Studios Teste", "Producer 3", true));
    movies.add(new Movie(1995, "Born to Test 8", "Studios Teste", "Producer 4", true));
    movies.add(new Movie(1997, "Born to Test 10", "Studios Teste", "Producer 5", true));
    movies.add(new Movie(1999, "Born to Test 4", "Studios Teste", "Producer 2", true));
    movies.add(new Movie(1999, "Born to Test 9", "Studios Teste", "Producer 5", true));
    movies.add(new Movie(2014, "Born to Test 6", "Studios Teste", "Producer 3", true));
    movies.add(new Movie(2022, "Born to Test 7", "Studios Teste", "Producer 4", true));

    when(movieRepository.getWinners()).thenReturn(movies);

    mvc.perform(MockMvcRequestBuilders
      .get("/movies/winners")
      .contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.min[0].producer").value("Producer 1"))
      .andExpect(jsonPath("$.min[0].interval").value("0"))
      .andExpect(jsonPath("$.min[0].previousWin").value("1981"))
      .andExpect(jsonPath("$.min[0].followingWin").value("1981"))
      .andExpect(jsonPath("$.max[0].producer").value("Producer 4"))
      .andExpect(jsonPath("$.max[0].interval").value("27"))
      .andExpect(jsonPath("$.max[0].previousWin").value("1995"))
      .andExpect(jsonPath("$.max[0].followingWin").value("2022"))
      .andExpect(jsonPath("$.max[1].producer").value("Producer 3"))
      .andExpect(jsonPath("$.max[1].interval").value("27"))
      .andExpect(jsonPath("$.max[1].previousWin").value("1987"))
      .andExpect(jsonPath("$.max[1].followingWin").value("2014"));
  }
}
