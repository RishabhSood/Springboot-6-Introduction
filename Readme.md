1. Open [start.spring.io](start.spring.io) and set all dependencies / build tool type / spring version / java version etc and download the starter repository.

2. We defined POJOs for JPA Entities (Book & Author) inside the java folder located in `src > main > java > com > example > appName > domain`. (POJO: Plain Old Java Object. Contains Fields, Getters & Setters (these can be generated automatically in VSCode by `right click > source action.. > generate getters and setters`) with no such restriction or rules to adhere to)

3. We Defined a Many to Many Relationship between the two objects. Following insights might help:
    - In JPA, the owning side of a Many-to-Many relationship is responsible for managing the relationship and defining the join table, while the inverse side simply maps the relationship to the owning side. The mappedBy attribute in the @ManyToMany annotation in the Author entity specifies the field in the owning side (Book entity) that maps to the relationship.
    - By specifying mappedBy = "authors" in the @ManyToMany annotation of the Author entity, you are telling JPA that the authors field in the Book entity is the owning side of the relationship, and that it should use the author_book join table defined in the Book entity to manage the relationship. This means that you do not need to define another @JoinTable annotation in the Author entity, as the relationship and join table are already defined in the Book entity. JPA will automatically create the join table and manage the Many-to-Many relationship between Author and Book entities based on the annotations in the Book entity.

4. Equality for two objects (as determined by hibernate) may affect many operations run on an entity, thus we define the `equals` and the `hashcode` methods on an entity (this can be generated automatically in VSCode by `right click > source action.. > equals & hashcode`).

5.  A JPA repository is a component in a Java application that implements the JPA specification and provides a simple and consistent interface for accessing and managing data stored in a relational database.
    - In particular, a JPA repository is typically implemented using a library such as Spring Data JPA, which provides a set of standard interfaces and classes for working with JPA. These interfaces and classes are designed to reduce boilerplate code and make it easy to define queries and interact with a database using JPA.

    - We extend the CrudRepository interface to create repositories for Author & Book Entities.

        CRUDRepository is an interface in Spring Data JPA that provides a set of methods for performing common database operations on entities. The acronym CRUD stands for Create, Read, Update, and Delete, which are the four basic functions that are typically required for persistent storage of data in a database.

        - The CRUDRepository interface extends the Repository interface and adds the following methods:

            - `save()` : This method is used to save an entity to the database. It can be used to create a new entity or update an existing one.
            - `findById()` : This method is used to retrieve an entity from the database by its ID.
            - `findAll()` : This method is used to retrieve all entities from the database.
            - `deleteById()` : This method is used to delete an entity from the database by its ID.
            - `delete()` : This method is used to delete an entity from the database.
            
            - In addition to these methods, the CRUDRepository interface also provides some other methods for querying the database, such as `existsById()`, `count()`, and `deleteAll()`. These methods can be used to check if an entity exists in the database, count the number of entities in the database, or delete all entities from the database.

            - By using the CRUDRepository interface, developers can easily perform common database operations without having to write SQL queries or boilerplate code. The interface is also flexible enough to allow developers to define custom queries using JPA Query Language (JPQL) or native SQL, if necessary.