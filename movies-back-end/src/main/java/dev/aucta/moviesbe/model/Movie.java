package dev.aucta.moviesbe.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String genre;
    private Integer year;
//    private Integer ratingVotes;
//    private Double averageRating;
    @OneToMany
    private List<Rating> ratings;

    @OneToMany
    private List<Review> reviews;

    public Movie(String title, String description, String genre, Integer year) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.year = year;
//        this.ratingVotes = 0;
//        this.averageRating = 0.0;
        this.ratings = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }
}
