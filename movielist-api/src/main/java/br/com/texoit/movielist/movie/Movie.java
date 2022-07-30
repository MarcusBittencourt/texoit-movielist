package br.com.texoit.movielist.movie;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "MOVIES")
public class Movie {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "`YEAR`", nullable = false)
  private Integer year;

  @Column(name = "TITLE", length = 80, nullable = false)
  private String title;

  @Column(name = "STUDIOS", length = 80, nullable = false)
  private String studios;

  @Column(name = "PRODUCERS", length = 255, nullable = false)
  private String producers;

  @Column(name = "WINNER", nullable = true)
  private Boolean winner;

  public Movie() {
  }

  public Movie(
    Integer year, 
    String title, 
    String studios, 
    String producers, 
    Boolean winner
  ) {
    this.year = year;
    this.title = title;
    this.studios = studios;
    this.producers = producers;
    this.winner = winner;
  }

  @Override
  public String toString() {
    return String.format(
      "Movie[id=%d, yeader=%d, title=%s, studios=%s, producers=%s, winner=%s]",
      this.id,
      this.year,
      this.title,
      this.studios,
      this.producers,
      this.winner 
    );
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getStudios() {
    return studios;
  }

  public void setStudios(String studios) {
    this.studios = studios;
  }

  public String getProducers() {
    return producers;
  }

  public void setProducers(String producers) {
    this.producers = producers;
  }

  public Boolean getWinner() {
    return winner;
  }

  public void setWinner(Boolean winner) {
    this.winner = winner;
  }

}
