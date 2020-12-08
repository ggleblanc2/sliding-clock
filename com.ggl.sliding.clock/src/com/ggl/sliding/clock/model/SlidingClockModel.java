package com.ggl.sliding.clock.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SlidingClockModel {

	private static final SimpleDateFormat OUTPUT_TIME = new SimpleDateFormat("hhmmssa");

	private String hourString;
	private String tenMinuteString;
	private String minuteString;
	private String tenSecondString;
	private String secondString;
	private String meridianString;

	public SlidingClockModel() {
		setCurrentTime();
	}

	public void setCurrentTime() {
		Calendar currentTime = Calendar.getInstance();
		String timeString = OUTPUT_TIME.format(currentTime.getTime());
		this.hourString = convertHourString(timeString);
		this.tenMinuteString = timeString.substring(2, 3);
		this.minuteString = timeString.substring(3, 4);
		this.tenSecondString = timeString.substring(4, 5);
		this.secondString = timeString.substring(5, 6);
		this.meridianString = timeString.substring(6, 8);
	}

	private String convertHourString(String timeString) {
		String hourString = timeString.substring(0, 2);
		int hour = Integer.valueOf(hourString);
		return String.format("%2d", hour);
	}

	public String getHourString() {
		return hourString;
	}

	public String getTenMinuteString() {
		return tenMinuteString;
	}

	public String getMinuteString() {
		return minuteString;
	}

	public String getTenSecondString() {
		return tenSecondString;
	}

	public String getSecondString() {
		return secondString;
	}

	public String getMeridianString() {
		return meridianString;
	}

}
