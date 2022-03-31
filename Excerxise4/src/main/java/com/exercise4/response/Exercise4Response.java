package com.exercise4.response;

public class Exercise4Response {

  private String title;
  private String time;
  private String date;
  private int duration;

  public Exercise4Response(
    String title,
    String time,
    String date,
    int duration
  ) {
    super();
    this.title = title;
    this.time = time;
    this.date = date;
    this.duration = duration;
  }

  public Exercise4Response() {
    super();
  }

  /**
   * @return String return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * @param title the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * @return String return the time
   */
  public String getTime() {
    return time;
  }

  /**
   * @param time the time to set
   */
  public void setTime(String time) {
    this.time = time;
  }

  /**
   * @return String return the date
   */
  public String getDate() {
    return date;
  }

  /**
   * @param date the date to set
   */
  public void setDate(String date) {
    this.date = date;
  }

  /**
   * @return int return the duration
   */
  public int getDuration() {
    return duration;
  }

  /**
   * @param duration the duration to set
   */
  public void setDuration(int duration) {
    this.duration = duration;
  }
}
