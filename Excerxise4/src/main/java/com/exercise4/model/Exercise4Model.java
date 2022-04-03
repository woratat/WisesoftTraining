package com.exercise4.model;

public class Exercise4Model {

	private String title;
	private String time;
	private String date;
	private int duration;

	public Exercise4Model(String title, String time, String date, int duration) {
		super();
		this.title = title;
		this.time = time;
		this.date = date;
		this.duration = duration;
	}

	public Exercise4Model() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
}
