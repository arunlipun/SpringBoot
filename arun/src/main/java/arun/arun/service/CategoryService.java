package arun.arun.service;

import arun.arun.dto.CategoryDTO;
import arun.arun.entity.Category;
import arun.arun.exception.CategoryAlreadyExistsException;
import arun.arun.mapper.CategoryMApper;
import arun.arun.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor

public class CategoryService {
    private CategoryRepository categoryRepository;

//    create category
    public CategoryDTO createCategory(CategoryDTO categoryDTO){
    Optional<Category> categoryOptional=    categoryRepository.findByName(categoryDTO.getName());
    if(categoryOptional.isPresent()){
        throw new CategoryAlreadyExistsException("Category "+  categoryDTO.getName() + "already exists");
    }
        Category category=CategoryMApper.toCategoryEntity(categoryDTO);
      category=  categoryRepository.save(category);
      return  CategoryMApper.toCategoryDTO(category);


    }
//    get all category

    public List<CategoryDTO> getAllCategories(){
        return categoryRepository.findAll().stream().map(CategoryMApper::toCategoryDTO).toList();
    }

//    get category by id
    public CategoryDTO getCategoryById(Long Id){
       Category category= categoryRepository.findById(Id).orElseThrow(()->new RuntimeException("Category not found"));
       return CategoryMApper.toCategoryDTO(category);
    }

//    delete category
    public String deleteCategory(Long id){
        categoryRepository.deleteById(id);
        return "Category"+ id + "has been  deleted";
    }

}
