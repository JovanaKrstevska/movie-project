package dev.aucta.moviesbe.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieCreationRequestDto {

    private String title;
    private String description;
    private String genre;
    private Integer year;

}
