package com.JavaaAssessment.SeptDecAssessment;

import com.JavaaAssessment.SeptDecAssessment.Controllers.CliController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * The {@code JavaProductMarketPlace} class is the main class of the application.
 * <p>
 * This class is annotated with the {@link org.springframework.boot.autoconfigure.SpringBootApplication}
 * annotation to indicate that it is the main class of the application.
 * </p>
 *
 * @see org.springframework.boot.autoconfigure.SpringBootApplication
 */
@SpringBootApplication
@RestController
public class JavaProductMarketPlace {

	public static void main(String[] args) {
		var x = SpringApplication.run(JavaProductMarketPlace.class, args);
		final int LAST_OPTION_MENU = 11;

		CliController cliHandler = x.getBean(CliController.class);
		int option;
		do {
			option = cliHandler.showMenu();
			switch (option) {
				case 1 -> // list all products
					cliHandler.showAll();
				case 2 -> // list one product
					cliHandler.showOne();
				case 3 -> // create product
					cliHandler.createProduct();
				case 4 -> // update product
					cliHandler.update();
				case 5 -> // delete product
					cliHandler.delete();
				case 6 -> // show all customers
					cliHandler.showAllCustomers();
				case 7 -> // show one customer
					cliHandler.showOneCustomer();
				case 8 -> // create customer
					cliHandler.createCustomer();
				case 9 -> // update customer
					cliHandler.updateCustomer();
				case 10 -> // delete customer
					cliHandler.deleteCustomer();
				case LAST_OPTION_MENU -> // exit
					System.out.println("bye!");
				default -> System.out.println("Option " + option + " not found");
			}
		} while (option != LAST_OPTION_MENU);

//		debug sequence
//				try {
//					SpringApplication.run(JavaProductMarketPlace.class, args);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
	}
}
