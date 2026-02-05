package arun.arun.controller;

import arun.arun.dto.CategoryDTO;
import arun.arun.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor

public class CategoryController {
   private CategoryService categoryService;
//    get all categories
    @GetMapping
    public List<CategoryDTO> getAllCategories(){
             return   categoryService.getAllCategories();
    }
//Authentication
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    create categories
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){




        return new ResponseEntity<>( categoryService.createCategory(categoryDTO),HttpStatus.CREATED);
    }
//    get category by Id_
    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable long id){
           return categoryService.getCategoryById(id);
    }

//    delete Category
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable long id) {
        categoryService.deleteCategory(id);

    return "Category deleted successfully";}
}
