package com.JavaaAssessment.SeptDecAssessment.Repository.FoodProduct;

import com.JavaaAssessment.SeptDecAssessment.Models.FoodProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository class for FoodProduct
 * This class is used to perform CRUD operations on the FoodProduct table
 * <p>
 * This interface extends the JpaRepository interface, which provides generic
 * CRUD operations and advanced querying capabilities for the FoodProduct entity.
 * It is annotated with the {@link org.springframework.data.jpa.repository.JpaRepository}
 * annotation to indicate that it is a repository interface.
 * </p>
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see com.JavaaAssessment.SeptDecAssessment.Models.FoodProduct
 */
@Repository
public interface FoodProductRepository
        extends JpaRepository<FoodProduct, UUID> {

    /**
     * This method is used to find a food product by id
     * @param id The id of the food product
     * @return FoodProduct
     */
    @Query("SELECT p FROM FoodProduct p WHERE p.id = ?1")
    Optional<FoodProduct> findProductById(Long id);

    /**
     * This method is used to find a food product by category
     * @param category The category of the food product
     * @return FoodProduct
     */
    @Query("SELECT p FROM FoodProduct p WHERE p.category = ?1")
    List<FoodProduct> findProductByCategory(String category);
}
