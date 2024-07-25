# Midterm Exam 2022-July

### Short Answers

1. **Explain Inversion of Control briefly.**

2. **What does `@Repository` annotation do?**

3. **How is an idempotent method different than a safe method?**

4. **Explain the difference between `RequestParam` and `PathVariable` by giving examples.**

5. **Explain ORM Entity Lifecycle briefly.**

6. **Explain Hibernate fetch strategies with advantages and disadvantages of them.**

7. **Explain `Advice`, `Join Point`, `Pointcut`, and `Aspect` in AOP.**

### Programming Part

8. **Annotate the domains based on the database tables given below, considering the following:**

   - Make the necessary changes to provided domains.
   - All associations are bi-directional.
   - All JPA operations applied on `user` should be propagated to the `address`.
   - You do not create the Service Layer. (Directly call the repository from Controller) during the exam.
     Here are the classes based on the provided diagram:
     ![alt text](image.png)

**Relationships:**

- Item – Comment => One to Many
- Category – Item => Many to Many
- You may find out other relations from the diagram.

### User Class

```java

public class User {
    private int id;
    private String email;
    private String firstname;
    private String lastname;
    private int rank;
}
```

### Address Class

```java
public class Address {
    private int id;
    private String city;
    private String street;
    private String zip;
}
```

### Comment Class

```java

public class Comment {
    private int id;
    private String comment;
}
```

### Item Class

```java

public class Item {
    private int id;
    private String name;
    private String description;
    private float price;
}
```

### Category Class

```java

public class Category {
    private int id;
    private String name;
}
```

### ItemComment Class

```java
public class ItemComment {
    private int itemId;
    private int commentId;
}
```

### CategoryItem Class

```java
public class CategoryItem {
    private int categoryId;
    private int itemId;
}
```

9. **Create a Rest Controller and a Repository for `item` domain to implement the following requirements:**

   - Use Spring Data Derived Queries (naming conventions) for the queries below:
     - Create an endpoint that returns the items whose price is greater than `minPrice`.
     - Create an endpoint that returns items where category equals to `cat` (user parameter).
     - Create an endpoint that return users who are selling a specific item with a price greater than a specified dollar value. For example:
       - Find users who are selling `pencil` and price greater than 70.

10. **You are developing a part of MIU Students Registration System. You need to implement cache features to satisfy the following:** - Create an annotation `MyCachable`. - Intercept every service method that has `MyCachable` annotation and check if the data exists in the cache. - Return it from the cache if it exists. - If it doesn't exist, call the repository method and save data in the cache, and then return the data. - You can assume that `CacheService` interface has the following methods: - `List<T> get(String className);` - `void put(List<T> data);` - To access the `className`, call `getTarget().getClass()` method in `JoinPoint` object.

### Create the Annotation `MyCachable`

```java

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyCachable {
}
```

### CacheService Interface

```java

public interface CacheService {
    <T> List<T> get(String className);
    void put(List<?> data);
}
```

### Aspect for Caching

```java

@Aspect
@Component
public class CacheAspect {

    @Autowired
    private CacheService cacheService;

    @Pointcut("@annotation(MyCachable)")
    public void myCachableMethods() {
    }

    @Around("myCachableMethods()")
    public Object cacheAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getName();
        List<?> cachedData = cacheService.get(className);

        if (cachedData != null && !cachedData.isEmpty()) {
            return cachedData;
        } else {
            Object result = joinPoint.proceed();
            cacheService.put((List<?>) result);
            return result;
        }
    }
}
```

### Example Service Using `MyCachable`

```java

@Service
public class ExampleService {

    @Autowired
    private ExampleRepository exampleRepository;

    @MyCachable
    public List<ExampleEntity> getAllEntities() {
        return exampleRepository.findAll();
    }
}
```

### Example Repository

```java

public interface ExampleRepository extends JpaRepository<ExampleEntity, Long> {
}
```

### Example Entity

```java

@Entity
public class ExampleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // Getters and Setters
}
```

This implementation includes the annotation `MyCachable`, a `CacheService` interface, and an aspect `CacheAspect` that intercepts methods annotated with `MyCachable`, checks if the data is in the cache, and if not, proceeds with the method execution and caches the result. An example service and repository are also provided to illustrate how the `MyCachable` annotation can be used.

11. Create the domain classes based on the requirements and database tables provided below. Note that all associations are bi-directional.

    **Database Requirements:**

    - The university is organized into colleges.

    - Each college has a:

      - Name
      - Main Office
      - Phone
      - Particular faculty member who is the dean of the college

    - Each college administers several academic departments.

    - Each department has a:
      - Name
      - Code
      - Office
      - Phone
      - Particular faculty member who chairs the department
    - Each department offers several courses.

    - Each course has a:

      - Name
      - Code
      - Course Level (1 -> freshman, 2 -> sophomore, ...)
      - Credit
      - Description

    - Courses are offered as sections.

      - Each section is related to a single course and a single instructor.

    - Each section has a:

      - Section number
      - Semester
      - Year
      - Classroom

    - Students can enroll in sections, and the date of enrollment needs to be kept.
    - Each instructor works for one department.

    - Each instructor has a:

      - First Name
      - Last Name
      - Address
      - Phone
      - Office
      - Rank

    - A student is assigned to one academic department.
    - Each student has a:
      - First Name
      - Last Name
      - Address
      - Phone
      - Major Code
