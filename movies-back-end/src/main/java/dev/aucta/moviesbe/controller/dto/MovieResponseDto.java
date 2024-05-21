package dev.aucta.moviesbe.controller.dto;


import dev.aucta.moviesbe.model.Rating;
import dev.aucta.moviesbe.model.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponseDto {

    private Long id;
    private String title;
    private String description;
    private String genre;
    private int year;
    private List<Rating> ratings;
    private List<Review> reviews;
}
