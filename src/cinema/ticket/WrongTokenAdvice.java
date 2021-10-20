package cinema.ticket;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ControllerAdvice
public class WrongTokenAdvice {

    @ResponseBody
    @ExceptionHandler(WrongTokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> wrongTokenHandler(WrongTokenException ex) {
        return Map.of("error", ex.getMessage());
    }
}
