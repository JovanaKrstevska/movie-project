package dev.aucta.moviesbe.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public abstract class EntitySpecification<T> implements Specification<T> {
    protected Predicate predicateYearRangeFilter(Path path, CriteriaBuilder criteriaBuilder, Integer yearFrom, Integer yearTo){
        if(yearTo == null){
            Predicate predicate = criteriaBuilder.equal(path, yearFrom);
            return predicate;
        } else {
            Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(path, yearFrom);
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(path, yearTo));
            return predicate;
        }
    }
    protected Predicate predicateStringFilter(Path path, CriteriaBuilder criteriaBuilder, String title){
        return criteriaBuilder.like(criteriaBuilder.lower(path), "%" + title.toLowerCase() + "%");
    }
}
