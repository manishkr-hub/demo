# KredX Money Lending Application

## Summary of REST APIs:

### User APIs
#### Sign-up:
* _Endpoint:_ POST {host}/v1/user/signup
* _Description:_ Creates a new user with the provided username and password.
* _Sample:_ `curl -i -X POST \
  -H "Content-Type:application/json" \
  -d \
  '{
  "name": "user1",
  "username": "user1",
  "password": "123"
  }' \
  'http://localhost:8000/v1/user/signup'`

#### Login:
* _Endpoint:_ POST {host}/v1/user/login
* _Description:_ Logs a user into the application, providing authentication for protected APIs.
* _Sample:_ `curl -i -X POST \
  -H "Content-Type:application/json" \
  -d \
  '{
  "username": "user1",
  "password": "123"
  }' \
  'http://localhost:8000/v1/user/login'`

#### Logout:
* _Endpoint:_ POST {host}/v1/user/logout
* _Description:_ Logs out the currently authenticated user, invalidating active sessions.
* _Sample:_ `curl -i -X POST \
  -H "Content-Type:application/json" \
  -d \
  '' \
  'http://localhost:8000/v1/user/logout'`


### Transaction APIs (Protected - Won't work if not logged in):
#### Lend:
* _Endpoint:_ POST {host}/v1/transaction/lend
* _Description:_ Records a lending transaction with details such as lender, borrower, amount, and date.
* _Sample:_ `curl -i -X POST \
  -H "Content-Type:application/json" \
  -d \
  '' \
  'http://localhost:8000/v1/transaction/lend?lenderUsername=user1&borrowerUsername=user2&amount=100'`

#### Borrow:
* _Endpoint:_ POST {host}/v1/transaction/borrow
* _Description:_ Records a borrowing transaction with details such as borrower, lender, amount, and date.
* _Sample:_ `curl -i -X POST \
  -H "Content-Type:application/json" \
  -d \
  '' \
  'http://localhost:8000/v1/transaction/borrow?lenderUsername=user1&borrowerUsername=user3&amount=400'`

#### Report:
* Endpoint:_ GET {host}/v1/transaction/report
* Description:_ Generates a report of transactions for the logged-in user, sorted based on specified parameters (amount, date).
* _Sample:_ `curl -i -X GET \
  'http://localhost:8000/v1/transaction/report?sortParameter=date&sortOrder=asc&username=user1'`
* _Sample Response:_ `{
  "username": "user1",
  "borrows": [
  {
  "username": "user4",
  "amount": 200,
  "date": "2024-01-06T23:20:17Z"
  },
  {
  "username": "user2",
  "amount": 400,
  "date": "2024-01-07T00:08:44Z"
  }
  ],
  "lends": [
  {
  "username": "user3",
  "amount": 100,
  "date": "2024-01-06T23:19:52Z"
  },
  {
  "username": "user5",
  "amount": 600,
  "date": "2024-01-07T00:08:58Z"
  }
  ]
  }`


## Tech stacks:
* **Language:** Java 17
* **Framework:** Spring Boot
* **Database:** MySQL
* **Data Access:** Spring Data JPA
* **Authentication:** Spring Security
* **Version Control:** Git
* **Build Tool:** Maven
* **Deployment:** Spring Boot Embedded Tomcat
* **UTs & ITs:** Couldn't complete due to time constraint (we can use junit and mockito)
* **IDE:** IntelliJ IDEA


## Steps for running the application:
1. Clone the repository to your local machine using the following command: git clone https://github.com/manishkr-hub/money-lending.git
2. Ensure that MySQL is running on your local machine.
3. Create the necessary tables by executing the queries provided in the schema section.
4. Insert an entry to define the admin role in the "roles" table: INSERT INTO roles (name) VALUES ('ADMIN');
5. Change the port in application.properties if you want to run the application on any other port (by default will run on 8000)
5. Run the application using the main class MoneyLendingApplication.
6. After successfully starting the application, you can make API calls using Postman or any other REST client.
7. Refer to the "Summary of REST APIs" section for details on available APIs and their usage.


## Schema:
* roles: 
`CREATE TABLE roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);`

* users: 
`CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);`

* transactions:
`CREATE TABLE transactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    lender_id BIGINT,
    borrower_id BIGINT,
    amount DECIMAL(10, 2) NOT NULL,
    date TIMESTAMP NOT NULL,
    INDEX idx_date (date),
    INDEX idx_borrower_lender (borrower_id, lender_id)
);`

