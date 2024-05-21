package dev.aucta.moviesbe.service.impl;

import dev.aucta.moviesbe.exception.MovieNotFoundException;
import dev.aucta.moviesbe.exception.MovieRatingNotValidException;
import dev.aucta.moviesbe.exception.MovieReviewNotValidException;
import dev.aucta.moviesbe.model.Movie;
import dev.aucta.moviesbe.model.Rating;
import dev.aucta.moviesbe.model.Review;
import dev.aucta.moviesbe.repository.MovieRepository;
import dev.aucta.moviesbe.repository.RatingRepository;
import dev.aucta.moviesbe.repository.ReviewRepository;
import dev.aucta.moviesbe.service.MovieService;
import dev.aucta.moviesbe.specification.MovieSpecification;
import dev.aucta.moviesbe.specification.ReviewSpecification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final RatingRepository ratingRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> filterByMovieSpecification(MovieSpecification movieSpecification) {
        return movieRepository.findAll(movieSpecification);
    }

    @Override
    public Movie rateMovie(Long id, int ratingValue) {
        if (ratingValue > 10 || ratingValue < 1) {
            throw new MovieRatingNotValidException();
        }
        Movie movie = this.movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
        List<Rating> ratings = movie.getRatings();
        Rating rating = new Rating();
        rating.setNumber(ratingValue);
        this.ratingRepository.save(rating);
        ratings.add(rating);
        movie.setRatings(ratings);
        return movieRepository.save(movie);
    }

    @Override
    public Movie reviewMovie(Long id, String reviewValue) {
        if (reviewValue == null) {
            throw new MovieReviewNotValidException(id);
        }
        Movie movie = this.movieRepository.findById(id).orElseThrow(() -> new MovieReviewNotValidException(id));
        List<Review> reviews = movie.getReviews();
        Review review = new Review();
        review.setReview(reviewValue);
        this.reviewRepository.save(review);
        reviews.add(review);
        movie.setReviews(reviews);
        return movieRepository.save(movie);
    }

    @Override
    public Movie getMovie(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));

    }

    @Override
    public List<Movie> filterByReviewSpecification(ReviewSpecification reviewSpecification) {

        return this.movieRepository.findAll(reviewSpecification);
    }
}
