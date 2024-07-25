### JPA Mapping 2 - Study Notes

#### Inheritance Mapping Strategies

1. **Single Table per Hierarchy**

   - **Pros**: Simplified schema, quick polymorphic queries.
   - **Cons**: Nullable columns may arise, schema changes affect entire table.
   - **Annotations**:
     ```java
     @Entity
     @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
     @DiscriminatorColumn(name = "account_type", discriminatorType = DiscriminatorType.STRING)
     public abstract class Account {
       @Id @GeneratedValue
       private long number;
       private double balance;
       ...
     }
     ```

2. **Joined Tables**

   - **Pros**: Normalized schema, matches domain model structure.
   - **Cons**: Slower performance due to multiple SQL operations for updates/inserts.
   - **Annotations**:
     ```java
     @Entity
     @Inheritance(strategy = InheritanceType.JOINED)
     public abstract class Account {
       @Id @GeneratedValue
       private long number;
       private double balance;
       ...
     }
     ```

3. **Table per Concrete Class**
   - **Pros**: No joins needed, no nullable columns.
   - **Cons**: Requires `UNION` for polymorphic queries, may not be supported by all JPA implementations.
   - **Annotations**:
     ```java
     @Entity
     @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
     public class Account {
       @Id @GeneratedValue(strategy = GenerationType.TABLE)
       private long number;
       private double balance;
       ...
     }
     ```

#### Complex Mappings

- **Secondary Tables**

  - Allows a class to be mapped to multiple tables.
  - **Example**:

    ```java
    @Entity
    @SecondaryTable(name = "SavingsAccountDetails", pkJoinColumns = @PrimaryKeyJoinColumn(name = "account_id"))
    public class SavingsAccount {
      @Id
      @GeneratedValue
      private Long id;
      private String accountNumber;

      @Column(table = "SavingsAccountDetails")
      private double APY;
      ...
    }
    ```

- **Embedded Classes**

  - Embed multiple classes within a single table.
  - **Example**:
    ```java
    @Entity
    public class Person {
      @Id @GeneratedValue
      private int id;
      private String firstname;
      private String lastname;
      @Embedded
      private Address address;
      ...
    }
    @Embeddable
    public class Address {
      private String street;
      private String city;
      private String state;
      private String zip;
      ...
    }
    ```

- **Composite Keys**
  - Multiple column primary keys.
  - **Example**:
    ```java
    @Embeddable
    public class Name implements Serializable {
      private String firstname;
      private String lastname;
      ...
    }
    @Entity
    public class Employee {
      @Id
      private Name name;
      @Temporal(TemporalType.DATE)
      private Date startDate;
      ...
    }
    ```

This concise format provides a focused overview of complex JPA mapping techniques and is suitable for quick study sessions before exams.
