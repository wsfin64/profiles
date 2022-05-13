package com.example.demo.exception.handler;

import com.example.demo.exception.NaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class NaoEncontradoHandler {

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler({NaoEncontradoException.class})
    public ModeloHandler handle(NaoEncontradoException exception){
        ModeloHandler modeloHandler = new ModeloHandler(exception.getMessage());
        return modeloHandler;

    }
}
