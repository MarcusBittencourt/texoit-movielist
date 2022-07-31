package br.com.texoit.movielist.movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
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
  public Map<String, List<MovieWinnerIntervalsDTO>> findWinnersIntervals() {
    List<Movie> winners = this.movieRepository.getWinners();
    Map<String, List<Movie>> groupedWinners = new HashMap<>();
    winners.forEach(winner -> {
      if (!groupedWinners.containsKey(winner.getProducers()))
        groupedWinners.put(winner.getProducers(), new ArrayList<Movie>());
      groupedWinners.get(winner.getProducers()).add(winner);
    });

    Map<String, List<MovieWinnerIntervalsDTO>> intervals = new HashMap<>();
    intervals.put("min", new ArrayList<>());
    intervals.put("max", new ArrayList<>());
    groupedWinners.keySet().forEach(group -> {
      List<Movie> movies = groupedWinners.get(group);
      for (ListIterator<Movie> iterator = movies.listIterator(); iterator.hasNext();) {
        Movie win = iterator.next();
        if (!iterator.hasNext()) break;
        MovieWinnerIntervalsDTO interval = new MovieWinnerIntervalsDTO();
        interval.setProducer(group);
        interval.setPreviousWin(win.getYear());
        interval.setFollowingWin(iterator.next().getYear());
        interval.setInterval(interval.getFollowingWin() - interval.getPreviousWin());
        if (intervals.get("min").isEmpty()
        || interval.getInterval() <= intervals.get("min").get(0).getInterval())
          intervals.get("min").add(0, interval);
        intervals.get("min").removeIf(i -> interval.getInterval() < i.getInterval());
        if (intervals.get("max").isEmpty()
        || interval.getInterval() >= intervals.get("max").get(0).getInterval())
          intervals.get("max").add(0, interval);
        intervals.get("max").removeIf(i -> interval.getInterval() > i.getInterval());
      }
    });
    return intervals;
  }

}
