### Lesson 1: Introduction to Spring - Study Notes

#### Key Concepts:

1. **Enterprise Applications**

   - Web-based
   - Security, remote access, transactions, scheduling
   - Data access and integration
   - Multithreading/pooling

2. **Java EE Standard**

   - Technologies: Servlets, JSP, JSF, EJB, JDBC, JPA, RMI, JMS, Web services
   - Key aspects: Security, transactions

3. **What is Spring?**

   - Lightweight enterprise Java framework
   - Open source
   - Goal: Simplify enterprise Java application development

4. **Spring Framework Aims**

   - Ease of development following good programming practices
   - POJO-based programming
   - Separation of concerns
   - Flexibility

5. **POJO-Based Programming**

   - Code is written in plain Java objects
   - Promotes object-oriented principles
   - Easy to understand, refactor, and unit test

6. **Domain-Driven Design (DDD)**

   - Business logic is captured in the domain model reflecting real-world domains
   - Independence of business logic from enterprise service objects

7. **Advantages of DDD**

   - Business logic remains independent of technology changes
   - Easier to switch technologies
   - Easier to write, test, and modify business logic

8. **Core Components of Spring**
   - Dependency Injection (DI)
   - Aspect-Oriented Programming (AOP)
   - Enterprise Service Templates

#### Sample Code Snippets:

**Dependency Injection Example:**

```java
public class AccountService {
    private AccountDAO accountDAO;

    public void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account getAccount(int accountNumber) {
        return accountDAO.loadAccount(accountNumber);
    }
}
```

```xml
<bean id="accountService" class="bank.AccountService">
    <property name="accountDAO" ref="accountDAO" />
</bean>
<bean id="accountDAO" class="bank.dao.AccountDAO" />
```

**Basic Spring Application:**

```java
package module2.helloworld;

public class CustomerService {
    public void sayHello() {
        System.out.println("Hello from CustomerService");
    }
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">
    <bean id="customerService" class="module2.helloworld.CustomerService" />
</beans>
```

```java
package module2.helloworld;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("module2/helloworld/springconfig.xml");
        CustomerService customerService = context.getBean("customerService", CustomerService.class);
        customerService.sayHello();
    }
}
```

**Dependency Injection Types:**

1. **Setter Injection**

   ```java
   public class AccountService {
       private IAccountDAO accountDAO;

       public void setAccountDAO(IAccountDAO accountDAO) {
           this.accountDAO = accountDAO;
       }
   }
   ```

   ```xml
   <bean id="accountService" class="AccountService">
       <property name="accountDAO" ref="accountDAO" />
   </bean>
   <bean id="accountDAO" class="AccountDAO" />
   ```

2. **Constructor Injection**

   ```java
   public class AccountService {
       private IAccountDAO accountDAO;

       public AccountService(IAccountDAO accountDAO) {
           this.accountDAO = accountDAO;
       }
   }
   ```

   ```xml
   <bean id="accountService" class="AccountService">
       <constructor-arg ref="accountDAO" />
   </bean>
   <bean id="accountDAO" class="AccountDAO" />
   ```

3. **Autowiring**

   ```java
   public class CustomerService {
       private EmailService emailService;

       @Autowired
       public void setEmailService(EmailService emailService) {
           this.emailService = emailService;
       }
   }
   ```

   ```xml
   <context:annotation-config/>
   <bean id="customerService" class="mypackage.CustomerService"/>
   <bean id="emailService" class="mypackage.EmailService"/>
   ```

#### Notes:

- **AOP (Aspect-Oriented Programming)** separates crosscutting concerns (e.g., logging, security) from business logic.
- **Enterprise Service Templates** simplify programming enterprise service APIs like JDBC, JMS, and JavaMail.
- **Spring Beans** are default singletons unless specified otherwise.
- **Lifecycle methods** can be defined using `init-method` and `destroy-method` attributes or annotations like `@PostConstruct` and `@PreDestroy`.

### Summary:

This lesson provides an overview of Spring framework, focusing on simplifying enterprise Java application development through POJO-based programming, dependency injection, and domain-driven design. Key components and examples illustrate the framework's flexibility and best practices.
