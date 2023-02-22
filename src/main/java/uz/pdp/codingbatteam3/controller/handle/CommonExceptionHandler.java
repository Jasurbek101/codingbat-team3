package uz.pdp.codingbatteam3.controller.handle;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import uz.pdp.codingbatteam3.common.exception.RecordNotFoundException;

public class CommonExceptionHandler {
    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String recordNotFound(Exception e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "404";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String badRequest(Exception e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "404";
    }
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String nullPointer(Exception e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "404";
    }

}
