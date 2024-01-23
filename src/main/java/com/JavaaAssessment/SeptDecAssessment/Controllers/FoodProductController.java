package com.JavaaAssessment.SeptDecAssessment.Controllers;

import com.JavaaAssessment.SeptDecAssessment.Models.FoodProduct;
import com.JavaaAssessment.SeptDecAssessment.Services.FoodProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/products")
public class FoodProductController {

    private final FoodProductService productService;

    @Autowired
    public FoodProductController(FoodProductService productService) {
        this.productService = productService;
    }

    /**
     * Get all Food Products.
     *
     * @return A list of all FoodProduct.
     */
    @GetMapping(path = "/all")
    public List<FoodProduct> getAllProducts() {
        return productService.findAllProducts();
    }

    /**
     * Get all Food Products.
     *
     * @return A list of all FoodProduct.
     */
    @GetMapping(path = "/public/all")
    public List<FoodProduct> getAllProductsPublic() {
        return productService.findAllProducts();
    }

    /**
     * Create a new Food Product.
     *
     * @param product
     * @return The created FoodProduct object.
     */
    @PostMapping(path = "/add")
    public ResponseEntity<FoodProduct> addProduct(@RequestBody FoodProduct product) {
       try {
           productService.addFoodProduct(product);
              return ResponseEntity.created(null).body(product);
       } catch (Exception e) {
           System.out.println("e = " + e);
              return ResponseEntity.badRequest().build();
       }
    }

    /**
     * Delete a Food Product.
     *
     * @param id The id of the FoodProduct to be deleted.
     * @return True if the FoodProduct was deleted, false otherwise.
     */
    @DeleteMapping(path = "/delete/{id}")
    public boolean deleteProduct(@PathVariable("id") Long id) {
        return productService.deleteFoodProduct(id);
    }

    /**
     * Update a Food Product.
     *
     * @param productID The id of the FoodProduct to be updated.
     * @param product the product body
     * @return True if the FoodProduct was updated, false otherwise.
     */
    @PutMapping(path = "/update/{id}")
    public boolean updateProduct(@PathVariable("id") Long productID, @RequestBody FoodProduct product) {
        System.out.println("id = " + productID);
        return productService.updateFoodProduct(productID, product.getSKU(), product.getDescription(), product.getPrice());
    }

    /**
     * Get a Food Product by id.
     *
     * @param id The id of the FoodProduct to be retrieved.
     * @return The FoodProduct object.
     */
    @GetMapping(path = "/{id}")
    public FoodProduct getProductById(@PathVariable("id") Long id) {
        return productService.findProduct(id);
    }

    /**
     * Get a Food Product by category.
     *
     * @param category The category of the FoodProduct to be retrieved.
     * @return The FoodProduct object.
     */
    @GetMapping(path = "/{category}")
    public List<FoodProduct> getProductByCategory(@PathVariable("category") String category) {
        return productService.findProductByCategory(category);
    }
}
