package br.com.texoit.movielist.movie;

import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

@Component
public class MovieDataLoader implements ApplicationRunner {

  private static final Logger log = LoggerFactory.getLogger(MovieDataLoader.class);
  private MovieRepository movieRepository;

  MovieDataLoader(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    URL resource = getClass().getClassLoader().getResource("data/movie-data.csv");
    Path path = Paths.get(resource.toURI());
    log.info("Locating csv data file %s".formatted(path.toAbsolutePath()));
    this.initDatabase(path);
    log.info("Seed database was loaded!");
  }

  private void initDatabase(Path dataSource) throws IOException {
    boolean presentData = movieRepository.count() > 0;
    log.info("Preloading movie list data");
    if (presentData)
      return;
    this.loadDataFromCSV(dataSource);
  }

  private void loadDataFromCSV(Path dataSource) throws IOException {
    Reader reader = Files.newBufferedReader(dataSource);
    CsvToBeanBuilder<Movie> builder = new CsvToBeanBuilder(reader)
      .withMappingStrategy(new MovieMappingStrategy())
      .withSeparator(';')
      .withSkipLines(1);
    CsvToBean<Movie> csvToBean = builder.build();
    movieRepository.saveAll(csvToBean.parse());
  }

}
