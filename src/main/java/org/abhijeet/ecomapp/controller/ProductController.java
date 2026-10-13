package org.abhijeet.ecomapp.controller;

import org.abhijeet.ecomapp.model.Product;
import org.abhijeet.ecomapp.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:5173")
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private productService service;

    @GetMapping("/products")
    public List<Product> getProducts(){
        return service.getProducts();
    }

    @GetMapping("product/{id}")
    public Product getProduct(@PathVariable int id){
        return service.getProduct(id);
    }

    @GetMapping("product/{id}/image")
    public byte[] getProductImage(@PathVariable int id){
        return service.getProduct(id).getImageData();
    }

    @PostMapping("product")
    public ResponseEntity<?> addProduct(@RequestPart Product product , @RequestPart MultipartFile imageFile) throws IOException {
        Product savedProduct = service.addProduct(product , imageFile);
        return new ResponseEntity<>(savedProduct , HttpStatus.CREATED);
    }

    @PutMapping("product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id , @RequestPart Product product , @RequestPart MultipartFile imageFile) throws IOException {
        Product updatedProduct = service.updateProduct(id , product , imageFile);
        return new ResponseEntity<>(updatedProduct , HttpStatus.OK);
    }

    @DeleteMapping("product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id){
        service.deleteProduct(id);
        return new ResponseEntity<>("Product Deleted" , HttpStatus.OK);
    }

    @GetMapping("products/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String keyword){
        List<Product> res = service.searchProduct(keyword);
        return new ResponseEntity<>(res , HttpStatus.OK);
    }
}
