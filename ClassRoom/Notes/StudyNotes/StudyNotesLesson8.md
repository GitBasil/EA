# Lesson 8 MongoDB-2 - Study Notes

## Today's Requirements on Databases

- **Big data**: Large datasets
- **Agility**: Adaptability to changes
- **Unstructured/Semi-structured data**: Handling different data formats

## Database Problems

- **Too much data**: Data no longer fits on one node
- **Scaling issues**: Vertical scaling is expensive and has a single point of failure; Horizontal scaling is complex

## Brewer’s CAP Theorem

- **Consistency**: All nodes see the same data at the same time
- **Availability**: Data is accessible whenever needed
- **Partition tolerance**: Ability to divide data over multiple servers

## Consistency Types

- **Strict Consistency**: Always correct data, no data loss
- **Eventual Consistency**: Data may not be correct immediately but will eventually be consistent

## Problems with Relational Databases

- **Scaling writes**: Difficult and limited
- **Fixed schema**: Schema evolution requires migrations, causing downtime
- **Handling unstructured data**: Poor performance with unstructured and semi-structured data like emails, tweets, and multimedia

## NoSQL Characteristics

- **Key-value store**
- **No fixed schema**
- **Scalable**
- **Eventual consistency**

## MongoDB Overview

- **Document database**: Stores data as documents
- **Fast**: Efficient performance
- **Handles large datasets**

## MongoDB Data Model (JSON Example)

```json
{
  "customer_id": 1,
  "first_name": "Mark",
  "last_name": "Smith",
  "city": "San Francisco",
  "accounts": [
    {
      "account_number": 13,
      "branch_ID": 200,
      "account_type": "Checking"
    },
    {
      "account_number": 14,
      "branch_ID": 200,
      "account_type": "IRA",
      "beneficiaries": []
    }
  ]
}
```

## Rich Document Structures

- Example:
  ```json
  {
    "category": "glove",
    "model": "PRO112PT",
    "name": "Air Elite",
    "brand": "Rawlings",
    "price": 229.99,
    "available": "2013-03-31",
    "position": ["infield", "outfield", "pitcher"],
    "endorsed": {
      "name": "Ryan Howard",
      "team": "Phillies",
      "position": "first base"
    },
    "history": [
      { "date": "2013-03-31", "price": 279.99 },
      { "date": "2013-06-01", "price": 259.79 },
      { "date": "2013-08-15", "price": 229.99 }
    ]
  }
  ```
- Fields can contain arrays and sub-documents

## BSON

- **Binary JSON**: Data is stored in binary format

## Find() Method

- Used to query MongoDB collections

## Spring Mongo Libraries

- Dependency:
  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-mongodb</artifactId>
  </dependency>
  ```

## The Mongo Documents (Example)

- Customer class:
  ```java
  @Document
  public class Customer {
      @Id
      private int customerNumber;
      private String name;
      private String email;
      private String phone;
      private CreditCard creditCard;
  }
  ```
- CreditCard class:
  ```java
  public class CreditCard {
      private String cardNumber;
      private String type;
      private String validationDate;
  }
  ```

## The Repository

- Interface:
  ```java
  @Repository
  public interface CustomerRepository extends MongoRepository<Customer, Integer> {
      Customer findByPhone(String phone);
      Customer findByEmail(String email);
      List<Customer> findByCreditCardType(String type);
      @Query("{email : :#{#email}}")
      Customer findCustomerWithEmail(@Param("email") String email);
  }
  ```

## The Application

- Application class:

  ```java
  public class Application implements CommandLineRunner {
      @Autowired
      private CustomerRepository customerRepository;

      public static void main(String[] args) {
          SpringApplication.run(Application.class, args);
      }

      @Override
      public void run(String... args) throws Exception {
          // create customer
          Customer customer = new Customer(101, "John Doe", "johnd@acme.com", "0622341678");
          CreditCard creditCard = new CreditCard("12324564321", "Visa", "11/23");
          customer.setCreditCard(creditCard);
          customerRepository.save(customer);

          // get customers
          System.out.println(customerRepository.findById(66).get());
          System.out.println(customerRepository.findById(101).get());

          // update customer
          customer = customerRepository.findById(101).get();
          customer.setEmail("jd@gmail.com");
          customerRepository.save(customer);
      }
  }
  ```

## Connecting the Parts of Knowledge

1. **Document Database**: MongoDB stores documents, not relational data
2. **Spring Boot Mongo**: Facilitates the use of MongoDB in applications
3. **Unified Field**: All intelligence resides in the field of transcendental consciousness
4. **Unity Consciousness**: Everything is an expression of the Self
