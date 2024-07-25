# Lesson 7 Transactions - Study Notes

## Basics of Transactions

### What is a transaction?

- **A unit of actions with the following ACID characteristics:**
  - **Atomicity:** All changes occur together or no change occurs (All-or-nothing).
  - **Consistency:** The transaction transforms the system from one consistent state to another consistent state. Transaction must be correct according to application rules.
  - **Isolation:** Data used in one transaction cannot be used in other transactions until the transaction is committed.
  - **Durability:** Once a transaction is committed, its effects are guaranteed to be persistent.

### How do transactions work?

- **DB Application:**
  - **Start transaction -> Commit transaction**
    - Insert A
    - Insert B
  - **Start transaction -> Rollback transaction**
    - Insert A
    - Insert B (error)

## Local or Global Transaction

### Local Transactions

- **Characteristics:**
  - Managed by the database.
  - Simple and fast.

### Global Transactions

- **Characteristics:**
  - Managed by the transaction manager in the Java EE application server.
  - Also called XA transactions.
  - Only needed when two transactional resources are used within one transaction.
  - Uses 2 Phase commit.

## 2 Phase Commit

- **Phases:**
  - **Phase 1: Prepare to commit.**
  - **Phase 2: Commit or Rollback based on the result.**

### Characteristics of XA Transactions

- **Downsides:**
  - 2 phase commit does not guarantee that nothing can go wrong anymore.
  - Slow and often runs over remote connections.
  - Transactional resources become dependent on each other.
  - You have to keep the locks until ALL resources are finished.

## Transaction Propagation

### Types

- **REQUIRED:** If the calling method runs in a transaction, the called method joins the same transaction. If not, a new transaction is created.
- **REQUIRES_NEW:** The called method always runs in a new transaction.
- **MANDATORY:** The called method joins the existing transaction. Throws an exception if there is no existing transaction.
- **SUPPORTS:** The called method joins the transaction if there is one. Otherwise, it runs without a transaction.
- **NOT_SUPPORTED:** The called method does not run within a transaction.
- **NEVER:** Throws an exception if the calling method runs in a transaction.

## Isolation Levels

- **4 levels of isolation:**
  - TransactionReadUncommitted
  - TransactionReadCommitted
  - TransactionRepeatableRead
  - TransactionSerializable
- **Transaction problems:**
  - Dirty read
  - Non-repeatable read
  - Phantom read

### Examples

- **Dirty Read:**
  - Transaction B uses the value of X that was never committed.
- **Non-Repeatable Read:**
  - Transaction B reads X twice, and the value is different.
- **Phantom Read:**
  - Data changes when referenced multiple times.

## JDBC Transaction Example

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
            // no action needed
        }
    }
}
```

## Spring-JPA Transaction Example

```java
public interface CustomerRepository extends JpaRepository<Customer, Long> {}

@Override
public void run(String... args) throws Exception {
    customerrepository.save(new Customer("Jack", "Bauer", "jack@acme.com"));
    customerrepository.save(new Customer("Chloe", "O'Brian", "chloe@acme.com"));
}
```

## Rollback with Checked Exceptions

- The transaction manager by default only rolls back for runtime exceptions.
- To rollback for checked exceptions, explicitly specify this.

```java
public class BankingService implements IBankingService{
    @Transactional(rollbackFor = {DAOException.class})
    public void createCustomerAccount(String customerName, int accountnumber) throws Exception {
        Customer customer = new Customer(customerName);
        customerDao.save(customer);
        Account account = new Account(accountnumber);
        accountDao.save(account);
    }
}
```

## Optimistic Concurrency

- Assumes that lost update conflicts generally don’t occur but keeps versions to know when they do.
- Uses read committed transaction level.
- Guarantees best performance and scalability.
- The default way to deal with concurrency.
- First commit wins instead of last commit wins.

### Versioning

- Additional version column to track updates.

```java
@Entity
public class Customer {
    @Id
    @GeneratedValue
    private int id;
    private String firstname;
    private String lastname;
    @Version
    private int version;
}
```

### Handling StaleObjectStateException

- When a version conflict occurs, Hibernate throws a `StaleObjectStateException`.
- Catching this exception allows you to notify the user about the conflict, enabling them to reload the data and apply their updates against the latest data.

## Main Points

- Always try to use local transactions and only use global transactions when there is no other choice.
- The Spring framework makes it very easy to specify transactions on methods of Spring beans.
- Do less and accomplish more; transactions are automatically applied in an additional AOP layer.

## Conclusion

When defining transaction boundaries in your application, it is important to define the correct transaction propagation and isolation levels to ensure data consistency and integrity.
