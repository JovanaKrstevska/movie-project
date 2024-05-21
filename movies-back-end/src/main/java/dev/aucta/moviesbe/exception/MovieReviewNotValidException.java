package dev.aucta.moviesbe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class MovieReviewNotValidException extends RuntimeException{

    public MovieReviewNotValidException(Long id){
        super(String.format("Should not be blank"));
    }
}
