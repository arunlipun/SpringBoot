package arun.arun.controller;

import arun.arun.dto.ProductDTO;
import arun.arun.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;
//    getAll Product
    @GetMapping
    public List<ProductDTO> getProducts(){
        return productService.getAllProducts();
    }
//    createProduct
    @PostMapping
    public ResponseEntity<ProductDTO >cerateProduct(@RequestBody ProductDTO productDTO) {
      ProductDTO createdProduct=  productService.cerateProduct(productDTO);
      return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }
//    update product
    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return productService.updateProduct(id, productDTO);
    }

//    get product by Ids
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO>getProductById(@PathVariable Long id){
        ProductDTO productDTO = productService.getProductById(id);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }
 //delete
    @DeleteMapping("/{id}")
 public String  deleteProduct(@PathVariable Long id){
       return productService.deleteProduct(id);
 }


}
