package arun.arun.mapper;

import arun.arun.dto.ProductDTO;
import arun.arun.entity.Category;
import arun.arun.entity.Product;

public class ProductMapper {
//  entity-dto  toProductDTO
    public static ProductDTO toProductDTO(Product product) {

        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),

                product.getCategory().getId()

                );
    }

    // dto-entity
    public static Product toProductEntity(ProductDTO productDTO, Category category) {
        Product product = new Product();
       product.setName(productDTO.getName());
       product.setDescription(productDTO.getDescription());
       product.setPrice(productDTO.getPrice());
       product.setCategory(category);
       return   product;
    }
}
