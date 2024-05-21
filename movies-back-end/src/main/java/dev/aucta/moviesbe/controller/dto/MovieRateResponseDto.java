package dev.aucta.moviesbe.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieRateResponseDto {

    private Long id;
    private String title;
    private String description;
    private String genre;
    private int year;
    private Double averageRating;
}
