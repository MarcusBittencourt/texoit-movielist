package br.com.texoit.movielist.movie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.texoit.movielist.movie.domain.Movie;

@RestController("movies")
public class MovieController {

  @Autowired
  private MovieService movieService; 

  @GetMapping(path = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Movie>> all() {
    return new ResponseEntity<>(this.movieService.findAll(), HttpStatus.OK);
  }

  @GetMapping(path = "/movies/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Movie> findById(@PathVariable("id") Long id) {
    return this.movieService.findById(id).map(
      found -> {
        return new ResponseEntity<>(found, HttpStatus.OK);
    })
    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  } 

  @PostMapping(path = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Movie> save(@RequestBody Movie movie) {
    return new ResponseEntity<>(this.movieService.save(movie), HttpStatus.CREATED);
  }

  @PutMapping(path = "/movies/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Movie> update(@PathVariable("id") Long id, @RequestBody Movie movie) {
    return this.movieService.findById(id).map(
      found -> {
        movie.setId(id);
        this.movieService.save(movie);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    })
    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping(path = "/movies/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Movie> delete(@PathVariable("id") Long id) {
    return this.movieService.findById(id).map(
      found -> {
        this.movieService.delete(id);
        return new ResponseEntity<>(found, HttpStatus.OK); 
    })
    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}
