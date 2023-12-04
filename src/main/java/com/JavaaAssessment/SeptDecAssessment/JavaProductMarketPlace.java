package com.JavaaAssessment.SeptDecAssessment;

import com.JavaaAssessment.SeptDecAssessment.Controllers.CliController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class JavaProductMarketPlace {

	public static void main(String[] args) {
		var x = SpringApplication.run(JavaProductMarketPlace.class, args);
		final int LAST_OPTION_MENU = 6;

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
				case LAST_OPTION_MENU -> // exit
					System.out.println("bye!");
				default -> System.out.println("Option " + option + " not found");
			}
		} while (option != LAST_OPTION_MENU);
	}
}
