### Lesson 6 JPA Queries - Study Notes

#### Query Techniques

- **Query creation from method names**
- **Using @Query**
- **Using named queries**
- **Using native queries**
- **Using specifications**

#### Method-Based Query

**Query creation from method names:**

```java
public interface UserRepository extends JpaRepository<User, Long> {
  User findByEmailAddress(String emailAddress);
  List<User> findByLastname(String lastname);
  List<User> findByEmailAddressAndLastname(String emailAddress, String lastname);
}
```

**Query methods rules:**

- Start with prefixes: `find…By`, `read…By`, `query…By`, `count…By`, `get…By`.
- Specify property: add property name before `By`.
  - `findTitleBy`
- Limit results: add `First` or `Top` before `By`.
  - `findTopBy`, `findTop1By`, `findFirstBy`, `findFirst2By`
- Unique results: add `Distinct` before `By`.
  - `findTitleDistinctBy`, `findDistinctTitleBy`
- Search criteria after `By`.
  - `findByEmailAddressAndLastname`
- Parameters match search conditions in number and order.

**Query method examples:**

```java
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByLastName(String lastName);
    Optional<Customer> findByEmail(String email);
    Customer findByFirstNameAndLastName(String fistName, String lastName);
    List<Customer> findFirst2By();
}
```

**Example usage:**

```java
Optional<Customer> custopt = custemerrepository.findByEmail("dpalmer@gmail.com");
if (custopt.isPresent()) {
  Customer thecustomer = custopt.get();
  System.out.println(thecustomer);
}

Customer cust = custemerrepository.findByFirstNameAndLastName("Chloe", "O'Brian");
System.out.println(cust);

for (Customer cust2 : custemerrepository.findFirst2By()) {
  System.out.println(cust2);
}
```

#### Pagination

**Pagination and Sorting:**

```java
public interface ProductRepository extends JpaRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {
    List<Product> findAllByCategory(String category, Pageable pageable);
}
```

**Example usage:**

```java
Page<Product> productPage = productRepository.findAll(PageRequest.of(0, 9));
productPage.forEach(p -> System.out.println(p));
System.out.println("Total number of elements = " + productPage.getTotalElements());
System.out.println("Total number of pages = " + productPage.getTotalPages());

List<Product> products = productRepository.findAllByCategory("phones", PageRequest.of(0, 5));
products.forEach(p -> System.out.println(p));

productPage = productRepository.findAll(PageRequest.of(0, 9, Sort.by("name")));
productPage.forEach(p -> System.out.println(p));
```

#### Using @Query

```java
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByLastName(String lastName);

    @Query("select c from Customer c where c.email = ?1")
    Customer findByEmail(String email);

    @Query("select c from Customer c where c.email = :email ")
    Customer findByEmail(@Param("email") String email);
}
```

#### JPQL Examples

```java
select b from Book b where b.price > 15
select b.title from Book b
select b from Book b where b.price between 10 and 15
select b from Book b where b.title like ‘%love%’
select b from Book b order by b.price asc
select c from Customer c where c.address.city = ‘Boston’
select c from Customer c JOIN c.creditcards cr where cr.number= ‘127865439867’
```

#### Named Query

```java
@Entity
@NamedQuery(name="Employee.findByFirstName", query="select e from Employee e where e.firstname = :name")
public class Employee {
    @Id
    @GeneratedValue
    private int id;
    private String firstname;
    private String lastname;
}

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);
}
```

#### Native Query

```java
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByLastName(String lastName);

    @Query(value = "SELECT * FROM customer WHERE EMAIL = ?1", nativeQuery = true)
    Customer findByEmail(String email);

    @Query(value = "SELECT * FROM customer WHERE EMAIL = :email", nativeQuery = true)
    Customer findByEmail(@Param("email") String email);
}
```

#### Specifications

```java
public class BookSpecifications {
    public static Specification<Book> hasInTitle(String title) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + title + "%");
    }
    public static Specification<Book> hasAuthorWithFirstName(String authorFirstName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("authors").get("firstname"), authorFirstName);
    }
    public static Specification<Book> isInCategory(String category) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("category"), category);
    }
}
```

**Using Specifications:**

```java
  Specification<Book> categoryspec = BookSpecifications.isInCategory("Programming");
  List<Book> programmingBooks = bookrepository.findAll(categoryspec);
  programmingBooks.forEach(b -> System.out.println(b));

  Specification<Book> titlespec = BookSpecifications.hasInTitle("Harry");
  List<Book> harryBooks = bookrepository.findAll(titlespec);
  harryBooks.forEach(b -> System.out.println(b));

  Specification<Book> authorWithFirstNamespec = BookSpecifications.hasAuthorWithFirstName("John");
  List<Book> johnBooks = bookrepository.findAll(authorWithFirstNamespec);
  johnBooks.forEach(b -> System.out.println(b));
```

**Combining Specifications:**

```java
  Specification<Book> authorWithFirstNamespec = BookSpecifications.hasAuthorWithFirstName("John");
  Specification<Book> priceGreatherThanspec = BookSpecifications.withPriceGreatherThan(15.0);
  List<Book> pricyBooksFromAuthor = bookrepository.findAll(Specification.where(authorWithFirstNamespec).and(priceGreatherThanspec));
  pricyBooksFromAuthor.forEach(b -> System.out.println(b));
```

#### Bulk Update and Delete

```java
update Customer c set c.status = ‘Gold’ where c.orders > :numberoforders
delete Customer c where c.status = :status
```

**Modifying Statements:**

```java
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByLastName(String lastName);

    @Modifying
    @Transactional
    @Query("update Customer cust set cust.firstName = :firstname where cust.lastName = :lastname")
    int setFixedFirstnameFor(@Param("firstname") String firstName, @Param("lastname") String lastName);
}
```

#### Entities Example

```java
@Entity
public class Customer {
   @Id
   @GeneratedValue
   private long id;
   private String firstname;
   private String lastname;
   @ManyToOne(cascade={CascadeType.PERSIST})
   private Address address;
}

@Entity
public class Address {
   @Id
   @GeneratedValue
   private long id;
   private String street;
   private String city;
   private String zip;
}
```

#### ManyToOne Example

```java
public void run(String... args) throws Exception {
   Address a1 = new Address("mainstreet 1", "Chicago", "58902");
   Customer c1 = new Customer("Frank", "Brown");
   c1.setAddress(a1);
   customerRepository.save(c1);

   Address a2 = new Address("mainstreet 4", "New York", "21345");
   Customer c2 = new Customer("Frank", "Johnson");
   c2.setAddress(a2);
   customerRepository.save(c2);

   List<Customer> customerList = customerRepository.findByFirstname("Frank");
   customerList.forEach(c -> System.out.println(c));
}
```

**Hibernate Output:**

```plaintext
Hibernate: select customer0_.id as id1_2_, customer0_.address_id as address_4_2_, customer0_.firstname as firstnam2_2_, customer0_.lastname as lastname3_2_ from customer customer0_ where customer0_.firstname=?
Hibernate: select address0_.id as id1_0_0_, address0_.city as city2_0_0_, address0_.street as street3_0_0_, address0_.zip as zip4_0_0_ from address address0_ where address0_.id=?
```

#### Optimization Summary

- **Always make ManyToOne relations lazy.**
- **Use join fetch to fetch them eagerly.**
- **OneToMany relations are already lazy.**
- **Use distinct join fetch to fetch them eagerly.**
- **Check ORM database access frequency.**

---

These notes include key points and example code snippets to help you quickly study the essential concepts and techniques related to JPA queries.
