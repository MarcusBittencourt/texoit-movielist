package br.com.texoit.movielist.movie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.texoit.movielist.movie.domain.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

  @Query(value = "SELECT * FROM MOVIES WHERE WINNER = TRUE ORDER BY PRODUCERS, RELEASE ASC", nativeQuery = true)
  List<Movie> getWinners();
}
