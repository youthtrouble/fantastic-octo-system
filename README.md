### INSTRUCTIONS
#####  Server-side implementation
- To run the project on the backend, you need to input the following command:
  &nbsp;&nbsp;&nbsp;&nbsp;`./mvnw clean spring-boot:run`

- Alternatively, If you have MVN installed, you could run:
  &nbsp;&nbsp;&nbsp;&nbsp;`mvn clean spring-boot:run`

- To navigate to the Javadoc entrypoint, find the file in the path:
  &nbsp;&nbsp;&nbsp;&nbsp;`target/site/apidocs/index.html`
- If this folder doesn't exist, it is as a result of running the spring app with the clean flag as specified above. Please run `./mvnw javadoc:javadoc`

- To run all 77 unit tests contained in the `src/test/java` folder, run the following command:
  &nbsp;&nbsp;&nbsp;&nbsp;`./mvnw test`

#####  Client-side implementation
- `yarn install`
- Start command for frontend: `yarn dev` (Nodejs needs to be installed) Go to **http://localhost:3000/**

  ######  Authentication details(The details work for both the deployed application and Local server):

- Username: "admin"
- Password: "deswerfdeji"

- **Deployed Server-side API on Azure:** [https://oop-assessment-oop-again.azuremicroservices.io](https://oop-assessment-oop-again.azuremicroservices.io/)
- **Deployed Client-side  Application on Netlify:** [https://oop-assessment.netlify.app/](https://oop-assessment.netlify.app/)


###### Functionality Completed:

###### Food product class implemented

- [x] ~~Food product class fully implemented and tested~~

###### Customer class implemented

- [x] ~~Customer class fully implemented and tested including the address class~~

###### Menu and Database Functionality

- [x] ~~Menu option to list all customers in the system~~
- [x] ~~Menu option to add a customer to the system~~
- [x] ~~Menu option to find a customer by ID~~
- [x] ~~Menu option to update a customer~~
- [x] ~~Menu option to delete a customer from the system~~
- [x] ~~Menu option to list all products in the system~~
- [x] ~~Menu option to add a product to the system~~
- [x] ~~Menu option to find a product by ID~~
- [x] ~~Menu option to update a product by ID~~
- [x] ~~Menu option to delete a product by ID~~

###### Web Functionality

- [x] ~~Web page to list all customers in the system~~
- [x] ~~Web function to add a customer to the system~~
- [x] ~~Web function to edit a customer in the system~~
- [x] ~~Web function to delete a customer from the system~~
- [x] ~~Web function to list all products in the system~~
- [x] ~~Web function on to add and edit a product in the system~~
- [x] ~~Web function delete a product~~

###### Advanced Features

- [x] ~~Shopping Basket~~
- [x] ~~Search descriptions and/or filter for products~~
- [x] ~~Lambda expressions~~
- [x] ~~Unit Testing~~
- [x] ~~JavaDoc~~
- [x] ~~Design Pattern(s)~~ - **MVC**
- [ ] Food item class with expiry date
- [x] Search descriptions and/or filter for expired items
