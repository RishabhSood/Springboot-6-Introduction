# Introduction to SpringFramework

![wp11161502-2](https://user-images.githubusercontent.com/55499929/234184216-523567a6-48c5-4031-a679-58b372e6a822.png)

### Setup & JPA Entities
> Commit Reference: [`e4a21b9`](https://github.com/RishabhSood/Springboot-6-WebApp/commit/e4a21b90c1757373aae02eaa28955a64a137e581)
1. Open [start.spring.io](start.spring.io) and set all dependencies / build tool type / spring version / java version etc and download the starter repository.

2. We defined POJOs for JPA Entities (Book & Author) inside the java folder located in `src > main > java > com > example > appName > domain`. (POJO: Plain Old Java Object. Contains Fields, Getters & Setters (these can be generated automatically in VSCode by `right click > source action.. > generate getters and setters`) with no such restriction or rules to adhere to)

### Many-to-Many Relationship b/w entities
> Commit Reference: [`345ab80`](https://github.com/RishabhSood/Springboot-6-WebApp/commit/345ab8075dbe4398016f7454b2745ed8222bfc85)

3. We Defined a Many to Many Relationship between the two objects. 
    <details><summary>Defining Many to Many Relationships</summary>

    - In JPA, the owning side of a Many-to-Many relationship is responsible for managing the relationship and defining the join table, while the inverse side simply maps the relationship to the owning side. The mappedBy attribute in the @ManyToMany annotation in the Author entity specifies the field in the owning side (Book entity) that maps to the relationship.
    - By specifying mappedBy = "authors" in the @ManyToMany annotation of the Author entity, you are telling JPA that the authors field in the Book entity is the owning side of the relationship, and that it should use the author_book join table defined in the Book entity to manage the relationship. This means that you do not need to define another @JoinTable annotation in the Author entity, as the relationship and join table are already defined in the Book entity. JPA will automatically create the join table and manage the Many-to-Many relationship between Author and Book entities based on the annotations in the Book entity.
    </details>

### Object Equality, Hashcode & toString() for Entities
> Commit Reference: [`50b4274`](https://github.com/RishabhSood/Springboot-6-WebApp/commit/50b4274a149b954c7f9b08ccb75372dc884e4746)

4. Equality for two objects (as determined by hibernate) may affect many operations run on an entity, thus we define the `equals` and the `hashcode` methods on an entity (this can be generated automatically in VSCode by `right click > source action.. > equals & hashcode`).

### JPA Repositories
> Commit Reference: [`3709f011`](https://github.com/RishabhSood/Springboot-6-WebApp/commit/3709f011d3acf0aae1af855c6314f17ad97d4c68)

5.  We extend the CrudRepository interface to create repositories for Author & Book Entities.
    <details><summary>What is a JPA Repository?</summary>

    - A JPA repository is a component in a Java application that implements the JPA specification and provides a simple and consistent interface for accessing and managing data stored in a relational database.

    - In particular, a JPA repository is typically implemented using a library such as Spring Data JPA, which provides a set of standard interfaces and classes for working with JPA. These interfaces and classes are designed to reduce boilerplate code and make it easy to define queries and interact with a database using JPA.
    </details>
    <details><summary>What is a CrudRepository?</summary>
    
    - CRUDRepository is an interface in Spring Data JPA that provides a set of methods for performing common database operations on entities. The acronym CRUD stands for Create, Read, Update, and Delete, which are the four basic functions that are typically required for persistent storage of data in a database.
    
    - The CRUDRepository interface extends the Repository interface and adds the following methods:

        - `save()` : This method is used to save an entity to the database. It can be used to create a new entity or update an existing one.
        - `findById()` : This method is used to retrieve an entity from the database by its ID.
        - `findAll()` : This method is used to retrieve all entities from the database.
        - `deleteById()` : This method is used to delete an entity from the database by its ID.
        - `delete()` : This method is used to delete an entity from the database.
        
        - In addition to these methods, the CRUDRepository interface also provides some other methods for querying the database, such as `existsById()`, `count()`, and `deleteAll()`. These methods can be used to check if an entity exists in the database, count the number of entities in the database, or delete all entities from the database.

        - By using the CRUDRepository interface, developers can easily perform common database operations without having to write SQL queries or boilerplate code. The interface is also flexible enough to allow developers to define custom queries using JPA Query Language (JPQL) or native SQL, if necessary.
    </details>

### Bootstrapping, Object Creation & Linking
> Commit Reference: [`ae33e9c`](https://github.com/RishabhSood/Springboot-6-WebApp/commit/ae33e9c6da5caa879301a6765df4feb282d6764c), [`8dcc41d`](https://github.com/RishabhSood/Springboot-6-WebApp/commit/8dcc41d4c1b64c40c670851c675761e03db7ff59)
   
6. We add a CommandLineRunner, which initializes the H2 in-memory database with an Author & a Book object, which are mapped to each other. We noticed how, the many-to-many field when left blank may lead to null-field error (thus, we initalize it with a new hashset). (Note that, for a many to many relationship, we must update the `Set` attribute for both the objects. Fixed in the second commit mentioned above)
    <details><summary>What are Spring Stereotypes?</summary>
    
    - In the Spring framework, a stereotype is a way of marking a class to indicate its role in the application. The most commonly used stereotypes in Spring are `@Component`, `@Service`, `@Repository`, and `@Controller`. These stereotypes are used to define specific roles for classes in a Spring application and to help Spring manage the lifecycle and dependencies of these classes.

    - Here's a brief overview of each of these stereotypes:

        - `@Component`: This stereotype is used to mark a class as a component of the Spring application. This includes classes that don't fit into other stereotypes, such as utility classes or classes that don't handle user requests.
        - `@Service`: This stereotype is used to mark a class as a service in the Spring application. Services typically handle business logic and are used to encapsulate complex business rules.
        - `@Repository`: This stereotype is used to mark a class as a repository in the Spring application. Repositories are used to interact with a database or other data store, and typically provide CRUD (Create, Read, Update, Delete) operations for entities.
        - `@Controller`: This stereotype is used to mark a class as a controller in the Spring application. Controllers are responsible for handling user requests and returning responses.
        
        By using these stereotypes, Spring can automatically detect and manage the lifecycle of these classes, including creating instances of the class, injecting dependencies, and cleaning up resources when they're no longer needed. This helps to reduce boilerplate code and simplify the configuration of a Spring application.
    </details>

    <details><summary>What is a CommandLineRunner ?</summary>

    - In the Spring Framework, a command line runner is an interface that allows developers to create components that can be executed from the command line.
    - The `CommandLineRunner` interface defines a single method, run(), which is called when the application is started and is used to execute the code required for the command line application.
    - You can specify the order of execution for multiple CommandLineRunner instances by implementing the Ordered interface or using the `@Order` annotation.
    - To use the Ordered interface, you need to implement it in your CommandLineRunner component and define a numerical value for the order in the `getOrder()` method.
        ```java
        @Component
        public class MyCommandLineRunner implements CommandLineRunner, Ordered {

            @Override
            public void run(String... args) throws Exception {
                System.out.println("Hello, world!");
            }

            @Override
            public int getOrder() {
                return 1;
            }
        }
        ```
    - Alternatively, you can use the `@Order` annotation to specify the order. Components with lower order values are executed first.
        ```java
        @Component
        @Order(1)
        public class MyOtherCommandLineRunner implements CommandLineRunner {

            @Override
            public void run(String... args) throws Exception {
                System.out.println("Hello, world from MyOtherCommandLineRunner!");
            }
        }
        ```
    - Each CommandLineRunner component can be defined in a separate file, typically as a Spring `@Component` or `@Service` class.
    - Spring will `automatically detect` and execute all `CommandLineRunner` instances defined in the application context when the application `starts`.
    </details>

### Viewing & Querying H2 DB
> Commit Reference: [`8dcc41d`](https://github.com/RishabhSood/Springboot-6-WebApp/commit/8dcc41d4c1b64c40c670851c675761e03db7ff59)
- In case developer tools are installed, one can enable the h2-console by adding the following setting:
    `spring.h2.console.enabled=true`
    to the `src > main > resources > application.properties` file.
- Once done, the console can then be accessed at `/h2-console`. Remember to note the `JDBC URL` output in the console (this can then be used to log into the database on the console).

### MVC Architecture
> Commit Reference: None

<img width="861" alt="image" src="https://user-images.githubusercontent.com/55499929/233903601-d2760632-992a-4de7-b134-1d91830e4f54.png">

- `Model:` Simple POJO with collection of properties which may/may not be used by the view. It represents the data and business logic of the application. It is responsible for managing the data, performing operations on the data, and providing access to the data to the other components of the application. 
    The Model typically consists of classes that define the data objects and their relationships, as well as the operations that can be performed on the data. It encapsulates the business logic of the application and provides a layer of abstraction between the data and the other components of the application.

    Example: JPA + Hibernate
- `View:` Data as requested by the client (GUI). Implemented with JSP, Thymeleaf etc. Can be HTML, JSON, XML etc.
- `Controller:` Java CLass implemeted to handle request mapping. Contains minimal business logic, and primarily acts a 'traffic cop'. Works in conjunction with services which contains business logic.

    <details> <summary>An insight on Models & Service</summary>
    The Model represents the data and its operations, while the Service layer encapsulates the application's business logic and coordinates the interaction between various components, including the Model.
    </details>

### Creating a Service Layer
> Commit Reference: [`e94f32a`](https://github.com/RishabhSood/Springboot-6-WebApp/commit/e94f32a4d15a55ae9c7c7fe4db6f7cb94cd05e7f)
- We create a `services` package in the `src > main > java > com.example.appname` directory.
- Next, here we add `interfaces` & their respective `implemntation` (annotated by the `@Service` decorator). Here, we add a `Books` Service which contains a `findAll` method.
    <details><summary>How does Dependency injection work in the current context ?</summary>
    
    - The `"@Service"` annotation indicates to the Spring framework that this class is a candidate for dependency injection.

    - The `BookServiceImpl` class has a dependency on the `BookRepository interface`, which is injected through the constructor using constructor-based dependency injection. 

    - By using dependency injection, the service component is not directly responsible for creating its dependencies. Instead, the Spring framework will create an instance of the `BookRepository` implementation and pass it into the `constructor` of the `BookServiceImpl class`. This allows for a more modular and flexible application design.
    </details>

### Spring Controllers
> Commit Reference: [`aa23c69`](https://github.com/RishabhSood/Springboot-6-WebApp/commit/aa23c69cb234ada3ce38c4075e4823de771224d8)
- We create a controller `BookController` which returns an iterable of all books in the db, when hit on the endpoint `/books`. The controller depends on a service for its implementation, which is injected by `spring`. Notice that the service interface is provided instead of an implementation.
- A `Controller` ideally returns a view (to which it passes information via a `model`, which contains `key-value` attributes with the information needed)
- When Spring detects that the BookController class has a constructor with a single parameter of type BookService, it looks for a bean that implements the BookService interface and injects it into the constructor. In this case, Spring finds the BookServiceImpl bean that implements the BookService interface and injects it into the BookController constructor.
    <details><summary>How does Spring knows which implementation to inject?</summary>

    - When we have multiple services that implement the same interface, we need to specify which implementation should be used for a given component. This is done using the `@Qualifier` annotation, which allows us to specify a specific implementation that should be used for dependency injection. 
    - Spring will use the implementation that is specified in the configuration, and if we don't specify a qualifier, Spring will try to autowire the dependencies based on type and may throw an exception if it finds multiple implementations of the same interface. By specifying the `@Qualifier` annotation, we can ensure that the correct implementation is used for dependency injection.
        <details><summary>Example:</summary>
        
        ```java
        @Service
        @Qualifier("bookServiceImpl")
        public class BookServiceImpl implements BookService {
        // implementation details
        }
        ```
        _And we would modify the constructor of the BookController to use the "@Qualifier" annotation to specify the BookServiceImpl implementation:_
        ```java
        @Controller
        public class BookController {
            
            private final BookService bookService;

            public BookController(@Qualifier("bookServiceImpl") BookService bookService) {
                this.bookService = bookService;
            }

            // rest of the class implementation
        }
        ```
        </details>
    </details>

### Thymeleaf Templates
> Commit Reference: [`ba675d3`](https://github.com/RishabhSood/Springboot-6-WebApp/commit/ba675d3ee4bd027a58c35174e1e16116c38dffa3)
- Thymeleaf templating requires the `thymeleaf starter` dependecy, which must be added to the `pom.xml` file.
- Next, one can start creating tempaltes in `src > main > resources > templates`
- 📑 [Docs](https://www.thymeleaf.org/documentation.html)

### Summary
> You might notice that we have added a List-Authors View in the final commit, this is left as an exercise for the reader to implement.
- We have created a very basic application which renders templates by thymeleaf.
- Spring framework takes the controller, which are mapping us to the right templates/documents (which go all the way to the database and get the records).
- We haven't created any databse tables on our own, this is done automatically by hibernate (configured by springboot). It creates databases based on what we configured in our domain. Hibernate performs reflection on the Entities we have created in order to do this.
- The databases are then initialized by the CommandLineRunner.
