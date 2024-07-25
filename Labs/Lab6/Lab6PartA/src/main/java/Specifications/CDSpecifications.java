package Specifications;

import org.springframework.data.jpa.domain.Specification;

import domain.CD;

public class CDSpecifications {

    public static Specification<CD> getCDsWithArtist(String artist)
    {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("artist"), artist);
    }

    public static Specification<CD> getCDsWithPriceBiggerThan(double price)
    {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("price"), price);
    }
}
