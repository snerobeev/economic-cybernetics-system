## Economic Cybernetics System
Java-based economic planning system implementing N.A. Veduta's cybernetic approach to economic management and intersectoral balance modeling.

📋 Table of Contents

Overview
Architecture
Technologies
Quick Start
Database Schema
Configuration
API Documentation
Development
Mathematical Models
Contributing

🎯 Overview
This system implements economic cybernetics principles based on the work of N.A. Veduta, focusing on:

Static Planning Models - Intersectoral balance matrices (Leontief models)
Dynamic Simulation - Time series economic forecasting
Feedback Control Systems - Plan vs actual deviation monitoring
Optimization Engines - Resource allocation and planning optimization

🏗️ Architecture
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Presentation  │    │    Business     │    │   Data Access   │
│     Layer       │────│     Layer       │────│     Layer       │
│  (REST API)     │    │  (Core Logic)   │    │ (Repositories)  │
└─────────────────┘    └─────────────────┘    └─────────────────┘
│
┌─────────────────┐
│  Mathematical   │
│    Engine       │
│ (Matrix Calc)   │
└─────────────────┘
Module Structure

Static Planning Module - Intersectoral balances and planning coefficients
Dynamic Simulation Module - Economic growth and trend modeling
Feedback Control Module - Plan monitoring and corrective actions
Optimization Engine - Linear programming and resource optimization

🛠️ Technologies

Java 21 - Core language
Spring Boot 3.2 - Application framework
Spring Data JPA - Data persistence
Hibernate - ORM
H2 Database - In-memory database (development)
EJML 0.43.1 - Matrix calculations
Gradle (Kotlin DSL) - Build tool
JUnit 5 - Testing framework

🚀 Quick Start
Prerequisites

Java 21 or higher
Gradle 7.0+ (or use wrapper)

Installation

Clone the repository:
bash git clone https://github.com/your-username/economic-cybernetics-system.git

cd economic-cybernetics-system

Build the project:

bash./gradlew build

Run the application:

bash./gradlew bootRun

Access the application:


API: http://localhost:8080
H2 Console: http://localhost:8080/h2-console

H2 Database Connection

JDBC URL: jdbc:h2:mem:econplan
Username: sa
Password: password

📊 Database Schema
Entity Relationships
SECTORS (Economic Sectors)
↓ (one-to-many)
PRODUCTS (Products/Goods)
↓ (one-to-many)
PLAN_INDICATORS (Planning Indicators)
Core Entities
EconomicSector

Primary economic sectors (agriculture, industry, services)
Unique sector codes for identification

Product

Goods and services within sectors
Measured in specific units (tons, pieces, m³)

PlanIndicator

Quarterly planning targets and actual results
Deviation tracking and performance metrics

⚙️ Configuration
Application Properties (application.yml)
The system uses YAML configuration with the following main sections:
Database Configuration
yamlspring:
datasource:
url: jdbc:h2:mem:econplan
driver-class-name: org.h2.Driver
username: sa
password: password

JPA/Hibernate Settings
yamlspring:
jpa:
hibernate:
ddl-auto: create-drop  # Recreates schema on restart
show-sql: true           # Logs SQL queries
Custom Economic Settings
yamleconomic-cybernetics:
matrix:
precision: 6           # Matrix calculation precision
max-iterations: 1000   # Maximum optimization iterations
planning:
default-quarters: 4    # Default planning horizon
base-year: 2025       # Base year for calculations

🔧 Development
Project Structure
src/main/java/dev/nerobeev/economic_cybernetics_system/
├── EconomicCyberneticsSystemApplication.java  # Main application class
├── entities/         # JPA entities
├── repositories/     # Data access layer
├── services/         # Business logic
│   ├── static/       # Static planning models
│   ├── dynamic/      # Dynamic simulation
│   ├── feedback/     # Control systems
│   └── optimization/ # Optimization algorithms
├── controllers/      # REST API endpoints
├── config/          # Configuration classes
├── math/            # Mathematical utilities
└── dto/             # Data Transfer Objects

Building and Testing
bash# Build project
./gradlew build

# Run tests
./gradlew test

# Clean and rebuild
./gradlew clean build

# Run with specific profile
./gradlew bootRun --args='--spring.profiles.active=dev'

📐 Mathematical Models
Intersectoral Balance (Leontief Model)
The system implements the classic input-output model:
X = (I - A)^(-1) * Y
Where:

X = Total output vector
I = Identity matrix
A = Technical coefficients matrix
Y = Final demand vector

Implementation Details

Matrix Operations: Using EJML library for efficient calculations
Precision: Configurable precision for economic calculations
Iteration Limits: Maximum iterations for convergence algorithms

🤝 Contributing

Fork the repository
Create a feature branch (git checkout -b feature/amazing-feature)
Commit your changes (git commit -m 'Add amazing feature')
Push to the branch (git push origin feature/amazing-feature)
Open a Pull Request

📝 License
This project is licensed under the MIT License - see the LICENSE file for details.
📚 References

Veduta, N.A. "Economic Cybernetics" - Theoretical foundations
Leontief, W. "Input-Output Economics" - Mathematical models
Spring Boot Documentation - https://spring.io/projects/spring-boot
EJML Documentation - http://ejml.org/

👥 Authors

[Sergei Nerobeev] - Initial work - snerobeev


For questions and support, please open an issue in the GitHub repository.