1. Project Overview: Lead Management System for Udaan
   The Lead Management System (LMS) for Udaan is a backend application designed to streamline the tracking and management of leads, interactions, and key entities such as restaurants, Points of Contact (POCs), and Key Account Managers (KAMs). It facilitates efficient collaboration and decision-making to enhance lead conversion.
   Objectives
   •	Simplify lead and interaction tracking.
   •	Centralize data for restaurants, POCs, and KAMs.
   •	Ensure scalability and maintainability with modular architecture.
   Key Features
   1	Lead Management: Manage and track leads, including status updates.
   2	Restaurant Management: Add and manage restaurant details linked to leads.
   3	Interaction Tracking: Record and update stakeholder interactions.
   4	POC Management: Manage contact details for effective communication.
   5	KAM Integration: Assign and monitor KAMs for leads and restaurants.
   6	Order Management: Track orders linked to leads and restaurants.
   Technology Stack
   •	Backend: Java (Spring Boot) for RESTful APIs.
   •	Database: Relational database for data persistence.
   •	Deployment: Docker and Kubernetes for scalability.
   Benefits
   •	Efficiency: Automates lead management processes.
   •	Insights: Provides detailed tracking for informed decisions.
   •	Scalability: Modular design allows for easy feature expansion.
   This system empowers Udaan with efficient lead management, fostering collaboration and driving business growth.

2. System Requirements
   1. Software Requirements
      •	Operating System: Compatible with any OS that supports Java 17 (e.g., Windows 10/11, macOS, Linux).
      •	Java: Java Development Kit (JDK) 17 or later.
      •	Maven: Apache Maven 3.8.1 or later for build and dependency management.
      •	Database: MySQL 8.0.33 or compatible versions for the database.
      •	Docker: Docker (optional for containerization, if using the Spotify Docker Maven plugin).
      •	IDE: IntelliJ IDEA, Eclipse, or any modern Java IDE with Spring Boot support.
   2. Dependencies
      •	Spring Boot: Version 3.1.0 (for web, JPA, validation, and security).
      •	MySQL Connector: Version 8.0.33 (runtime dependency for MySQL).
      •	Spring Security: Included for authentication and authorization.
      •	Springdoc OpenAPI: Version 2.1.0 (for API documentation).
      •	Mockito: For unit testing.
      •	Spring Security Test: For testing security features.
   3. Hardware Requirements
      •	Processor: Quad-core or better.
      •	RAM: Minimum 8 GB (16 GB recommended for smooth development and testing).
      •	Storage: At least 10 GB of free space (additional space may be required for Docker images and MySQL data).
   4. Network Requirements
      •	Reliable internet connection to fetch dependencies during the Maven build process and for running MySQL if hosted on a remote server.
   5. Containerization (Optional)
      If Docker is used:
      •	Ensure the Docker daemon is running.
      •	Sufficient privileges to execute Docker commands.


3. Installation Instructions
   Step 1: Prerequisite Setup
        1. Install Java 17:
      •	Download JDK 17 from the Oracle website or use OpenJDK.
      •	Set up JAVA_HOME in your environment variables.
        2. Install Maven:
      •	Download Apache Maven from Maven Downloads.
      •	Add MAVEN_HOME to your environment variables and include Maven in your system's PATH.
        3. Install MySQL:
      •	Install MySQL 8.0.33 or a compatible version.
      •	Create a database named lead_management and note down the username (root) and password for configuration.
        4. Install Docker (Optional):
      •	Download and install Docker from Docker Hub for containerization.

    Step 2: Clone the Repository
    •Clone the project repository using the following command: git clone https://github.com/your-repo/lead-management-system.git
    •cd lead-management-system

    Step 3: Configure the Application
        1. Set up application.properties (or application.yml) under src/main/resources:
         spring.datasource.url=jdbc:mysql://localhost:3306/lead_management
         spring.datasource.username=root
         spring.datasource.password=your_password
         spring.jpa.hibernate.ddl-auto=update
         spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
         spring.security.user.name=admin
         spring.security.user.password=${RANDOM_PASSWORD}
         Note: The RANDOM_PASSWORD is generated every time the application runs. Use this password for Basic Auth in Postman.
           2. (Optional) Configure Docker:
         •	Create the Docker image using the Maven plugin: mvn docker:build

    Step 4: Build the Application
        •	Use Maven to build the project: mvn clean install

4. Run the Application
   1. Start the application:
      •	Run the application using Maven: mvn spring-boot:run
   2. Run the generated JAR file:
      •	Alternatively, execute the JAR file directly: java -jar target/leadEntry-management-system-0.0.1-SNAPSHOT.jar

    Step 6: Access the Application
     •	Open a web browser and go to: http://localhost:8080
     •	API documentation (Swagger UI) can be accessed at: http://localhost:8080/swagger-ui.html

5. Test the Application via Postman
   1. Set Up Basic Authentication:
      •	Use the generated username (root) and password (visible in the application logs when the project starts).
   2. Test CRUD Operations:
      •	Configure the API endpoints in Postman and use Basic Auth credentials for authentication.

6. API Documentation
   1. Lead Management
      1.1 Add New Restaurant Lead
      •	Endpoint: POST /api/leads
      •	Description: Create a new restaurant lead.
      •	Request Body:

{
"restaurantId": 1,
"status": "Active",
"callFrequency": "Weekly",
"lastCallDate": "2025-01-01",
"orders": [1, 2]
}
•	Response:

{
"id": 1,
"restaurantId": 1,
"status": "Active",
"callFrequency": "Weekly",
"lastCallDate": "2025-01-01",
"orders": [1, 2]
}
1.2 Get Lead by ID
•	Endpoint: GET /api/leads/{id}
•	Description: Retrieve lead details by its ID.
•	Response:

{
"id": 1,
"restaurantId": 1,
"status": "Active",
"callFrequency": "Weekly",
"lastCallDate": "2025-01-01",
"orders": [1, 2]
}
1.3 Update Lead Information
•	Endpoint: PUT /api/leads/{id}
•	Description: Update lead status or call frequency.
•	Request Body:

{
"status": "Closed",
"callFrequency": "Monthly",
"lastCallDate": "2025-01-05",
"orders": [3]
}
•	Response:

{
"id": 1,
"restaurantId": 1,
"status": "Closed",
"callFrequency": "Monthly",
"lastCallDate": "2025-01-05",
"orders": [3]
}

2. Contact Management
   2.1 Add Point of Contact (POC)
   •	Endpoint: POST /api/restaurants/{restaurantId}/pocs
   •	Description: Add a new point of contact (POC) for a restaurant.
   •	Request Body:

{
"name": "John Doe",
"role": "Manager",
"contactInfo": "johndoe@example.com"
}
•	Response:

{
"id": 301,
"name": "John Doe",
"role": "Manager",
"contactInfo": "johndoe@example.com"
}
2.2 Get All Points of Contact (POCs)
•	Endpoint: GET /api/restaurants/{restaurantId}/pocs
•	Description: Retrieve all points of contact for a restaurant.
•	Response:

[
{
"id": 301,
"name": "John Doe",
"role": "Manager",
"contactInfo": "johndoe@example.com"
},
{
"id": 302,
"name": "Jane Smith",
"role": "Sales Rep",
"contactInfo": "janesmith@example.com"
}
]

3. Interaction Tracking
   3.1 Record Interaction
   •	Endpoint: POST /api/leads/{leadId}/interactions
   •	Description: Record an interaction (e.g., a call) made with a lead.
   •	Request Body:

{
"type": "Follow-up",
"date": "2025-01-01",
"details": "Discussed new promotions and next steps."
}
•	Response:

{
"id": 1,
"type": "Follow-up",
"date": "2025-01-01",
"details": "Discussed new promotions and next steps."
}
3.2 Get All Interactions for Lead
•	Endpoint: GET /api/leads/{leadId}/interactions
•	Description: Retrieve all interactions related to a specific lead.
•	Response:

[
{
"id": 1,
"type": "Follow-up",
"date": "2025-01-01",
"details": "Discussed new promotions and next steps."
},
{
"id": 2,
"type": "Introductory Call",
"date": "2025-01-02",
"details": "Introduced services and offered a demo."
}
]

4. Call Planning
   4.1 Set Call Frequency for a Lead
   •	Endpoint: PUT /api/leads/{leadId}/call-frequency
   •	Description: Set or update the call frequency for a lead.
   •	Request Body:

{
"callFrequency": "Weekly"
}
•	Response:

{
"id": 1,
"callFrequency": "Weekly"
}
4.2 Get Leads Needing Calls Today
•	Endpoint: GET /api/leads/due-for-call
•	Description: Retrieve all leads that require a call today.
•	Response:
[
{
"id": 1,
"restaurantId": 1,
"callFrequency": "Weekly",
"lastCallDate": "2025-01-01"
},
{
"id": 2,
"restaurantId": 2,
"callFrequency": "Monthly",
"lastCallDate": "2025-01-01"
}
]

5. Performance Tracking
   5.1 Track Well-Performing Accounts
   •	Endpoint: GET /api/leads/well-performing
   •	Description: Retrieve all well-performing accounts based on ordering patterns or call frequency.
   •	Response:

[
{
"id": 1,
"restaurantId": 1,
"status": "Active",
"callFrequency": "Weekly",
"lastCallDate": "2025-01-01",
"orders": [1, 2]
}
]
5.2 Track Underperforming Accounts
•	Endpoint: GET /api/leads/under-performing
•	Description: Retrieve accounts that are underperforming based on ordering frequency or low engagement.
•	Response:
[
{
"id": 3,
"restaurantId": 3,
"status": "Inactive",
"callFrequency": "Monthly",
"lastCallDate": "2025-01-01",
"orders": []
}
]
5.3 Monitor Ordering Patterns and Frequency
•	Endpoint: GET /api/leads/{leadId}/order-pattern
•	Description: Monitor and analyze the ordering patterns of a lead.
•	Response:
{
"leadId": 1,
"restaurantId": 1,
"orderPatterns": {
"lastOrderDate": "2025-01-01",
"orderFrequency": "Weekly",
"averageOrderAmount": 150.75
}
}

7. Response Models
   LeadEntry
   {
   "id": 1,
   "restaurantId": 1,
   "status": "Active",
   "callFrequency": "Weekly",
   "lastCallDate": "2025-01-01",
   "orders": [1, 2]
   }
   PointOfContact
   {
   "id": 301,
   "name": "John Doe",
   "role": "Manager",
   "contactInfo": "johndoe@example.com"
   }
   Interaction
   {
   "id": 1,
   "type": "Follow-up",
   "date": "2025-01-01",
   "details": "Discussed new promotions and next steps."
   }
   Restaurant
   {
   "id": 1,
   "name": "Restaurant Name",
   "address": "123 Main Street, City, Country",
   "contactInfo": "contact@example.com",
   "leadEntries": [
   {
   "id": 1,
   "status": "Active",
   "callFrequency": "Weekly",
   "lastCallDate": "2025-01-01",
   "orders": [1, 2]
   }
   ]
   }



