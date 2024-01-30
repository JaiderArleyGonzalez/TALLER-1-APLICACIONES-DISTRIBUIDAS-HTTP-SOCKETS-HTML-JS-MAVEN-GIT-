# TALLER-1-APLICACIONES-DISTRIBUIDAS-HTTP-SOCKETS-HTML-JS-MAVEN-GIT-
## FilmFinder HTTP Server
FilmFinder HTTP Server is a simple Java-based HTTP server that allows users to search for movie details using the OMDb API.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

To run the FilmFinder HTTP Server, you need to have the following software installed:

- Java Development Kit (JDK) version 8 or higher

### Installing

Follow these steps to set up the development environment:

1. Clone the repository to your local machine:

    ```
    git clone https://github.com/JaiderArleyGonzalez/TALLER-1-APLICACIONES-DISTRIBUIDAS-HTTP-SOCKETS-HTML-JS-MAVEN-GIT-.git
    ```
2. Navigate to the project directory
### Running the HTTP Server

To run the FilmFinder HTTP Server, execute the following command:
``` 
    mvn exec:java 
```
The server will start running on port 35000 by default.

You can test the server by opening a web browser and navigating to http://localhost:35000.
### Running the Tests
To run the automated tests for this system, execute the following command:

``` 
    mvn test
```

### Built With
- Java - Programming language
- Maven - Dependency Management
### Author
- Jaider Gonzalez
### Acknowledgments
- OMDb API for providing movie data.
- Inspiration from various open-source projects.