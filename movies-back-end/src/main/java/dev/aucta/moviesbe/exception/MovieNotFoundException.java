package dev.aucta.moviesbe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MovieNotFoundException extends RuntimeException{
    public MovieNotFoundException(Long id){
        super(String.format("Movie with id: %d is not found", id));
    }
}
