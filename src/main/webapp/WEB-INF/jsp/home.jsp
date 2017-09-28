<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html lang=en>
    <head>
        <meta charset=utf-8>
        <title>Spring Boot <spring:eval expression="@environment.getProperty('spring.application.name')" /></title>
    </head>

    <body id=home>

        <h1>Spring Boot <spring:eval expression="@environment.getProperty('spring.application.name')" /></h1>
        <p><a href=swagger-ui.html>Swagger API.</a></p>
        
    </body>
</html>