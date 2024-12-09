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
![image](https://github.com/user-attachments/assets/8ade1e52-e0ff-4627-ae74-e60637d1e7ae)

---

### **2. Retrieve All Orders**

**Endpoint**: `GET /api/orders`

**Description**: Retrieves all orders stored in the database.

**cURL**:
```bash
curl -X GET http://localhost:8080/api/orders
```
![image](https://github.com/user-attachments/assets/d7b4dd4c-1f7b-4d8b-9685-aeec2c5fe228)

---

### **3. Retrieve Order by ID**

**Endpoint**: `GET /api/orders/{id}`

**Description**: Retrieves a specific order by its ID.

**cURL**:
```bash
curl -X GET http://localhost:8080/api/orders/1
```
![image](https://github.com/user-attachments/assets/26e9e2ab-f785-4fc1-b16f-0846dc475b29)

---

### **4. Update Order Status**

**Endpoint**: `PUT /api/orders/{id}`

**Description**: Updates the status of a specific order.

**cURL**:
```bash
curl -X PUT "http://localhost:8080/api/orders/1?status=PROCESSED"
```
![image](https://github.com/user-attachments/assets/b990c871-415c-4fa4-9c1d-19aec4881ad4)

---

### **5. Fetch Orders from Product A**

**Endpoint**: `GET /api/orders/fetch`

**Description**: Fetches new orders from the external system (Mocky API).

**cURL**:
```bash
curl -X GET http://localhost:8080/api/orders/fetch
```
![image](https://github.com/user-attachments/assets/552e0216-e634-4ae0-85bc-23c87c0e31a4)

---

### **6. Send Orders to Product B**

**Endpoint**: `POST /api/orders/send-to-product-b`

**Description**: Sends processed orders to Product B.

**cURL**:
```bash
curl -X POST http://localhost:8080/api/orders/send-to-product-b
```
![image](https://github.com/user-attachments/assets/9256bc0c-2f96-4065-96bf-2d76c8ac18a0)

---

### **7. Delete Order**

**Endpoint**: `DELETE /api/orders/{id}`

**Description**: Deletes an order by its ID.

**cURL**:
```bash
curl -X DELETE http://localhost:8080/api/orders/1
```
![image](https://github.com/user-attachments/assets/85635998-cce2-4549-adb8-181393ea9449)

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
