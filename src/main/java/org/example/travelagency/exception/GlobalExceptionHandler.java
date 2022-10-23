package org.example.travelagency.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ModelAndView entityNotFoundExceptionHandler(HttpServletRequest request, EntityNotFoundException exception) {
        return getModelAndView(request, HttpStatus.NOT_FOUND, exception);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView internalServerErrorHandler(HttpServletRequest request, Exception exception) {
        return getModelAndView(request, HttpStatus.INTERNAL_SERVER_ERROR, exception);
    }

    private ModelAndView getModelAndView(HttpServletRequest request, HttpStatus httpStatus, Exception exception) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("code", String.format("%s %s", httpStatus.value(), httpStatus.getReasonPhrase()));
        modelAndView.addObject("message", exception.getMessage());
        return modelAndView;
    }
}
