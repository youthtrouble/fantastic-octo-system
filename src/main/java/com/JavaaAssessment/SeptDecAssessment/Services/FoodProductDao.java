package com.JavaaAssessment.SeptDecAssessment.Services;

import com.JavaaAssessment.SeptDecAssessment.Models.Customer;
import com.JavaaAssessment.SeptDecAssessment.Models.FoodProduct;
import com.JavaaAssessment.SeptDecAssessment.Repository.FoodProduct.FoodProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodProductDao {
    private final FoodProductRepository foodProductRepository;

    @Autowired
    public FoodProductDao(FoodProductRepository productRepository) {
        this.foodProductRepository = productRepository;
    }

    public boolean addFoodProduct(FoodProduct product) {
        foodProductRepository.save(product);
        return true;
    }

    public boolean deleteFoodProduct(int id) {
        Optional<FoodProduct> productOptional = foodProductRepository
                .findProductById(id);
        if (productOptional.isPresent()) {
            return true;
        }
        ;
        return false;
    }

    @Transactional
    public boolean updateFoodProduct(
            int id,
            String SKU,
            String description,
            double price,
            int quantity) {

        FoodProduct product = foodProductRepository.findProductById(id)
                .orElseThrow(
                        () -> new IllegalStateException("Food Product with id " + id + " does not exist")
                );
        if (SKU != null && !SKU.isEmpty() && !SKU.equals(product.getSKU())) {
            product.setSKU(SKU);
        }
        if (description != null && !description.isEmpty() && !description.equals(product.getCategory())) {
            product.setCategory(description);
        }
        if (price > 0 && price != product.getPrice()) {
            product.setPrice(price);
        }
        foodProductRepository.save(product);
        return true;
    }

    public List<FoodProduct> findAllProducts() {
        return foodProductRepository.findAll();
    }

    public List<FoodProduct> findAllProductsByCustomer(Customer customer) {
        return foodProductRepository.findProductsByCustomerID(customer.getCustomerId());
    }

    public FoodProduct findProduct(int id) {
        return foodProductRepository.findProductById(id)
                .orElseThrow(() -> new IllegalStateException("Food Product with id " + id + " does not exist"));
    }

    public List<FoodProduct> findProductByCategory(String category) {
        return foodProductRepository.findProductByCategory(category);
    }
}
