# Instagram-API

## Frameworks and Languages used:
* `Java 17`
* `Maven 4.0.0`
* `MySql Version 8.0.32`
* `Spring Boot 3.0.5(Framwork)`
*  `IntelliJ IDEA 2023.1 (Community Edition)`


## Data Flow:

### 1. Models:
* It consists User, Post and AuthenticationToken entity classes along with their properties.
* Used @Entity annotation to create table of class in database.
* Create constructors and getters and setters of all of classes By using annotation @Data  and @NoArgsConstructor.
* Used @OneToMany and @ManyToOne annotation to perform one to Many and Many to one mapping between user and post and also using one to one mapping between AuthenticationToken and User.Using some validation on user and post class properties.

### 2. Controllers:
* It consists of UserController and PostController classes in which used the annotations like @RestController to annotate the class as Controller layer.
* Used annotation @GetMapping , @PostMapping , @PutMapping , @DeleteMapping to map the HTTP web requests to the specific handler methods.


### API References:

#### User API References

* User signUp:
```
  http://localhost:8080/user/signUp
```

* User singIn:
```
  http://localhost:8080/user/signIn
```

* Update User PhoneNo By UserId:
```
  http://localhost:8080/user/userId/1?userPhoneNo={phoneNo}&token={token}&userEmail={useEmail}
```
#### Post API References:

* Create Post:
```
  http://localhost:8080/post/token/{token}/userEmail/{userEmail}
```

* Get all Posts:
```
 http://localhost:8080/post/all
```
* Get Posts by userName:
```
 http://localhost:8080/post/userName/{userName}?token={token}9&userEmail={userEmail}
```


### 3. Services:
* It consists  UserService, postService and AuthenticationService classes in which having some business logic of every handler methods.
* Used @Service annotation to indicate that a class belongs to the service layer.

### 4. Repositories:
* It consists of IUserRepo, IPostRepo and ITokenRepo interfaces that extends JpaRepository interface so we can use inbuilt JpaRepository method and we create some cusom finder method and writting some query method to perform crud operation like create ,read, update and delete.
* Used @Repository annotation to indicate that a interface belongs to the repository layer(Dao layer).


## Project Summary:

In this spring boot application I had created API's mapping for CRUD operations like create(save), read(find) and update.Aim of this project creating Instagram API for  signUp instagram account, signIn account, create post on instagram by user and get posts of a user by user name. In this project uses authentication and uses some validation in user and post classes.
