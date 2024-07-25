## Lesson 4 - JPA Mapping 1-2 - Study Notes

### Mapping Data Types

- **Annotation Types:**
  - `@Column` - Specifies more details.
  - `@Temporal` - Specifies how a Date should be persisted (DATE, TIME, TIMESTAMP).
  - `@Lob` - Indicates large values.
  - `@Transient` - Indicates that a property should not be persisted.

### Default Mapping

```java
@Entity
public class Person {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private Date birthday;
    private String biography;
    private String temp;
    // Other fields and methods
}
```

### Specify Different Mapping

```java
@Entity
public class Person {
    @Id
    @GeneratedValue
    private long id;

    @Column(name="FULLNAME", length=255, nullable=false)
    private String name;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Lob
    private String biography;

    @Transient
    private String temp;
    // Other fields and methods
}
```

- `name` will be stored as `FULLNAME VARCHAR(255) NOT NULL`
- `birthday` will be stored as a Date.
- `biography` will be stored as CLOB instead of VARCHAR.
- `temp` will not be stored in the database.

### Property or Field Access

- JPA can access objects in two ways:
  - **Property Access:** Through getter/setter methods.
  - **Field Access:** Directly from/to the fields.

### Specifying Access with Annotations

- Placing `@Id` on a field specifies field access for the entire object. All other annotations should be on the fields.
- Placing `@Id` on a getter specifies property access for the entire object. All other annotations should be on the getters.

### Entity Object Lifecycle

- **Transient:** A new entity that has not been persisted.
- **Persistent/Managed:** An entity managed by the persistence context.
- **Detached:** An entity with a database identity but not managed by the current persistence context.
- **Removed:** An entity that is removed from the database.

### Persistence Context

- Manages the entities.
- Guarantees that managed entities will be saved in the database.
- Tracks changes until they are pushed to the database.
- Works like a cache.

### Association Mapping

- **One-to-Many Uni-directional Default Mapping:**

  ```java
  @Entity
  public class Person {
      @Id
      @GeneratedValue
      private int id;
      private String firstname;
      private String lastname;
      @OneToMany
      private List<Car> cars = new ArrayList<Car>();
      // Other fields and methods
  }

  @Entity
  public class Car {
      @Id
      @GeneratedValue
      private int id;
      private short year;
      private String model;
      private String maker;
      // Other fields and methods
  }
  ```

### Cascade Types

- **ALL:** Cascade on all operations.
- **PERSIST:** Cascade on persist operations.
- **MERGE:** Cascade on merge operations.
- **REMOVE:** Cascade on remove operations.
- **REFRESH:** Cascade on refresh operations.

### Changing the Default Fetching

```java
@Entity
public class Course {
    @Id
    private String courseNumber;
    private String name;
    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="courseid")
    private Collection<Student> students = new ArrayList<Student>();
    // Other fields and methods
}
```

### Mapping Collections

- **Not Ordered List:**

  ```java
  @Entity
  public class Person {
      @Id
      @GeneratedValue
      private int id;
      private String firstname;
      private String lastname;
      @OneToMany(mappedBy="owner", cascade=CascadeType.PERSIST)
      private Collection<Tool> tools = new ArrayList<Tool>();
      // Other fields and methods
  }

  @Entity
  public class Tool {
      @Id
      @GeneratedValue
      private int id;
      private String type;
      private String size;
      @ManyToOne
      private Person owner;
      // Other fields and methods
  }
  ```

- **Set:**

  ```java
  @Entity
  public class Toolbox {
      @Id
      @GeneratedValue
      private int id;
      private String manufacturer;
      private String model;
      @OneToMany(mappedBy="toolbox", cascade=CascadeType.PERSIST)
      private Set<Tool> tools = new HashSet<Tool>();
      // Other fields and methods
  }

  @Entity
  public class Tool {
      @Id
      @GeneratedValue
      private int id;
      private String type;
      private String size;
      @ManyToOne
      private Toolbox toolbox;
      // Other fields and methods
  }
  ```

- **One-to-Many Bi-directional List:**

  ```java
  @Entity
  public class Person {
      @Id
      @GeneratedValue
      private int id;
      private String firstname;
      private String lastname;
      @OneToMany(cascade=CascadeType.PERSIST)
      @JoinColumn(name="buyer_id")
      @OrderColumn(name="sequence")
      private List<Item> shopList = new ArrayList<Item>();
      // Other fields and methods
  }

  @Entity
  public class Item {
      @Id
      @GeneratedValue
      private int id;
      private String name;
      private String description;
      // Other fields and methods
  }
  ```

- **Map:**

  ```java
  @Entity
  public class Person {
      @Id
      @GeneratedValue
      private int id;
      private String firstname;
      private String lastname;
      @OneToMany(mappedBy="owner", cascade=CascadeType.PERSIST)
      @MapKey(name="name")
      private Map<String, Pet> pets = new HashMap<String, Pet>();
      // Other fields and methods
  }

  @Entity
  public class Pet {
      @Id
      @GeneratedValue
      private int id;
      private String name;
      private String species;
      private String race;
      @ManyToOne
      private Person owner;
      // Other fields and methods
  }
  ```

### JPA Default Fetching

- `@OneToOne` defaults to eager loading.
- `@ManyToOne` defaults to eager loading.
- `@OneToMany` defaults to lazy loading.
- `@ManyToMany` defaults to lazy loading.

---

These notes provide a quick reference for JPA mapping and should help in understanding and implementing the various types of associations and their configurations in JPA.
