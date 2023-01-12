<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0,minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
        <title>Task 3</title>
    </head>
    <body>
        <h1><%= "Distributed Systems Class Clicker" %> </h1>
        <br/>
        <p>Submit your answer to the current question:</p>
        <form action="submit" method="get">
            <input type="radio" name="question" value="A"> A
            <br>
            <input type="radio" name="question" value="B"> B
            <br>
            <input type="radio" name="question" value="C"> C
            <br>
            <input type="radio" name="question" value="D"> D
            <br>
            <input type="submit" value="Submit">
        </form>
        <a href="getResults">Show Results</a>
    </body>
</html>