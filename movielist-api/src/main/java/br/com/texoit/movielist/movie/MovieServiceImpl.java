package br.com.texoit.movielist.movie;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.texoit.movielist.movie.domain.Movie;
import br.com.texoit.movielist.movie.dto.MovieWinnerIntervalsDTO;

@Service
public class MovieServiceImpl implements MovieService {

  @Autowired
  private MovieRepository movieRepository;

  @Override
  public Movie save(Movie movie) {
    return this.movieRepository.save(movie);
  }

  @Override
  public void delete(Long id) {
    this.movieRepository.deleteById(id);
  }

  @Override
  public List<Movie> findAll() {
    return this.movieRepository.findAll();
  }

  @Override
  public Optional<Movie> findById(Long id) {
    return this.movieRepository.findById(id);
  }

  @Override
  public Map<String, Optional<MovieWinnerIntervalsDTO>> findWinnersIntervals() {
    List<Movie> winners = this.movieRepository.getWinners();
    Map<String, MovieWinnerIntervalsDTO> intervals = new HashMap<String, MovieWinnerIntervalsDTO>();
    winners.forEach(winner -> {
      MovieWinnerIntervalsDTO movieInterval = new MovieWinnerIntervalsDTO();
      Boolean winnerExists = intervals.containsKey(winner.getProducers());

      if(winnerExists) movieInterval = intervals.get(winner.getProducers());
      else movieInterval.setProducer(winner.getProducers());

      if(movieInterval.getPreviousWin() == null || winner.getYear() < movieInterval.getPreviousWin()) 
        movieInterval.setPreviousWin(winner.getYear());

      if(movieInterval.getFollowingWin() == null || winner.getYear() > movieInterval.getFollowingWin())
        movieInterval.setFollowingWin(winner.getYear());

      movieInterval.setInterval(movieInterval.getFollowingWin() - movieInterval.getPreviousWin());
      intervals.put(winner.getProducers(), movieInterval);
    });
    
    Map<String, Optional<MovieWinnerIntervalsDTO>> result = new HashMap<String, Optional<MovieWinnerIntervalsDTO>>();
    Comparator<MovieWinnerIntervalsDTO> comparator = Comparator.comparing(MovieWinnerIntervalsDTO::getInterval);
    result.put("min", intervals.values().stream().filter(interval -> interval.getInterval() > 0).min(comparator));
    result.put("max", intervals.values().stream().filter(interval -> interval.getInterval() > 0).max(comparator));
    return result;
  }

}
