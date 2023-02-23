package uz.pdp.codingbatteam3.controller.handle;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import uz.pdp.codingbatteam3.common.exception.RecordNotFoundException;
import uz.pdp.codingbatteam3.common.exception.RecordAlreadyExistException;

@Controller
@ControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String recordNotFound(Exception e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "home";
    }

    @ExceptionHandler(RecordAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String badRequest(Exception e, Model model) {
        model.addAttribute("User Already exists ", e.getMessage());
        return "register";
    }
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String nullPointer(Exception e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "home";
    }

}
