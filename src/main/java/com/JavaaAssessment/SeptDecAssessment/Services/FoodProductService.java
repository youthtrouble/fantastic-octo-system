package com.JavaaAssessment.SeptDecAssessment.Services;

import com.JavaaAssessment.SeptDecAssessment.Models.FoodProduct;
import com.JavaaAssessment.SeptDecAssessment.Repository.FoodProduct.FoodProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The {@code FoodProductService} class provides services related to food product operations,
 * such as adding, deleting, updating, and retrieving food product information.
 *
 * @author Deji Ajibola
 * @version 1.0
 */
@Service
public class FoodProductService {

    private final FoodProductRepository foodProductRepository;

    /**
     * Constructs a new {@code FoodProductService} instance with the specified {@code FoodProductRepository}.
     *
     * @param productRepository The repository for managing food product data.
     */
    @Autowired
    public FoodProductService(FoodProductRepository productRepository) {
        this.foodProductRepository = productRepository;
    }

    /**
     * Adds a new food product to the repository.
     *
     * @param product The food product to be added.
     * @return {@code true} if the food product is successfully added, {@code false} otherwise.
     */
    public boolean addFoodProduct(FoodProduct product) {
        foodProductRepository.save(product);
        return true;
    }

    /**
     * Deletes a food product with the specified ID from the repository.
     *
     * @param id The ID of the food product to be deleted.
     * @return {@code true} if the food product is successfully deleted, {@code false} otherwise.
     */
    public boolean deleteFoodProduct(Long id) {
        Optional<FoodProduct> productOptional = foodProductRepository.findProductById(id);
        if (productOptional.isPresent()) {
            foodProductRepository.delete(productOptional.get());
            return true;
        }
        return false;
    }

    /**
     * Updates the information of an existing food product in the repository.
     *
     * @param id          The ID of the food product to be updated.
     * @param SKU         The new SKU (null or empty to keep existing value).
     * @param description The new description (null or empty to keep existing value).
     * @param price       The new price (greater than 0 to update, 0 to keep existing value).
     * @return {@code true} if the food product is successfully updated, {@code false} otherwise.
     */
    @Transactional
    public boolean updateFoodProduct(Long id, String SKU, String description, double price) {
        // Retrieve the food product from the repository or throw an exception if not found
        Optional<FoodProduct> product = foodProductRepository.findProductById(id);

        if (product.isPresent()) {
            System.out.println("product found = " + product);
            // Update food product information if new values are provided
            if (SKU != null && !SKU.isEmpty() && !SKU.equals(product.get().getSKU())) {
                product.get().setSKU(SKU);
            }

            if (description != null && !description.isEmpty() && !description.equals(product.get().getCategory())) {
                product.get().setCategory(description);
            }

            if (price > 0 && price != product.get().getPrice()) {
                product.get().setPrice(price);
            }

//            // Save the updated food product back to the repository
//            FoodProduct Foodt = foodProductRepository.save(product.get());
//            System.out.println("Foodt saved = " + Foodt);
            return true;
        }

        return false;
    }

    /**
     * Retrieves a list of all food products from the repository.
     *
     * @return A list of all food products.
     */
    public List<FoodProduct> findAllProducts() {
        return foodProductRepository.findAll();
    }

    /**
     * Retrieves a food product with the specified ID from the repository.
     *
     * @param id The ID of the food product to be retrieved.
     * @return The food product with the specified ID.
     * @throws IllegalStateException if the food product is not found.
     */
    public FoodProduct findProduct(Long id) {
        return foodProductRepository.findProductById(id)
                .orElseThrow(() -> new IllegalStateException("Food Product with id " + id + " does not exist"));
    }

    /**
     * Retrieves a list of food products with the specified category from the repository.
     *
     * @param category The category of the food products to be retrieved.
     * @return A list of food products with the specified category.
     */
    public List<FoodProduct> findProductByCategory(String category) {
        return foodProductRepository.findProductByCategory(category);
    }
}
