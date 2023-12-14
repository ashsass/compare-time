package application;

public class Time implements Comparable<Time>{
	private int hours;
	private int minutes;
	private String meridian;
	
	public Time(int hours, int minutes, String meridian) throws InvalidTimeException215{
		this(String.format("%02d:%02d %s", hours, minutes, meridian));
} 
	
	public Time(String x) throws InvalidTimeException215 {
		/* Array Index:
		 * [0] = hours
		 * [1] = minutes
		 * [2] = meridian*/
		String[] timeArray = x.split("[:\\s]");
		
		/*Instance variables to hold the input values before assigning to class instance variables*/
		/*Checks if the user input is numeric for hours and minutes*/
		try {
			int hours = (int)Integer.parseInt(timeArray[0]);
			int minutes = (int)Integer.parseInt(timeArray[1]);
			String meridian = timeArray[2];
			
			/*Check that the value for hours and minutes is 1 - 2 numbers: 1, 25, 59, 02, etc*/
			if(timeArray[0].length() > 0 && timeArray[0].length() < 3
					&& timeArray[1].length() > 0 && timeArray[1].length() < 3) {
				/*Check that hours range from 1 - 12, minutes from 00 - 59, and meridian is AM or PM */
				if(hours > 0 && hours <= 12 && 
						minutes >= 0 && minutes < 60  &&
						(meridian.equals("AM") || meridian.equals("PM"))) {
					this.hours = hours;
					this.minutes = minutes;
					this.meridian = meridian;
				} else {	
					throw new InvalidTimeException215(x);
				}
			} else {	
				throw new InvalidTimeException215(x);
			}
		}
		catch (NumberFormatException ex) {
			throw new InvalidTimeException215(x);
		}	
	}
	
	@Override
	public int compareTo(Time o) {
	/*Return 1 if instance is later than parameter, 0 if instance and parameter is the same time, -1 if instance is earlier than parameter*/
		
		/*Create variables to hold the instance time and parameter time*/
		int instanceHours = hours == 12 ? 0 : hours;
		int parameterHours = o.hours == 12 ? 0 : o.hours;
	
		if((meridian.equals("PM") && o.meridian.equals("AM")) || 
				(meridian.equals(o.meridian) && instanceHours > parameterHours) || 
				(meridian.equals(o.meridian) && instanceHours == parameterHours && minutes > o.minutes)) 
			return 1;
		else if((meridian.equals("AM") && o.meridian.equals("PM")) || 
				(meridian.equals(o.meridian) && instanceHours < parameterHours) || 
				(meridian.equals(o.meridian) && instanceHours == parameterHours && minutes < o.minutes)) 
			return -1;
		else 
			return 0;
	}
	
	@Override
	public String toString() {
		return String.format("%02d:%02d %s", hours, minutes, meridian);
	}
	
	//Getters
	public int getHours() {
		return hours;
	}
	
	public int getMinutes() {
		return minutes;
	}
	
	public String getMeridian() {
		return meridian;
	}
}
