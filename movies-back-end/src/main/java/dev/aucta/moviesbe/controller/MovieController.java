package dev.aucta.moviesbe.controller;


import dev.aucta.moviesbe.controller.dto.*;
import dev.aucta.moviesbe.filter.MovieFilter;
import dev.aucta.moviesbe.filter.ReviewFilter;
import dev.aucta.moviesbe.model.Movie;
import dev.aucta.moviesbe.model.Rating;
import dev.aucta.moviesbe.service.MovieService;
import dev.aucta.moviesbe.specification.MovieSpecification;
import dev.aucta.moviesbe.specification.ReviewSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Movie create(@RequestBody MovieCreationRequestDto movieCreationRequestDto) {
        return this.movieService.createMovie(new Movie(movieCreationRequestDto.getTitle(), movieCreationRequestDto.getDescription(), movieCreationRequestDto.getGenre(), movieCreationRequestDto.getYear()));
    }

    @PostMapping()
    public List<MovieRateResponseDto> filterMovies(@RequestBody MovieFilter movieFilter) {
//        return this.movieService.movieFilters(new MovieSpecification(movieFilter));
        List<Movie> filteredMovies = movieService.filterByMovieSpecification(new MovieSpecification(movieFilter));

        return filteredMovies.stream()
                .map(movie -> {
                    MovieRateResponseDto dto = new MovieRateResponseDto();
                    dto.setId(movie.getId());
                    dto.setTitle(movie.getTitle());
                    dto.setDescription(movie.getDescription());
                    dto.setGenre(movie.getGenre());
                    dto.setYear(movie.getYear());
                    dto.setAverageRating(movie.getRatings().stream().mapToInt(Rating::getNumber).average().orElse(0.0));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @PostMapping("/{id}/rate")
    public MovieRateResponseDto rateMovie(@PathVariable("id") Long id, @RequestBody RatingDto ratingDto) {
        Movie movie = this.movieService.rateMovie(id, ratingDto.getRating());
        return new MovieRateResponseDto(movie.getId(), movie.getTitle(), movie.getDescription(), movie.getGenre(), movie.getYear(), movie.getRatings().stream().mapToInt(Rating::getNumber).average().orElse(0.0));
    }

    @PostMapping("/{id}/review")
    public ResponseEntity<String> reviewMovie(@PathVariable("id") Long id, @RequestBody ReviewDto reviewDto) {
        Movie movie = this.movieService.reviewMovie(id, reviewDto.getReview());
        if(movie!=null){
            return ResponseEntity.status(HttpStatus.CREATED).body("Review added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add review");
        }
    }
    @GetMapping("/{id}")
    public MovieResponseDto getMovie(@PathVariable("id") Long id){
        Movie movie = this.movieService.getMovie(id);
        return new MovieResponseDto(movie.getId(), movie.getTitle(), movie.getDescription(), movie.getGenre(), movie.getYear(), movie.getRatings(), movie.getReviews());
    }
    @PostMapping("/reviews")
    public List<MovieReviewResponseDto> movieReviewFilter(@RequestBody ReviewFilter reviewFilter) {
        List<Movie> filteredMovies = movieService.filterByReviewSpecification(new ReviewSpecification(reviewFilter));

        return filteredMovies.stream()
                .map(movie -> {
                    MovieReviewResponseDto dto = new MovieReviewResponseDto();
                    dto.setId(movie.getId());
                    dto.setTitle(movie.getTitle());
                    dto.setDescription(movie.getDescription());
                    dto.setAverageRating(movie.getRatings().stream().mapToInt(Rating::getNumber).average().orElse(0.0));
                    dto.setReviews(filteredMovies.stream()
                            .flatMap(movie1 -> movie1.getReviews().stream())
                            .collect(Collectors.toList()));
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
