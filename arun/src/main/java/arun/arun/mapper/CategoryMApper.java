package arun.arun.mapper;

import arun.arun.dto.CategoryDTO;
import arun.arun.entity.Category;

public class CategoryMApper {
    public static CategoryDTO toCategoryDTO(Category category){
       if(category==null){
           return null;
       }
       CategoryDTO categoryDTO=new CategoryDTO();
       categoryDTO.setId(category.getId());
       categoryDTO.setName(category.getName());
       categoryDTO.setProducts(category.getProducts().stream().map(ProductMapper::toProductDTO).toList());
       return categoryDTO;
    }
    public static Category toCategoryEntity(CategoryDTO categoryDTO){
        Category category=new Category();
        category.setName(categoryDTO.getName());
        return category;
    }
}
