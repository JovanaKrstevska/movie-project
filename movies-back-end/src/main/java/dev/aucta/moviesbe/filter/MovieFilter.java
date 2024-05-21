package dev.aucta.moviesbe.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieFilter {

    private String title;
    private List<String> genres;
    private Integer yearFrom;
    private Integer yearTo;
    public Integer averageRating;
}
