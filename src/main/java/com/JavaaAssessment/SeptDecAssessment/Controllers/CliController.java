package com.JavaaAssessment.SeptDecAssessment.Controllers;

import com.JavaaAssessment.SeptDecAssessment.Models.Address;
import com.JavaaAssessment.SeptDecAssessment.Models.Customer;
import com.JavaaAssessment.SeptDecAssessment.Models.FoodProduct;
import com.JavaaAssessment.SeptDecAssessment.Repository.Customer.CustomerRepository;
import com.JavaaAssessment.SeptDecAssessment.Repository.FoodProduct.FoodProductRepository;
import com.JavaaAssessment.SeptDecAssessment.Services.CustomerService;
import com.JavaaAssessment.SeptDecAssessment.Services.FoodProductService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class CliController {

    private final FoodProductService productService;
    private final FoodProductRepository foodProductRepository;
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;

    /**
     * Constructor.
     *
     * @param productService The product service.
     */
    public CliController(FoodProductService productService, FoodProductRepository foodProductRepository, CustomerRepository customerRepository, CustomerService customerService) {
        this.productService = productService;
        this.foodProductRepository = foodProductRepository;
        this.customerRepository = customerRepository;
        this.customerService = customerService;
    }

    /**
     * Show the menu and return the option selected.
     *
     * @return The option selected.
     */
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
        System.out.println("[6] List all customers");
        System.out.println("[7] Search for customer by ID");
        System.out.println("[8] Add a new customer");
        System.out.println("[9] Update customer by ID");
        System.out.println("[10] Delete customer by ID");
        System.out.println("[11] Exit");
        return scanner.nextInt();
    }

    /**
     * Create a new product from the command line interface.
     * This method will ask the user for all the data needed to create a new product.
     * It will then call the product service to create the product.
     * If the product is created successfully, it will print a success message.
     * If the product is not created successfully, it will print a failure message.
     * The method will then wait for the user to press enter before returning.
     */
    public void createProduct(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Let's create a product!");
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

        boolean persisted = productService.addFoodProduct(new FoodProduct(SKU, category, description, price));
        if (persisted) {
            System.out.println("Product created!");
        } else {
            System.out.println("Product not created");
        }
    }

    /**
     * Show all products from the command line interface.
     * This method will call the product service to get all products.
     * It will then print all the products to the console.
     * The method will then wait for the user to press enter before returning.
     */
    public void showAll() {
            System.out.println("Let's list all Food products!");
            List<FoodProduct> products = productService.findAllProducts();
            products.forEach(System.out::println);
            new Scanner(System.in).nextLine();
    }

    /**
     * Show one product from the command line interface.
     * This method will ask the user for the id of the product to show.
     * It will then call the product service to get the product.
     * If the product is found, it will print the product to the console.
     * If the product is not found, it will print a failure message.
     * The method will then wait for the user to press enter before returning.
     */
    public void showOne() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Let's see a product!");
        System.out.println("Give me an id of any product and I'll show it");
        Long id = scanner.nextLong();
        FoodProduct product = productService.findProduct(id);
        scanner.nextLine(); //consume \n
        if(product != null) {
            System.out.println(product);
        }else {
            System.out.println("Product not found");
        }
        scanner.nextLine();
    }

    /**
     * Update a product from the command line interface.
     * This method will ask the user for the id of the product to update.
     * It will then call the product service to get the product.
     * If the product is found, it will ask the user for the new data.
     * It will then call the product service to update the product.
     * If the product is updated successfully, it will print a success message.
     * If the product is not updated successfully, it will print a failure message.
     * The method will then wait for the user to press enter before returning.
     */
    public void update() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Let's update a product!");
        System.out.println("Give me all the data to update the product.");
        System.out.println("Id of product: ");
        Long id = scanner.nextLong();
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

    /**
     * Delete a product from the command line interface.
     * This method will ask the user for the id of the product to delete.
     * It will then call the product service to get the product.
     * If the product is found, it will delete the product.
     * If the product is deleted successfully, it will print a success message.
     * If the product is not deleted successfully, it will print a failure message.
     * The method will then wait for the user to press enter before returning.
     */
    public void delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Let's delete a product!");
        System.out.println("Give me an id of any product and I'll delete it");
        Long id = scanner.nextLong();
        boolean result = productService.deleteFoodProduct(id);
        scanner.nextLine(); //consume \n
        if(result) {
            System.out.println("Product deleted!");
        }else {
            System.out.println("Product not found");
        }
        scanner.nextLine();
    }

    /**
     * Create a new customer from the command line interface.
     * This method will ask the user for all the data needed to create a new customer.
     * It will then call the customer service to create the customer.
     * If the customer is created successfully, it will print a success message.
     * If the customer is not created successfully, it will print a failure message.
     * The method will then wait for the user to press enter before returning.
     */
    public void createCustomer(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Let's create a Customer!");
        System.out.println("Give me all the data and I'll create the Customer.");
        System.out.println("Business Name: ");
        String businessName = scanner.nextLine();
        System.out.println("Country: ");
        String country = scanner.nextLine();
        System.out.println("Address Line 1: ");
        String addressLine1 = scanner.nextLine();
        System.out.println("Address Line 2: ");
        String addressLine2 = scanner.nextLine();
        System.out.println("Address Line 3: ");
        String addressLine3 = scanner.nextLine();
        System.out.println("Post Code: ");
        String postCode = scanner.nextLine();
        System.out.println("Business Phone Number: ");
        String businessPhoneNumber = scanner.nextLine();

        customerService.addCustomer(new Customer(businessName, businessPhoneNumber, country, new Address(addressLine1, addressLine2, addressLine3, country, postCode), postCode));
        System.out.println("Customer created!");

        scanner.nextLine();
    }

    /**
     * Show all customers from the command line interface.
     * This method will call the customer service to get all customers.
     * It will then print all the customers to the console.
     * The method will then wait for the user to press enter before returning.
     */
    public void showAllCustomers() {
            System.out.println("Let's list all Customers!");
            List<Customer> customers = customerRepository.findAll();
            customers.forEach(System.out::println);
            new Scanner(System.in).nextLine();
    }

    /**
     * Show one customer from the command line interface.
     * This method will ask the user for the id of the customer to show.
     * It will then call the customer service to get the customer.
     * If the customer is found, it will print the customer to the console.
     * If the customer is not found, it will print a failure message.
     * The method will then wait for the user to press enter before returning.
     */
    public void showOneCustomer() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Let's see a Customer!");
        System.out.println("Give me an id of any Customer and I'll show it");
        String id = scanner.nextLine();
        Customer customer = customerRepository.findCustomerById(id).orElseThrow(
                () -> new IllegalStateException("Customer not found")
        );
        scanner.nextLine(); //consume \n
        if(customer != null) {
            System.out.println(customer);
        }else {
            System.out.println("Customer not found");
        }
        scanner.nextLine();
    }

    /**
     * Update a customer from the command line interface.
     * This method will ask the user for the id of the customer to update.
     * It will then call the customer service to get the customer.
     * If the customer is found, it will ask the user for the new data.
     * It will then call the customer service to update the customer.
     * If the customer is updated successfully, it will print a success message.
     * If the customer is not updated successfully, it will print a failure message.
     * The method will then wait for the user to press enter before returning.
     */
    public void updateCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Let's update a Customer!");
        System.out.println("Give me all the data to update the Customer.");
        System.out.println("Id of Customer: ");
        String id = scanner.nextLine();
        Customer customer = customerRepository.findCustomerById(id).orElseThrow(
                () -> new IllegalStateException("Customer not found")
        );
        if(customer != null) {
            System.out.print("Business Name["+customer.getBusinessName()+"]:");
            String businessName = scanner.nextLine();
            System.out.print("Country["+customer.getAddress().getCountry()+"]:");
            String country = scanner.nextLine();
            System.out.print("Post Code["+customer.getAddress().getPostCode()+"]:");
            String postCode = scanner.nextLine();
            System.out.print("Business Phone Number["+customer.getTelephoneNumber()+"]:");
            String businessPhoneNumber = scanner.nextLine();
            if(!businessName.isEmpty()){
                customer.setBusinessName(businessName);
            }
            if(!businessPhoneNumber.isEmpty()){
                customer.setTelephoneNumber(businessPhoneNumber);
            }
            if(!country.isEmpty()){
                customer.getAddress().setCountry(country);
            }
            if(!postCode.isEmpty()){
                customer.getAddress().setPostCode(postCode);
            }
            customerRepository.save(customer);
                System.out.println("Customer updated!");

        }else {
            System.out.println("Customer not found");
        }
        scanner.nextLine();
    }

    /**
     * Delete a customer from the command line interface.
     * This method will ask the user for the id of the customer to delete.
     * It will then call the customer service to get the customer.
     * If the customer is found, it will delete the customer.
     * If the customer is deleted successfully, it will print a success message.
     * If the customer is not deleted successfully, it will print a failure message.
     * The method will then wait for the user to press enter before returning.
     */
    public void deleteCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Let's delete a Customer!");
        System.out.println("Give me an id of any Customer and I'll delete it");
        String id = scanner.nextLine();
        //find customer first, then delete
        Customer customer = customerRepository.findCustomerById(id).orElseThrow(
                () -> new IllegalStateException("Customer not found")
        );
        customerRepository.delete(customer);
        scanner.nextLine(); //consume \n
        System.out.println("Customer deleted!");
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
