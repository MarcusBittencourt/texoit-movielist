package br.com.texoit.movielist.movie;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.exceptions.CsvBeanIntrospectionException;
import com.opencsv.exceptions.CsvChainedException;
import com.opencsv.exceptions.CsvFieldAssignmentException;

public class MovieMappingStrategy extends ColumnPositionMappingStrategy {
  
  public MovieMappingStrategy() {
    this.setType(Movie.class);
  }

  @Override
  public Object populateNewBean(String[] line)
      throws CsvBeanIntrospectionException, CsvFieldAssignmentException, CsvChainedException {
    Movie movie = new Movie();
    movie.setYear(Integer.parseInt(line[0]));
    movie.setTitle(line[1]);
    movie.setStudios(line[2]);
    movie.setProducers(line[3]);
    movie.setWinner(line[4] == "yes");
    return movie;
  }
}
