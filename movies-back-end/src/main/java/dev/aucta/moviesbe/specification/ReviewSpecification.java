package dev.aucta.moviesbe.specification;

import dev.aucta.moviesbe.filter.ReviewFilter;
import dev.aucta.moviesbe.model.Movie;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReviewSpecification extends EntitySpecification<Movie> {

    private final ReviewFilter reviewFilter;

    @Override
    public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.and();
        query.distinct(true);

        String contentFilter = this.reviewFilter.getContent();
        if(contentFilter!=null){
            predicate = criteriaBuilder.and(predicate, this.predicateStringFilter(root.join("reviews", JoinType.LEFT).get("review"), criteriaBuilder, contentFilter));
        }
        return predicate;
    }
}