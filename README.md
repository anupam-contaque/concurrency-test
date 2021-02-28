#concurrency-test
Test concurrent updates in MySQL by incrementing single column in database on each request.

To run application, edit the application.properties file and add datasource configuration and make sure to un-comment last property to load schema on first run.
```properties
# Datasource URL of MySQL database
spring.datasource.url=jdbc:mysql://localhost:3306/concurrency
# Datasource username for connecting to database
spring.datasource.username=
# Datasource password for connecting to database
spring.datasource.password=


# Enable this property when running application for first time,
# this will create database schema from schema.sql file
# and data.sql will populate the initial counter value to 0.
#
# Comment this property if running application for second time
# or concurrency_test database table is already created.
#
#spring.datasource.initialization-mode=always
```

Then application can be started by
`mvn spring-boot:run` from CLI, and by default it will run on port 8080.

Following API endpoints are provided related to counter:

1. `POST /api/counter/increment` - increments counter in database by 1. 
   
    Sample response -
    ```json
    {
      "status": "success",
      "message": "Counter updated to 1"
    }
    ```
2. `POST /api/counter/reset` - reset counter in database to 0.

   Sample response -
    ```json
    {
      "status": "success",
      "message": "Counter reset to 0"
    }
    ```

3. `GET /api/counter/current-value` - get current value of counter from database.

   Sample response -
    ```json
    {
      "status": "success",
      "message": "Current value for counter is 1"
    }
    ```


For load testing use JMeter and add **concurrency.jmx** file, that will make 5000 concurrent request to `/api/counter/increment` endpoint and after finishing execution, value of counter in database should be 5000. 