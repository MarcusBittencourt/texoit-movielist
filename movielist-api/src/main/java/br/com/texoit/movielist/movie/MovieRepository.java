package br.com.texoit.movielist.movie;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.texoit.movielist.movie.domain.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
