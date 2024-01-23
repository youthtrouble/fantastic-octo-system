package com.JavaaAssessment.SeptDecAssessment.Models;

import jakarta.persistence.*;

/**
 * Represents the food product in the product application.
 * This class contains information about the food product's SKU, category, description, and price.
 * <p>
 *     The {@code FoodProduct} class provides methods to access and modify the food product's SKU, category, description, and price.
 *     It also includes annotations for database mapping and table configuration.
 *     </p>
 *
 *         The food product's information includes:
 *         <ul>
 *             <li>SKU</li>
 *             <li>Category</li>
 *             <li>Description</li>
 *             <li>Price</li>
 *             </ul>
 *
 *             <p>
 *                 The {@code FoodProduct} class is annotated with {@code @Entity} to indicate that it is a JPA entity.
 *                 It is also annotated with {@code @Table} to specify the name of the database table for this entity.
 *                 </p>
 *                 <p>
 *                     The class includes getters and setters for all the properties, as well as constructors for creating instances of the class.
 *                     </p>
 *                     <p>
 *
 */
@Entity
@Table(name = "foodProducts")
public class FoodProduct extends BaseEntity{
    @Id
    @SequenceGenerator(name = "foodProduct_sequence", sequenceName = "foodProduct_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "foodProduct_sequence")
    private Long id;
    private String SKU;
    private String category;
    private String description;
    private double price;

    public FoodProduct() {

    }

    public FoodProduct(String name, String category, String description, double price) {
        this.SKU = name;
        this.category = category;
        this.description = description;
        this.price = price;
    }

    public FoodProduct(Long id, String name, String category, String description, double price) {
        this.id = id;
        this.SKU = name;
        this.category = category;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getSKU() {
        return SKU;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSKU(String name) {
        this.SKU = name;
    }

    public void setCategory(String description) {
        this.category = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", SKU='" + SKU + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }
}
