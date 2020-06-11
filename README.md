# spring-integration-file-pooler
A simple Spring Boot application that demonstrates how to set up File polling using the Spring Integration


The application consists of an Integration flow that polls a directory for files.

On finding a file it is transformed to a String and passed to a message handling flow and convert the csv input to json that writes it out to another directory.

It can be used as an entry point to a bigger application.

To build it:

```$code
> mvn clean install
```

To run it:
```$code

> mvn spring-boot:run
```



