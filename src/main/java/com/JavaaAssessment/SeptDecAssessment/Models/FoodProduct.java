package com.JavaaAssessment.SeptDecAssessment.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "foodProducts")
public class FoodProduct extends BaseEntity{
    @Id
    @SequenceGenerator(name = "foodProduct_sequence", sequenceName = "foodProduct_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "foodProduct_sequence")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private String SKU;
    private String category;
    private String description;
    private double price;

    public FoodProduct() {

    }

    public FoodProduct(String name, String category, String description, double price, Customer customer) {
        this.SKU = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.customer = customer;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
                ", name='" + SKU + '\'' +
                ", description='" + category + '\'' +
                ", price=" + price +
                '}';
    }
}
