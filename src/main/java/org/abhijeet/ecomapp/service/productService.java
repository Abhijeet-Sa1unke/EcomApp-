package org.abhijeet.ecomapp.service;

import org.abhijeet.ecomapp.exception.ProductNotFoundException;
import org.abhijeet.ecomapp.model.Product;
import org.abhijeet.ecomapp.model.ProductEntity;
import org.abhijeet.ecomapp.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class productService {

    @Autowired
    private ProductRepo repo;


    public List<Product> getProducts() {
        List<ProductEntity> pro = new ArrayList<>(repo.findAll());
        List<Product> res = new ArrayList<>();
        for(ProductEntity p : pro){
            res.add(new Product(p.getId(),p.getName(),p.getDescription(),
                    p.getBrand(),p.getPrice(),p.getCategory(),p.getReleaseDate(),
                    p.getProductAvailable(),p.getStockQuantity(), p.getImageName(),
                    p.getImageType(), p.getImageData()));
        }

        return res;
    }

    public Product getProduct(int id) {
        ProductEntity p = repo.findById(id).orElseThrow(() ->
                new ProductNotFoundException("Product not found with ID : " + id));


        return new Product(p.getId(),p.getName(),p.getDescription(),
                p.getBrand(),p.getPrice(),p.getCategory(),p.getReleaseDate(),
                p.getProductAvailable(),p.getStockQuantity(), p.getImageName(),
                p.getImageType(), p.getImageData());
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {

        ProductEntity pro = new ProductEntity();
        pro.setName(product.getName());
        pro.setBrand(product.getBrand());
        pro.setDescription(product.getDescription());
        pro.setCategory(product.getCategory());
        pro.setPrice(product.getPrice());
        pro.setProductAvailable(product.getProductAvailable());
        pro.setReleaseDate(product.getReleaseDate());
        pro.setStockQuantity(product.getStockQuantity());

        if(imageFile != null && !imageFile.isEmpty()) {
            pro.setImageData(imageFile.getBytes());
            pro.setImageName(imageFile.getOriginalFilename());
            pro.setImageType(imageFile.getContentType());
        }

        ProductEntity res = repo.save(pro);

        return new Product(res.getId(),res.getName(),res.getDescription(),
                res.getBrand(),res.getPrice(),res.getCategory(),res.getReleaseDate(),
                res.getProductAvailable(),res.getStockQuantity() , res.getImageName(),
                res.getImageType(), res.getImageData());


    }

    public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException {
        ProductEntity pro = repo.findById(id).orElseThrow(() ->
                new ProductNotFoundException("Product not found with ID : " + id));
        pro.setName(product.getName());
        pro.setBrand(product.getBrand());
        pro.setDescription(product.getDescription());
        pro.setCategory(product.getCategory());
        pro.setPrice(product.getPrice());
        pro.setProductAvailable(product.getProductAvailable());
        pro.setReleaseDate(product.getReleaseDate());
        pro.setStockQuantity(product.getStockQuantity());

        if(imageFile != null && !imageFile.isEmpty()) {
            pro.setImageData(imageFile.getBytes());
            pro.setImageName(imageFile.getOriginalFilename());
            pro.setImageType(imageFile.getContentType());
        }

        ProductEntity res = repo.save(pro);

        return new Product(res.getId(),res.getName(),res.getDescription(),
                res.getBrand(),res.getPrice(),res.getCategory(),res.getReleaseDate(),
                res.getProductAvailable(),res.getStockQuantity() , res.getImageName(),
                res.getImageType(), res.getImageData());
    }

    public void deleteProduct(int id) {
        repo.findById(id).orElseThrow(() ->
                new ProductNotFoundException("Product not found with ID : " + id));
        repo.deleteById(id);
    }

    public List<Product> searchProduct(String keyword) {
        List<ProductEntity> p = repo.searchProduct(keyword);
        List<Product> result = new ArrayList<>();
        for(ProductEntity res : p){
            result.add(new Product(res.getId(),res.getName(),res.getDescription(),
                    res.getBrand(),res.getPrice(),res.getCategory(),res.getReleaseDate(),
                    res.getProductAvailable(),res.getStockQuantity() , res.getImageName(),
                    res.getImageType(), res.getImageData()));
        }
        return result;
    }
}
