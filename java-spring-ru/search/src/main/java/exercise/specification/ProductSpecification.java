package exercise.specification;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;

// BEGIN
@Component
public class ProductSpecification {

    private Specification<Product> inCategory(Long categoryId){
        return (root, query, cb) -> categoryId == null ? cb.conjunction() : cb.equal(root.get("category").get("id"), categoryId);
    }
    private Specification<Product> priceLt(Integer price){
        return (root, query, cb) -> price == null ? cb.conjunction() : cb.lessThan(root.get("price"), price);
    }
    private Specification<Product> priceGt(Integer price){
        return (root, query, cb) -> price == null ? cb.conjunction() : cb.greaterThan(root.get("price"), price);
    }
    private Specification<Product> ratingGt(Double rating){
        return (root, query, cb) -> rating == null ? cb.conjunction() : cb.greaterThan(root.get("rating"), rating);
    }
    private Specification<Product> titleCont(String titleCont){
        return (root, query, cb) -> titleCont == null ? cb.conjunction() : cb.like(root.get("title"), titleCont+"+"); //+/gmi"
    }

    public Specification<Product> build(ProductParamsDTO dto){
        return inCategory(dto.getCategoryId())
                .and(priceLt(dto.getPriceLt()))
                .and(priceGt(dto.getPriceGt()))
                .and(ratingGt(dto.getRatingGt()))
                .and(titleCont(dto.getTitleCont()));
    }
}
// END
