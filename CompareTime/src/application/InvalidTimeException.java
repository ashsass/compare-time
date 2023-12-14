package application;

public class InvalidTimeException extends Exception {
	private String message;
	
	public InvalidTimeException(int hours, int minutes, String meridian) {
		this(String.format("%02d:%02d %s", hours, minutes, meridian));
	}

	public InvalidTimeException(String message) {
		super("\nInvalid Time: " + message + ".\nPlease use HH:MM AM or HH:MM PM");
		this.message = message;
	}

}
