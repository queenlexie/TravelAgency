public class WrongPESELException extends Exception {
    public WrongPESELException() {
    }

    public WrongPESELException(String message) {
        super(message);
    }

    public WrongPESELException(String message, Exception inner) {
        super(message);
    }
}
