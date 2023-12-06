package com.JavaaAssessment.SeptDecAssessment.Controllers;

import com.JavaaAssessment.SeptDecAssessment.Models.Customer;
import com.JavaaAssessment.SeptDecAssessment.Models.FoodProduct;
import com.JavaaAssessment.SeptDecAssessment.Repository.Customer.CustomerRepository;
import com.JavaaAssessment.SeptDecAssessment.Repository.FoodProduct.FoodProductRepository;
import com.JavaaAssessment.SeptDecAssessment.Services.FoodProductDao;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class CliController {

    private final FoodProductDao productService;
    private final FoodProductRepository foodProductRepository;
    private final CustomerRepository customerRepository;

    public CliController(FoodProductDao productService, FoodProductRepository foodProductRepository, CustomerRepository customerRepository) {
        this.productService = productService;
        this.foodProductRepository = foodProductRepository;
        this.customerRepository = customerRepository;
    }

    public int showMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------------------------------------------------");
        System.out.println("\t\t The Food Store");
        System.out.println("Choose from the following options: \n");
        System.out.println("------------------------------------------------------\n");
        System.out.println("[1] List all products");
        System.out.println("[2] Search for product by ID");
        System.out.println("[3] Add a new product");
        System.out.println("[4] Update product by ID");
        System.out.println("[5] Delete product by ID");
        System.out.println("[6] Exit");
        return scanner.nextInt();
    }

    public void createProduct(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Let's create a product!");
        System.out.println("First of all, I'd like to know your business name.");
        System.out.println("Business name: ");
        String businessName = scanner.nextLine();
        Customer business = customerRepository.findByBusinessName(businessName).orElseThrow(
                () -> new IllegalStateException("Oops, you might have entered a wrong business name. Lets try again.")
        );
        System.out.println("Give me all the data and I'll create the product.");
        System.out.println("Descriprion: ");
        String description = scanner.nextLine();
        System.out.println("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("SKU: ");
        String SKU = scanner.nextLine();
        System.out.println("Category: ");
        String category = scanner.nextLine();

        //TODO: find business/customer by business name

        boolean persisted = productService.addFoodProduct(new FoodProduct(SKU, category, description, price, business));
        if (persisted) {
            System.out.println("Product created!");
        } else {
            System.out.println("Product not created");
        }
        scanner.nextLine();
    }

    public void showAll() {
            System.out.println("Let's list all Food products!");
            List<FoodProduct> products = productService.findAllProducts();
            products.forEach(System.out::println);
            new Scanner(System.in).nextLine();
    }

    public void showOne() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Let's see a product!");
        System.out.println("Give me an id of any product and I'll show it");
        int id = scanner.nextInt();
        FoodProduct product = productService.findProduct(id);
        scanner.nextLine(); //consume \n
        if(product != null) {
            System.out.println(product);
        }else {
            System.out.println("Product not found");
        }
        scanner.nextLine();
    }

    public void update() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Let's update a product!");
        System.out.println("Give me all the data to update the product.");
        System.out.println("Id of product: ");
        int id = scanner.nextInt();
        scanner.nextLine(); //consume \n
        FoodProduct product = productService.findProduct(id);
        if(product != null) {
            System.out.print("Description["+product.getDescription()+"]:");
            String Description = scanner.nextLine();
            System.out.print("Price["+product.getPrice()+"]:");
            String price = scanner.nextLine();
            System.out.print("SKU["+product.getSKU()+"]:");
            String SKU = scanner.nextLine();
            if(!Description.isEmpty()){
                product.setDescription(Description);
            }
            if(!SKU.isEmpty()){
                product.setSKU(SKU);
            }
            if(tryParseDouble(price)){
                product.setPrice(Double.parseDouble(price));
            }
            foodProductRepository.save(product);
                System.out.println("product updated!");

        }else {
            System.out.println("Product not found");
        }
        scanner.nextLine();
    }

    public void delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Let's delete a product!");
        System.out.println("Give me an id of any product and I'll delete it");
        int id = scanner.nextInt();
        boolean result = productService.deleteFoodProduct(id);
        scanner.nextLine(); //consume \n
        if(result) {
            System.out.println("Product deleted!");
        }else {
            System.out.println("Product not found");
        }
        scanner.nextLine();
    }

    public boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean tryParseDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
