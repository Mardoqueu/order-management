# **Order Management System**

## **Overview**

The Order Management System is a Spring Boot application designed for managing orders, integrating with external product services (via Mocky API), and storing orders in a database. The application is fully containerized using Docker for easy deployment and scalability. This README provides detailed information about the project setup, Dockerization, and cURL commands for testing.

---

## **Features**

1. **Order Management**:
   - Create new orders.
   - Retrieve all orders or specific orders by ID.
   - Update the status of orders.
   - Delete orders.

2. **External System Integration**:
   - Fetch orders from **Product A** (Mocky API).
   - Save fetched orders in the database.

3. **Database**:
   - Uses H2 (in-memory) for development and testing.
   - Includes an accessible H2 console.

4. **Dockerized Deployment**:
   - The application and database are fully containerized using Docker.
   - Docker Compose is used to orchestrate multi-container setups.

---

## **Technologies**

- **Backend**: Spring Boot (Java 17)
- **Database**: H2 (In-Memory)
- **External API**: Mocky.io
- **Build Tool**: Maven
- **Containerization**: Docker, Docker Compose
- **Testing**: Postman, cURL

---

## **Setup Instructions**

### **Running Locally**
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-repo/order-management.git
   cd order-management
   ```

2. **Build the Application**:
   ```bash
   mvn clean package
   ```

3. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```

4. **Access the Application**:
   - API Base URL: `http://localhost:8080`
   - H2 Console: `http://localhost:8080/h2-console`

---

### **Running with Docker**

#### **1. Build and Run Containers**
1. **Ensure Docker and Docker Compose are installed.**
2. **Build the Docker Image**:
   ```bash
   docker build -t order-management:latest .
   ```
3. **Start Containers with Docker Compose**:
   ```bash
   docker-compose up --build
   ```

#### **2. Access the Application**
- API Base URL: `http://localhost:8080`
- H2 Console: `http://localhost:8080/h2-console`
  - **JDBC URL:** `jdbc:h2:mem:testdb`
  - **Username:** `sa`
  - **Password:** `password`

#### **3. Stop Containers**
To stop the containers:
```bash
docker-compose down
```

---

## **Endpoints and cURL Commands**

### **1. Create Order**

**Endpoint**: `POST /api/orders`

**Description**: Creates a new order with default status `PENDING`.

**cURL**:
```bash
curl -X POST http://localhost:8080/api/orders \
-H "Content-Type: application/json" \
-d '{
    "productName": "Product Test",
    "quantity": 5,
    "totalPrice": 100.00
}'
```
![image](https://github.com/user-attachments/assets/f587a00c-9e4e-478b-91ac-2d4676b47bee)

---

### **2. Retrieve All Orders**

**Endpoint**: `GET /api/orders`

**Description**: Retrieves all orders stored in the database.

**cURL**:
```bash
curl -X GET http://localhost:8080/api/orders
```
![image](https://github.com/user-attachments/assets/e4247266-db03-4df8-8a4f-cffc170a5e0c)

---

### **3. Retrieve Order by ID**

**Endpoint**: `GET /api/orders/{id}`

**Description**: Retrieves a specific order by its ID.

**cURL**:
```bash
curl -X GET http://localhost:8080/api/orders/1
```
![image](https://github.com/user-attachments/assets/bf5ee388-0d5b-474b-96b2-11dbe1550755)

---

### **4. Update Order Status**

**Endpoint**: `PUT /api/orders/{id}`

**Description**: Updates the status of a specific order.

**cURL**:
```bash
curl -X PUT "http://localhost:8080/api/orders/1?status=PROCESSED"
```
![image](https://github.com/user-attachments/assets/9c5a9887-ce25-489c-a0b7-b29da9e27ba8)

---

### **5. Fetch Orders from Product A**

**Endpoint**: `GET /api/orders/fetch`

**Description**: Fetches new orders from the external system (Mocky API).

**cURL**:
```bash
curl -X GET http://localhost:8080/api/orders/fetch
```
![image](https://github.com/user-attachments/assets/13a46471-0f50-4169-ada0-8ed9ece6699d)

---

### **6. Send Orders to Product B**

**Endpoint**: `POST /api/orders/send-to-product-b`

**Description**: Sends processed orders to Product B.

**cURL**:
```bash
curl -X POST http://localhost:8080/api/orders/send-to-product-b
```
![image](https://github.com/user-attachments/assets/a344ee59-c9fb-40f8-aa67-4e6caba8620e)

---

### **7. Delete Order**

**Endpoint**: `DELETE /api/orders/{id}`

**Description**: Deletes an order by its ID.

**cURL**:
```bash
curl -X DELETE http://localhost:8080/api/orders/1
```
![image](https://github.com/user-attachments/assets/8615df61-7c6f-4b2c-b825-7cc465e8d1f4)

---

## **Verify Database**

1. Access the H2 Console:
   - URL: `http://localhost:8080/h2-console`
2. Use the following credentials:
   - **JDBC URL:** `jdbc:h2:mem:testdb`
   - **Username:** `sa`
   - **Password:** `password`
3. Run the SQL Query:
   ```sql
   SELECT * FROM ORDER;
   ```

---

## **Notes**

- Ensure Docker is running if you are using the containerized setup.
- For production, replace the H2 database with a robust database like PostgreSQL or MySQL by updating the `application.properties` and `docker-compose.yml`.

Let me know if you'd like additional customizations or explanations! 😊
