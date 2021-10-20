package cinema.cinema;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ControllerAdvice
public class IllegalSeatParamAdvice {

    @ResponseBody
    @ExceptionHandler(IllegalSeatParamException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> illegalSeatParamHandler(IllegalSeatParamException ex) {
        return Map.of("error", ex.getMessage());
    }
}
