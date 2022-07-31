package br.com.texoit.movielist.movie.dto;

public class MovieWinnerIntervalsDTO {

  private String producer;
  private Integer interval;
  private Integer previousWin;
  private Integer followingWin;

  public Integer getFollowingWin() {
    return followingWin;
  }

  public void setFollowingWin(Integer followingWin) {
    this.followingWin = followingWin;
  }

  public String getProducer() {
    return producer;
  }

  public void setProducer(String producer) {
    this.producer = producer;
  }

  public Integer getInterval() {
    return interval;
  }

  public void setInterval(Integer interval) {
    this.interval = interval;
  }

  public Integer getPreviousWin() {
    return previousWin;
  }

  public void setPreviousWin(Integer previousWin) {
    this.previousWin = previousWin;
  }

  @Override
  public String toString() {
    return "MovieWinnerIntervalsDTO [followingWin=" + followingWin + ", interval=" + interval + ", previousWin="
        + previousWin + ", producer=" + producer + "]";
  }
}
