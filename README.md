# KredX Money Lending Application

## Summary of REST APIs:

### User APIs
#### Sign-up:
* _Endpoint:_ POST {host}/v1/user/signup
* _Description:_ Creates a new user with the provided username and password.

#### Login:
* _Endpoint:_ POST {host}/v1/user/login
* _Description:_ Logs a user into the application, providing authentication for protected APIs.

#### Logout:
* _Endpoint:_ POST {host}/auth/logout
* _Description:_ Logs out the currently authenticated user, invalidating active sessions.


### Transaction APIs:
#### Lend:
* Endpoint: POST {host}/v1/transaction/lend
* Description: Records a lending transaction with details such as lender, borrower, amount, and date.

#### Borrow:
* Endpoint: POST {host}/v1/transaction/borrow
* Description: Records a borrowing transaction with details such as borrower, lender, amount, and date.

#### Report:
* Endpoint: GET {host}/v1/transaction/user/{username}/report
* Description: Generates a report of transactions for the logged-in user, sorted based on specified parameters (amount, date).


## Tech stacks:
* **Language:** Java 17
* **Framework:** Spring Boot
* **Database:** MySQL
* **Data Access:** Spring Data JPA
* **Security:** Spring Security
* **Version Control:** Git
* **Build Tool:** Maven
* **Deployment:** Spring Boot Embedded Tomcat
* **Testing:** Couldn't complete due to time constraint (we can use junit and mockito)
* **IDE:** IntelliJ IDEA