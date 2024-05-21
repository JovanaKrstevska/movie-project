package dev.aucta.moviesbe.controller.dto;


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
public class MovieReviewResponseDto {

    private Long id;
    private String title;
    private String description;
    private Double averageRating;
    private List<Review> reviews;
}
