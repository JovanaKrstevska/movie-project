package dev.aucta.moviesbe.specification;

import dev.aucta.moviesbe.filter.MovieFilter;
import dev.aucta.moviesbe.model.Movie;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MovieSpecification extends EntitySpecification<Movie> {

    private final MovieFilter movieFilter;

    @Override
    public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.and();
        query.distinct(true);

        String tittleFilter = this.movieFilter.getTitle();
        Integer yearFromFilter = this.movieFilter.getYearFrom();
        Integer yearToFilter = this.movieFilter.getYearTo();
//        Integer averageRatingFilter = this.movieFilter.getAverageRating();
        if(tittleFilter!=null){
            predicate = criteriaBuilder.and(predicate, this.predicateStringFilter(root.get("title"), criteriaBuilder, tittleFilter));
        }
        if(yearFromFilter!=null){
            predicate = criteriaBuilder.and(predicate, this.predicateYearRangeFilter(root.get("year"), criteriaBuilder, yearFromFilter, yearToFilter));
        }
//        if(averageRatingFilter!=null){
//            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("rating"), averageRatingFilter));
//        }
        return predicate;
    }
}
