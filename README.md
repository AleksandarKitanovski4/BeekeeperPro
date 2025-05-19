# **Beekeeper Pro**

Beekeeper Pro is a comprehensive Java-based management system tailored for professional and hobbyist beekeepers. 
It allows users to efficiently manage their apiaries, beehives, inspections, products, finance records, and even weather insights. 
Built using a clean multi-layered architecture and PostgreSQL for persistence, the system supports both production and testing environments.

---

Project Structure

```
com.example.beekeeperpro
├── controller      # User interaction and method entry points
├── model           # POJO classes: User, Apiary, Beehive, Inspection, etc.
├── repository      # JDBC-based data access
├── service         # Business logic and validations
├── utility         # Helpers like DatabaseCleaner
├── weather         # Weather API integration
└── test            # JUnit5 test classes
```

Database

PostgreSQL Databases:

* `beekeeper_pro` (production)
* `beekeeper_pro_test` (test-only)

Tables:

* `users`
* `apiary`
* `beehive`
* `inspection`
* `product_stock`
* `product_price`
* `product_finance`
* `weather_data`

Constraints:

All primary and foreign keys set:

* `users_pkey`
* `apiary_user_id_fkey`
* `beehive_apiary_id_fkey`
* `inspection_user_id_fkey`
* `product_finance_beehive_id_fkey`

---

Features Implemented

User Management

* CRUD operations
* Unique username validation
* Service-layer logic

Apiary Management

* Full CRUD
* Prevent duplicate names
* Search by name

Beehive Management

* CRUD with uniqueness by number
* Validation: must belong to existing apiary

Inspection Management

* Link to beehives
* Prevent duplicates by date/beehive
* Delete by date

Product Module

* Track extracted and sold quantities
* Reduce and calculate remaining stock
* Price per product type with currency
* Finance tracking by beehive number

Weather Integration

* Uses OpenWeatherMap API
* Fetches weather data per city (temperature, wind, humidity, description)
* Caching enabled

---

Testing

* JUnit 5 based
* `DatabaseCleaner` resets `beekeeper_pro_test`
* Includes tests for all main controllers + negative test cases

**Test Classes:**

* `UserControllerTest`
* `ApiaryControllerTest`
* `BeehiveControllerTest`
* `InspectionControllerTest`
* `GeneralNegativeTest`

---

How to Run

Requirements:

* Java 17+
* PostgreSQL installed with databases created
* Environment variables:

    * `SQL_USER`
    * `SQL_PASS`

Running:

Use `Main.java` and controller classes to manually test features.

Running Tests:

* From terminal:

  ```
  mvn clean test
  ```
* Or in IntelliJ: Right-click test class > Run

---

Technologies Used

* Java 17
* PostgreSQL
* JDBC
* OpenWeatherMap API
* JUnit 5

---

Planned Next Steps

* Add product/finance tracking
* Add weather module
* Add CLI interface
* Add monthly inspection/product reports
* Migrate to Spring Boot (REST API)
* Docker support

---

Notes

* `DatabaseCleaner` never touches production DB
* Strict separation of responsibilities per layer
* Exceptions handled via `IllegalArgumentException`, `SQLException`
* Clean code principles and readability prioritized

---

Author

Aleksandar Kitanovski

Self-taught Java developer transitioning from a technical background in engineering and inspection to backend development. 
Passionate about clean code, learning new technologies, and building practical solutions.

---

Project Status

This project is currently *under active development*. 
New features, improvements, and refactoring are being added regularly as part of my learning journey and continued work on backend development with Java.

---

License

This project is open-source and free to use for educational purposes.
