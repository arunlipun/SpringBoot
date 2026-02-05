package arun.arun.service;

import arun.arun.dto.ProductDTO;
import arun.arun.entity.Category;
import arun.arun.entity.Product;
import arun.arun.exception.CategoryNotFoundException;
import arun.arun.mapper.ProductMapper;
import arun.arun.repository.CategoryRepository;
import arun.arun.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    public ProductDTO cerateProduct(ProductDTO productDTO) {
        if (productDTO.getCategoryId() == null) {
            throw new RuntimeException("Category ID must not be null");
        }

        Category category=categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(()-> new CategoryNotFoundException("Category"+ productDTO.getCategoryId()+ " Not found"));
        Product product = ProductMapper.toProductEntity(productDTO,category);
        product=productRepository.save(product);
        return ProductMapper.toProductDTO(product);
    }
    //get all products
    public List<ProductDTO>getAllProducts(){
      return productRepository.findAll().stream().map(ProductMapper::toProductDTO).toList();
    }
    //get product by id

    public ProductDTO getProductById(Long id){
        Product product=productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product Not found"));
        return ProductMapper.toProductDTO(product);
    }

    //update
    public ProductDTO updateProduct(Long id ,ProductDTO productDTO) {
        Product product=productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product Not found"));
      Category category=  categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(()-> new RuntimeException("Category Not found"));
      product.setName(productDTO.getName());
      product.setDescription(productDTO.getDescription());
      product.setPrice(productDTO.getPrice());
      product.setCategory(category);
      productRepository.save(product);
      return ProductMapper.toProductDTO(product);
    }
    //delete
    public String deleteProduct(Long id){
        productRepository.deleteById(id);
        return "Product"+ id +" deleted successfully";
    }

}
