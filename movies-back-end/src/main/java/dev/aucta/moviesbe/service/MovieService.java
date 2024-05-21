package dev.aucta.moviesbe.service;

import dev.aucta.moviesbe.model.Movie;
import dev.aucta.moviesbe.specification.MovieSpecification;
import dev.aucta.moviesbe.specification.ReviewSpecification;

import java.util.List;

public interface MovieService {
    Movie createMovie(Movie movie);
    List<Movie> filterByMovieSpecification(MovieSpecification movieSpecification);
    Movie rateMovie(Long id, int rating);
    Movie reviewMovie(Long id, String review);
    Movie getMovie(Long id);
    List<Movie> filterByReviewSpecification(ReviewSpecification reviewSpecification);
}
