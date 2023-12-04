package com.JavaaAssessment.SeptDecAssessment.Controllers;

import com.JavaaAssessment.SeptDecAssessment.Models.Customer;
import com.JavaaAssessment.SeptDecAssessment.Models.FoodProduct;
import com.JavaaAssessment.SeptDecAssessment.Repository.Customer.CustomerRepository;
import com.JavaaAssessment.SeptDecAssessment.Services.FoodProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class FoodProductController {

    private final FoodProductDao productService;
    private final CustomerRepository customerRepository;

    @Autowired
    public FoodProductController(FoodProductDao productService, CustomerRepository customerRepository) {
        this.productService = productService;
        this.customerRepository = customerRepository;
    }

    @PostMapping(path = "/add")
    public boolean addProduct(@RequestBody FoodProduct product) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer = customerRepository.findByEmail(email).orElseThrow(
                () -> new IllegalStateException("User not found")
        );
        product.setCustomer(customer);
        return productService.addFoodProduct(product);
    }

    @DeleteMapping(path = "/delete/{id}")
    public boolean deleteProduct(@PathVariable("id") int id) {
        return productService.deleteFoodProduct(id);
    }

    @PutMapping(path = "/update/{id}")
    public boolean updateProduct(@PathVariable("id") int id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) int price,
            @RequestParam(required = false) int quantity) {
        return productService.updateFoodProduct(id, name, description, price, quantity);
    }

    @GetMapping(path = "/")
    public List<FoodProduct> getProducts() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer = customerRepository.findByEmail(email).orElseThrow(
                () -> new IllegalStateException("User not found")
        );
        return productService.findAllProductsByCustomer(customer);
    }

    @GetMapping(path = "/{id}")
    public FoodProduct getProductById(@PathVariable("id") int id) {
        return productService.findProduct(id);
    }

    @GetMapping(path = "/{category}")
    public List<FoodProduct> getProductByCategory(@PathVariable("category") String category) {
        return productService.findProductByCategory(category);
    }
}
