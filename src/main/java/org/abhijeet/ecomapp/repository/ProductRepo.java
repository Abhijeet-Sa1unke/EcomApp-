package org.abhijeet.ecomapp.repository;

import org.abhijeet.ecomapp.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity , Integer> {

}
