# Lesson 3 JDBC and JPA - Study Notes

## Object-Relational Mismatch

- **Object-Oriented vs. Relational Database:**
  - Objects: Instantiations of classes with identity (e.g., `object1 == object2`).
  - Relational Model: Uses table name and primary key to identify a row.
  - Associations: Objects have one-to-one, many-to-one, etc., while relational models use foreign keys and link tables.
  - Inheritance: Exists in OO, but not in relational models.
  - Data Access: Follow object associations vs. using queries and joins.

## Java Persistence Possibilities

- **Stored Procedures:** Using PL/SQL or Transact-SQL.
- **SQL in Application:** SQL strings inside the app, using JDBC API or Spring JDBC template.
- **Object Relational Mapping (ORM):** Tools like Hibernate, Toplink, JDO, JPA to map an object model onto a relational schema.

## JDBC

- **Data Access Object (DAO) Pattern:**

  ```java
  public class ProductDAO {
      public void save(Product product) {...}
      public void update(Product product) {...}
      public Product load(int productNumber) {...}
      public void delete(int productNumber) {...}
      public Collection<Product> getAllProducts() {...}
  }
  ```

- **Typical JDBC Code:**

  ```java
  public void update(Employee employee) {
      Connection conn = null;
      PreparedStatement prepareUpdateEmployee = null;
      try {
          conn = getConnection();
          conn.setAutoCommit(false);
          prepareUpdateEmployee = conn.prepareStatement("UPDATE Employee SET firstname= ?, lastname= ? WHERE employeenumber=?");
          prepareUpdateEmployee.setString(1, employee.getFirstName());
          prepareUpdateEmployee.setString(2, employee.getLastName());
          prepareUpdateEmployee.setLong(3, employee.getEmployeeNumber());
          int updateresult = prepareUpdateEmployee.executeUpdate();
          conn.commit();
      } catch (SQLException e) {
          conn.rollback();
          System.out.println("SQLException in EmployeeDAO update() :" + e);
      } finally {
          try {
              prepareUpdateEmployee.close();
              closeConnection(conn);
          } catch (SQLException e1) {
              System.out.println("Exception in closing jdbc connection in EmployeeDAO" + e);
          }
      }
  }
  ```

- **Disadvantages of JDBC:**
  - Code duplication.
  - Open and closing connection issues.
  - Transaction and exception handling.
  - Large number of lines of code.
  - Database-specific error codes in case of SQL exceptions.

## Spring JDBC

- **Advantages:**

  - Takes care of opening and closing JDBC connections.
  - Handles transactions and exceptions.
  - Provides clear, human-readable error messages.

- **Example Code:**

  ```java
  @Repository
  public class CustomerDAO {
      @Autowired
      private NamedParameterJdbcTemplate jdbcTemplate;

      public void clearDB() {
          Map<String,Object> namedParameters = new HashMap<>();
          jdbcTemplate.update("DELETE from customer", namedParameters);
      }

      public void save(Customer customer) {
          Map<String,Object> namedParameters = new HashMap<>();
          namedParameters.put("customernumber", customer.getCustomerNumber());
          namedParameters.put("name", customer.getName());
          namedParameters.put("email", customer.getEmail());
          namedParameters.put("phone", customer.getPhone());
          jdbcTemplate.update("INSERT INTO customer VALUES ( :customernumber, :name, :email, :phone)", namedParameters);
      }
  }
  ```

## JPA (Java Persistence API)

- **Advantages of ORM:**

  - Higher productivity and less code.
  - Easier maintenance with mapping defined in one place.
  - Performance improvements due to caching and optimization by ORM developers.

- **JPA Persistence Methods:**

  - `find()`: Retrieve entity object given the ID.
    ```java
    Employee employee = entityManager.find(Employee.class, employeeid);
    ```
  - `persist()`: Save entity object to database (corresponds to INSERT).
    ```java
    entityManager.persist(employee);
    ```
  - `merge()`: Update entity object in database (corresponds to UPDATE).
    ```java
    entityManager.merge(employee);
    ```
  - `remove()`: Remove entity object from database (corresponds to DELETE).
    ```java
    entityManager.remove(employee);
    ```
  - `createQuery()`: Specify JPQL query translated to SQL.
    ```java
    List<Person> personList = entityManager.createQuery("select p from Person p").getResultList();
    ```

- **HelloWorld JPA Example:**

  ```java
  @Entity
  public class Person {
      @Id
      @GeneratedValue
      private long id;
      private String name;

      public Person() {}

      public Person(String name) {
          this.name = name;
      }

      // Getters and Setters
  }

  public class PersonDaoImpl implements PersonDao {
      private EntityManagerFactory emf;

      public PersonDaoImpl(EntityManagerFactory emf) {
          this.emf = emf;
      }

      public Person getPerson(long id) {
          Person person = null;
          EntityManager em = emf.createEntityManager();
          EntityTransaction tx = em.getTransaction();
          try {
              tx.begin();
              person = em.find(Person.class, id);
              tx.commit();
          } catch (Exception e) {
              tx.rollback();
              throw new DaoException(e);
          } finally {
              em.close();
          }
          return person;
      }

      // Other methods
  }

  public class Application {
      public static void main(String[] args) {
          EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaunit");
          PersonDao personDao = new PersonDaoImpl(emf);

          Person fbperson = new Person(1, "Frank Brown");
          personDao.savePerson(fbperson);

          Person mjperson = new Person(2, "Mary Jones");
          personDao.savePerson(mjperson);

          System.out.println("All persons in the database:");
          Collection<Person> personList = personDao.getAllPersons();
          for (Person person : personList) {
              System.out.println(person.getId() + " - " + person.getName());
          }

          personDao.deletePerson(mjperson);
          fbperson.setName("Frank Johnson");
          personDao.updatePerson(fbperson);

          System.out.println("All persons in the database:");
          Collection<Person> newPersonList = personDao.getAllPersons();
          for (Person person : newPersonList) {
              System.out.println(person.getId() + " - " + person.getName());
          }
      }
  }
  ```

## Summary

- **JPA simplifies database access over JDBC.**
- **Spring JPA further simplifies with additional features.**
- **Key concepts include EntityManager, transactions, and ORM advantages.**
- **Example codes demonstrate typical DAO patterns and transaction management.**

This Markdown conversion includes key points, code snippets, and explanations to help you study the JDBC and JPA concepts effectively.
