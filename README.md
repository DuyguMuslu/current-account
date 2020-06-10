CREATING CURRENT ACCOUNT
----------------------------------------
This is a simple service for creating account and displaying customer's accounts

Build
-----
- Clone the project to your local environment
(H2 Embedded DB is used)
- In the project directory run `./mvnw spring-boot:run`
 
Usage
---------------
- Base Path for the application is `http://localhost:8080/api/v1`
- REST APIs can be found on following URL
 - to initialize sample data
  `http://localhost:8080/api/v1/initialize`
 - to get customer account details
 `http://localhost:8080/api/v1/customer/{customerID}`
 - to create a new account for an existing customer
 `http://localhost:8080/api/v1/customer/{customerID}/initialCredit/{amount}`
- Sample requests are listed on a postman collection 
  https://www.getpostman.com/collections/3fb28129b9dce279eaae
        __Download__ : https://www.postman.com 
- DB Console can be accessed via http://localhost:8080/api/v1/h2-console/login.jsp
    .No password required, JDBC Url is `jdbc:h2:file:~/custaccounts-schema;AUTO_SERVER=TRUE` 
    
