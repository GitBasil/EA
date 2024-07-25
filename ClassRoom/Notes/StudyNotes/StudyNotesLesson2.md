### Lesson 2 Spring Boot and AOP - Study Notes

#### Spring Boot Overview

- **Framework**: Simplifies configuration and running of Spring applications.
- **Features**:
  - Simple Maven configuration
  - Default/auto Spring configuration
  - Containerless deployment

#### Spring Boot POM File

```xml
<parent>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-parent</artifactId>
  <version>2.0.0.M6</version>
  <relativePath/> <!-- lookup parent from repository -->
</parent>
<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
  </dependency>
</dependencies>
<build>
  <plugins>
    <plugin>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-maven-plugin</artifactId>
    </plugin>
  </plugins>
</build>
```

#### Example Application

```java
@SpringBootApplication
public class SpringBootApplication {
  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(SpringBootApplication.class);
    CustomerService customerService = context.getBean("customerServiceImpl", CustomerService.class);
    customerService.addCustomer();
  }
}
```

#### Using Annotations

- **EmailServiceImpl**:
  ```java
  @Service
  public class EmailServiceImpl implements EmailService {
    public void sendEmail() {
      System.out.println("Sending email");
    }
  }
  ```
- **CustomerServiceImpl**:

  ```java
  @Service
  public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private EmailService emailService;

    public void addCustomer() {
      emailService.sendEmail();
    }
  }
  ```

#### Spring Boot Configuration

- **application.properties**: Default configuration file.
- **Injecting values**:

  ```java
  @Service
  public class EmailServiceImpl implements EmailService {
    @Value("${smtpserver}")
    String smtpServer;

    public void sendEmail() {
      System.out.println("Sending email using smtp server " + smtpServer);
    }
  }
  ```

#### Aspect-Oriented Programming (AOP)

- **Concepts**:
  - **Joinpoint**: Specific point in the code.
  - **Pointcut**: Collection of one or more joinpoints.
  - **Advice**: Implementation of the crosscutting concern.
  - **Aspect**: Defines advice and pointcuts.
  - **Weaving**: Combining advice with the target code at specified pointcuts.
- **AOP with Spring Boot**:
  ```java
  @Aspect
  @Configuration
  public class TraceAdvice {
    @Before("execution(* accountpackage.AccountService.*(..))")
    public void traceBeforeMethod(JoinPoint joinpoint) {
      System.out.println("before execution of method " + joinpoint.getSignature().getName());
    }

    @After("execution(* accountpackage.AccountService.*(..))")
    public void traceAfterMethod(JoinPoint joinpoint) {
      System.out.println("after execution of method " + joinpoint.getSignature().getName());
    }
  }
  ```

#### Advice Types

- **Before Advice**: Executes before the method.
- **After Returning Advice**: Executes after the method returns without exception.
- **After Throwing Advice**: Executes after the method throws an exception.
- **After Advice**: Executes after the method, regardless of its outcome.
- **Around Advice**: Wraps the method execution.

#### Sample Code for Getting Parameters from Joinpoint

```java
@Aspect
public class TraceAdvice {
  @After("execution(* mypackage.Customer.setName(..))")
  public void traceMethod(JoinPoint joinpoint) {
    Object[] args = joinpoint.getArgs();
    String name = (String) args[0];
    System.out.println("method = " + joinpoint.getSignature().getName());
    System.out.println("parameter name = " + name);
  }
}
```

These notes cover key points and sample code to help you understand Spring Boot and AOP. Make sure to review annotations and advice types for a deeper understanding of their applications.
