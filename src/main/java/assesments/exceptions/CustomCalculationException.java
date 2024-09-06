package main.java.assesments.exceptions;

public class CustomCalculationException extends Exception {
    private static final long serialVersionUID = 1L;

	public CustomCalculationException(String message, Throwable cause) {
        super(message, cause);
    }
}
