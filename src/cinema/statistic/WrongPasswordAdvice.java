package cinema.statistic;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ControllerAdvice
public class WrongPasswordAdvice {

    @ResponseBody
    @ExceptionHandler(WrongPasswordException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    Map<String, String> wrongPasswordHandler(WrongPasswordException ex) {
        return Map.of("error", ex.getMessage());
    }
}
