# ClusteredDataWarehouse
This software is used to accept fx deals and persist entries into a database. Validation is used on data fields to ensure accurate and valid inputation of data.

# Documentation of Project

## Technology used in Project
- Springboot
- Hibernate Validator
- Docker 
- MySQL
- MapStruct

# Project Packages
#### Controller Layer
- POST - fxDeals- Create a new fx deal
- GET - /fxDeals - Gets a list of fx deals
- GET - /fxDeals/{id} - Get an entry of an fx deal by id

### Service Layer
- The business logic can be located in the service layer. The service package contains the service interface and service implementation where the business logic is located.


#### Model Layer
- id - Unique id with Datatype: Long
- orderingCurrency - ISO Currency from deal with Datatype: Currency
- convertingCurrency - ISO Currency to deal with Datatype: Currency
- orderTimeStamp - Instantaneous time with Datatype: LocalDateTime
- amount - Amount with Datatype: BigDecimal

#### Logging
- Logging  was used to log errors and information of operations performed on the controllers and service layers.

#### Exceptions
-Exceptions were used to ensure errors were caught  and prevents sudden breakdown of the application.

### Request body
{
"orderingCurrency": "NGN",
"convertingCurrency": "USD",
"orderTimestamp": 163453563,
"amount": 5000
}


