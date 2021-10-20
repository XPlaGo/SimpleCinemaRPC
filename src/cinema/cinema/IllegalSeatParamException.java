package cinema.cinema;

public class IllegalSeatParamException extends RuntimeException {

    public IllegalSeatParamException(String reason) {
        super(reason);
    }
}
