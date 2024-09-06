package main.java.assesments.exceptions;

public class AdapterNotFoundException extends Exception {
	
    private static final long serialVersionUID = 1L;

	public AdapterNotFoundException(String message) {
        super(message);
    }

    public AdapterNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
