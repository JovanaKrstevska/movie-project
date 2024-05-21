package dev.aucta.moviesbe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class MovieRatingNotValidException extends RuntimeException{

    public MovieRatingNotValidException(){
        super(String.format("The rating must be in range between 1 to 10"));
    }
}
