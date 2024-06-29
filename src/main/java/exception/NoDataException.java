package exception;

public class NoDataException extends RuntimeException{

    public NoDataException() {
    }

    public NoDataException(String s) {
        super(s);
    }
}
