# Robot Simulator API

## Introduction
The **Robot Simulator API** is a Spring Boot application that simulates a robot's movement on a **RxC grid**. The robot follows movement commands such as:
- Move forward (`M`)
- Rotate left (`L`) or right (`R`)
- Stay within the grid boundaries

---

## Table of Contents
1. Installation & Running Locally
2. Docker Instructions
3. API Endpoints
4. Running Test Cases
5. Error Handling

---

## Installation & Running Locally

### Prerequisites
Ensure the following are installed:
- **Java 17**
- **Maven** (verify with `mvn -v`)
- **Docker** 

### Steps to Run

## Docker Instructions

### Build the Image
```sh
docker build -t robot-simulator .
```

### Run the Container
```sh
docker run -p 8080:8080 robot-simulator
```

### Manage Containers
- List running containers:
  ```sh
  docker ps
  ```
- Stop a container:
  ```sh
  docker stop <CONTAINER_ID>
  ```
- Remove a container:
  ```sh
  docker rm <CONTAINER_ID>
  ```
  ---
  
## Without Docker Instructions

1. Build the application:
   ```sh
   mvn clean package
   ```
2. Execute the JAR file:
   ```sh
   java -jar target/robot-simulator-0.0.1-SNAPSHOT.jar
   ```
3. Access the application at:  
   [http://localhost:8080](http://localhost:8080)


------

## API Endpoints

### `POST /robot/simulate` (Text Input)
#### Request Example (Plain Text)
```
5 5
1 2 S
MRMLM
```

#### Success Response (200 OK)
```
3 1 S
```

#### Error Response (400 Bad Request)
```json
{
  "message": "Invalid input format. Expected 3 lines: (table, robot, commands).",
  "status": "400 BAD_REQUEST"
}
```

---

### `POST /robot/simulate/file` (File Upload)
#### Request Parameters
| Parameter | Type        | Description                   |
|-----------|-------------|-------------------------------|
| `file`    | text/plain  | Text file containing input data |

**Example File Content**:
```
5 5
1 2 S
MRMLM
```

#### Success Response (200 OK)
```
3 1 S
```

#### Error Response (400 Bad Request)
```json
{
  "message": "Invalid file format or missing data",
  "status": "400 BAD_REQUEST"
}
```

---

## Running Test Cases
The application uses:
- **JUnit 5** for unit tests
- **MockMvc** for API testing

### Execute Tests
```sh
mvn clean test
```

### Test Summary
| Test Case                          | Result  |
|------------------------------------|---------|
| Valid simulation request          | Passed  |
| Invalid text input (wrong format)  | Passed  |
| File upload with valid data        | Passed  |
| File upload with empty data        | Passed  |


---

## Error Handling
Errors are managed via `GlobalExceptionHandler.java`, returning structured HTTP responses.

| Error Type               | Message                                | HTTP Status |
|--------------------------|----------------------------------------|-------------|
| Invalid Input            | Invalid input format                   | 400         |
| File Missing             | No file uploaded                       | 400         |
| Out of Bounds            | Movement out of bounds                 | 400         |
| Internal Server Error    | An unexpected error occurred           | 500         |

---
