# interview-test-app

## Project Description

This project is a framework for automated testing of the PlayerController application.

## Technology Stack

- **Language**: Java 11
- **Testing Framework**: TestNG
- **Reports**: Allure
- **HTTP Client**: RestAssured
- **Reduce boilerplate code**: Lombok  
- **Logging**: Enabled
- **Framework Configuration**: Customizable parameters (application URL, thread count, etc.)

## Task

1. Develop a framework for testing the application.
2. Write one positive and one negative automated test for each controller.
3. Identify potential bugs, covering critical ones with automated tests.
4. **Additional Requirements**:
    - Integrate **Allure** for generating test reports.
    - Run tests in **3 threads**.
    - Enable **logging**.
    - Add configuration options for the framework (**application URL, thread count, etc.**).

## Results

During the task execution, automated tests were implemented. And the following bugs were identified: [defects](bugs)

## Running the Tests

### Installation and Setup

1. **Clone the repository**
2. **Navigate to the project directory**:
   ```bash
   cd interview-test-app
   ```
3. **Build the project using Maven**:
   ```bash
   mvn clean install
   ```
4. **Run the tests**:
   ```bash
   mvn test
   ```
   By default, tests will run in 3 threads. This value can be changed in the config file: [config.properties](src/main/resources/config.properties)
5. To run tests with custom thread count use the following command:
   ```bash
   mvn test -Dthread.count=5
   ```
   Where `5` is the number of threads.

7. **Generate Allure Report**:
   
   After the tests are executed, the Allure report can be generated using the following command:
   ```bash
   mvn allure:serve
   ```
   Note: Allure CLI should be installed on the machine. For more information, please refer to the [Allure documentation](https://docs.qameta.io/allure/)
## Swagger Documentation

Swagger documentation is available at: [Swagger Player Controller](http://3.68.165.45/swagger-ui.htm)

