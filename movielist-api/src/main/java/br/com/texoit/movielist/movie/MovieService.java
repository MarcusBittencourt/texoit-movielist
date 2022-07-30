package br.com.texoit.movielist.movie;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import br.com.texoit.movielist.movie.domain.Movie;
import br.com.texoit.movielist.movie.dto.MovieWinnerIntervalsDTO;

public interface MovieService {

  public abstract Movie save(Movie movie);

  public abstract void delete(Long id);

  public abstract List<Movie> findAll();

  public abstract Optional<Movie> findById(Long id);

  public abstract Map<String, Optional<MovieWinnerIntervalsDTO>> findWinnersIntervals();

}
