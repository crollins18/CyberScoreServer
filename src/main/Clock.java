package main;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Clock {
	
	private long startTime;
	private SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
	
	public String start() {
		startTime = System.currentTimeMillis();
		Date temp = new Date(startTime);
		return sdf.format(temp);
	}
	
	public String getElapsedTime() {
		long elapsedTime = System.currentTimeMillis() - startTime;
		long elapsedSeconds = elapsedTime / 1000;
		long secondsDisplay = elapsedSeconds % 60;
		long elapsedMinutes = elapsedSeconds / 60;
		long minutesDisplay = elapsedMinutes % 60;
		long elaspedHours = elapsedMinutes / 60;
		return elaspedHours + "hrs " + minutesDisplay + "mins " + secondsDisplay + "secs";
	}
	
	

}
