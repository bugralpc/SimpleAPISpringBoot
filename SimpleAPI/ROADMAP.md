# ROAD MAP
## Current State

The current API leverages Spring Boot's @RestController and uses dependency injection to integrate the CompensationService class. It provides the following functionalities:
- Fetch all compensation data.
- Filter and sort compensation data based on HTTP request parameters.
- Fetch single compensation entity based on specific parameters.

The API employs a "partial" N-Tier/Hierarchical architecture, utilizing the service class for both de-serialization of .json files (acting as an in-memory database) and service provision to the Controller class. The service class is configured through the application.properties to access the JSON file path and is responsible for:
- Filtering operations based on properties like annualbasepay[gte], annualbasepay[lte], annualbasepay, gender, and yearsofexperience.
- Sorting operations for annualbasepay and yearsofexperience.
- Fetching single entities based on employer and annualbasesalary.

## Future Enhancements
- Advanced Filtering: Enhance functionality to support complex queries, including range queries and pattern matching.
- Sorting Capabilities: Extend sorting options to improve data manipulation.
Security Measures: Implement authentication and authorization to secure API access.
- Performance Optimization: Improve API performance through technical enhancements.
- Testing: Develop comprehensive unit and integration tests to ensure reliability.
- Architecture Expansion: Introduce Database and Repository classes to refine the architectural framework.
