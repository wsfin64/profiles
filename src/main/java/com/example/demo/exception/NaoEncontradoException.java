package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NaoEncontradoException extends RuntimeException{

    public NaoEncontradoException(String msg){
        super(msg);
    }
}
