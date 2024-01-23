# SpringBoot-API
An API built using SpringBoot, which uses XML as the input data format and produces JSON as the output. The queries are in SQL with the MariaDB management system.

# Project Description

This project is a Web Service utilizing the Java Spring Framework, specifically Spring Boot. It is developed entirely in Java, and Spring Boot is utilized through the Eclipse IDE, which can be downloaded from this link: [Spring Tools](https://spring.io/tools/).

To run the project in the Eclipse environment, follow these steps:
1. Open the project in Eclipse.
2. Execute the project by selecting the option "Run as Spring Boot App."

The service is connected to a database and is accessed by a Web Site that sends data in XML format. The URL structure for accessing this service is similar to the following:

http://localhost:8080/apibd?comanda=reserva-getReservasByCliente&dades=%3Creservas%3E%3Creserva%3E%3CidCliente%3E1%3C/idCliente%3E%3C/reserva%3E%3C/reservas%3E

In this URL, the table to be accessed, the API function to be used, and the input data for performing the operation in the database are specified.

Since the data passed is in XML, a parser has been created for each table, which converts the XML into an object with the structure of the table. Therefore, the controller decodes the URL, creates the object from the XML, and calls the service to execute the function.
