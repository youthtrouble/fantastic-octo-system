package com.JavaaAssessment.SeptDecAssessment.Repository.FoodProduct;

import com.JavaaAssessment.SeptDecAssessment.Models.FoodProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FoodProductRepository
        extends JpaRepository<FoodProduct, UUID> {

    @Query("SELECT p FROM FoodProduct p WHERE p.id = ?1")
    Optional<FoodProduct> findProductById(int id);

    @Query("SELECT p FROM FoodProduct p WHERE p.customer.customerId = ?1")
    List<FoodProduct> findProductsByCustomerID(Long customerID);

    @Query("DELETE FROM FoodProduct p WHERE p.id = ?1")
    Optional deleteFoodProductById(int id);

    @Query("SELECT p FROM FoodProduct p WHERE p.category = ?1")
    List<FoodProduct> findProductByCategory(String category);
}
