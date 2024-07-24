## Clustered Data Warehouse Application

This Spring Boot application manages foreign exchange (FX) deals in a clustered data warehouse setup. It includes components for handling, validating, and storing FX deal data efficiently.

### Key Components

1. **Service Layer**:
   - `FxDealServiceImpl`: Handles creating, fetching by ID, and listing all FX deals. Ensures valid data with the help of a validator and interacts with the repository for persistence.

2. **DTOs and Models**:
   - `FxDealOrderDTO`: Data Transfer Object for FX deals.
   - `FxDeal`: Entity representing an FX deal in the database.

3. **Repository**:
   - `FxDealRepository`: Interface for CRUD operations on `FxDeal` entities using Spring Data JPA.

4. **Validation and Mapping**:
   - `FxDealValidator`: Ensures currency codes are valid.
   - `FxDealDtoMapper`: Converts between `FxDeal` entities and `FxDealOrderDTO`.

5. **Exception Handling**:
   - `NotFoundException`: Thrown when an FX deal is not found by its ID.

6. **Logging**:
   - Uses `@Slf4j` for logging important actions and errors.

### Workflow

1. **Creating FX Deal**:
   - Validates currency codes.
   - Maps DTO to entity and saves it.
   - Returns the saved entity as a DTO.

2. **Fetching FX Deal by ID**:
   - Retrieves the deal from the repository.
   - Maps it to a DTO.
   - Throws `NotFoundException` if not found.

3. **Listing All FX Deals**:
   - Fetches all deals from the repository.
   - Maps them to DTOs and returns the list.

### Technologies Used

- **Spring Boot** for application framework.
- **Spring Data JPA** for database operations.
- **Jakarta Validation** for input validation.
- **Lombok** for reducing boilerplate code.

This app ensures accurate and efficient management of FX deals in a clustered environment.
