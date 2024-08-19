# Description 

This application enables users to interact with StackOverflow (SOF) data by fetching, viewing, and storing a list of SOF users. Users can view details such as name, userId, reputation, and last access date. Additionally, users can bookmark and unbookmark their favorite SOF users, view only bookmarked users, and manage their bookmark list. Users can also export data stored in a custom .sofusers file format

# Architecture.

The application is divided into two main components:


> Backend : 
 - SpringBoot
 - OpenFegin
 - Hibernate
 - H2 Database
 - Lombok
 - Spring Web
> Frontend
- SpringBoot
- JavaFX
 - OpenFegin

# Database Schema

![image](https://github.com/user-attachments/assets/1df8e961-5f11-4d9e-ba49-97b6165d179a)



# Personal reasoning why choosing a third-party library.
1. OpenFegin
     -  Simplifies sending HTTP requests to the SOF API.
      - Enhances code readability.
      - Provides easy serialization of API responses into Java objects.
2. Hibernate
     - Database ORM
3. H2 Database
     - in-memory Database
    - Offers an in-memory database for easier and faster development. 
4. Lombok 
    - Reduces boilerplate code and improves code readability.

5. Spring Web
   - Development of RESTful APIs.

6. Java FX
   - Developing User Interface
   - Intgerted with SecenBuilider to Drag and Drop UI elements 
     
      



    



 
 


## Installation

Pleses Git clone theses two Project 


```git
git clone https://github.com/xSami2/stackOverFlowUsers-Backend

git clone https://github.com/xSami2/stackOverFlowUsers-Frontend
```

## Running the Application


__1__ - Open both the backend and frontend projects in your IDE at the Same time.

__2__ - Download the required Maven dependencies for both projects.

__3__ - Run the backend application first.

__4__ - After the backend is running, start the frontend application.


# Note

the __.sofusers__ file will be Saved in the Backend Application in resource folder
