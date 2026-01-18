package arun.arun.service;

import arun.arun.dto.CategoryDTO;
import arun.arun.entity.Category;
import arun.arun.mapper.CategoryMApper;
import arun.arun.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor

public class CategoryService {
    private CategoryRepository categoryRepository;

//    create category
    public CategoryDTO createCategory(CategoryDTO categoryDTO){
        Category category=CategoryMApper.toCategory(categoryDTO);
      category=  categoryRepository.save(category);
      return  CategoryMApper.toCategoryDTO(category);


    }
//    get all category

//    get category by id

//    delete category

}
