package main.java.assesments.exceptions;

public class LogLineParseException extends Exception {
	
    private static final long serialVersionUID = 1L;

	public LogLineParseException(String message) {
        super(message);
    }

    public LogLineParseException(String message, Throwable cause) {
        super(message, cause);
    }
}