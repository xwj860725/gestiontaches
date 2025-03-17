# Petit full-stack projet " gestiontaches "   ( Java Spring-boot , React + Vite , PostgreSQL )

This is a sample task management system built with Spring Boot + PostgreSQL + React, supporting the following features:

User registration and login (via local accounts or Google OAuth2 third-party login)
Task CRUD (Create, Read, Update, Delete)
Task search, addition, and deletion
A basic example of a separated front-end and back-end development


/  Main Technology Stack  /  

Back-end:
Java 17
Spring Boot (RESTful API, Spring Data JPA)
Spring Security (user authentication, OAuth2 login)
PostgreSQL (database)

Front-end:
React + Vite
Fetch or Axios for calling back-end APIs
Simple CSS/Flex layout
Environment Dependencies
Java 17 or higher
Maven (recommended version 3.8+)
Node.js with npm or yarn (recommended Node 16+)

PostgreSQL (installed and ensure the database configuration is set correctly in the back-end’s application.properties)
Directory Structure Overview  ↓↓↓

gestiontaches
├── backend-springboot
│   ├── src
│   │   ├── main
│   │   │   └── java/fr/xwj860725/gestiontaches/...  # Controllers, services, entities, etc.
│   │   └── resources
│   │       └── application.properties  # Database configuration, etc.
│   ├── pom.xml
│   └── ...
├── frontend-react
│   ├── src
│   │   ├── App.jsx
│   │   ├── TasksPage.jsx
│   │   ├── LoginPage.jsx
│   │   ├── RegisterPage.jsx
│   │   └── ... 
│   ├── package.json
│   └── ...
└── README.md  # Project documentation


/  Quick Start  /

1. Start the Back-end
* Navigate to the back-end directory
cd backend-springboot

* (Optional) Build and package the project
mvn clean package

* Start Spring Boot
mvn spring-boot:run

* The back-end will listen on http://localhost:8080 by default
NB: The database configuration is ignored in this repository. Please configure your personal database settings as follows:

ini
Copier le code
spring.datasource.url=jdbc:postgresql://localhost:5432/your_project_name
spring.datasource.username=your_database_username
spring.datasource.password=your_database_password
spring.datasource.driver-class-name=org.postgresql.Driver


2. Start the Front-end
* Navigate to the front-end directory
cd ../frontend-react

* Install dependencies
npm install

* Start the development server
npm run dev

* The front-end will listen on http://localhost:5175 by default
NB: If you customize the front-end port, add that port to the whitelist in your back-end CORS or Security configuration.

3. Test Functionality
Open http://localhost:5175 in your browser to access the front-end login page.
Test registration, login, and Google third-party login (if configured).
After a successful login, access the task management page to add, search, and delete tasks.

NB: To enable Google OAuth2 login, please create OAuth2 credentials on the Google Cloud Platform and configure the following properties in your back-end's application.properties:
spring.security.oauth2.client.registration.google.client-id=YOUR_GOOGLE_CLIENT_ID
spring.security.oauth2.client.registration.google.client-secret=YOUR_GOOGLE_CLIENT_SECRET
NB: Some sensitive configurations (such as database passwords and third-party keys) should not be uploaded to the repository. You can ignore these files using .gitignore or manage them with environment variables.



/ License /  
If there are no special requirements, you may freely choose to use the MIT license or another open-source license.
