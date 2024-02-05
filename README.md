# Task Management System

A Task Management System lets people sign up as users or admins. It has handy features for both, like creating, reading, updating, and deleting tasks. It's a helpful tool for keeping tasks organized, designed to be easy for both admins and users to use.

* The app is built with strong tools like Java, Spring Boot, JPA/Hibernate, and MySQL for handling data well. Swagger makes it easy to manage APIs, and Postman is used to test them thoroughly.

* I organized the app using MVC architecture. Controllers manage APIs, Service classes handle business logic, and Repository interfaces talk to the database. I use DTOs (Data Transfer Objects) to manage entities efficiently and handle exceptions robustly. Transformers help transform data, making the codebase easy to maintain.

Key Features :-
* User Access :- Users can register in the database and manage their tasks by adding, deleting, and updating them. They can also retrieve a list of all assigned tasks.

* Admin Access :- Administrators have access to all user APIs, plus the ability to retrieve all tasks with sorting options for status and due dates. Additionally, admins can retrieve all tasks along with user data from the database.

* Authorization and Authentication :- Registration is open for both users and admins. After entering the correct username and password, users can use all CRUD functions for their tasks, and admins can do the same. However, users have access to limited APIs, while admins have access to all the APIs in the system.

* Swagger Implementation :- Utilized Swagger dependency to comprehensively document all project APIs, facilitating easy accessibility and integration for developers.

* API Testing :- Thoroughly tested all APIs, covering various exceptions and use cases, ensuring functionality and reliability, utilizing Postman software for comprehensive testing and validation.

* Entity Relationship Diagram:-  
![ER-Dig](https://github.com/arpitchavan30/Task-Management-System/assets/126240415/111af490-eeb6-4f53-af96-fd685416617f)
