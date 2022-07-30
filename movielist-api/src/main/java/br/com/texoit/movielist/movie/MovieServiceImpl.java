package br.com.texoit.movielist.movie;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.texoit.movielist.movie.domain.Movie;

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

}
