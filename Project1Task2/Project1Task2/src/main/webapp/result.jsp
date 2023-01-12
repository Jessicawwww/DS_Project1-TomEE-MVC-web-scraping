<%--
  Created by IntelliJ IDEA.
  User: olivia
  Date: 9/23/22
  Time: 2:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%= request.getAttribute("doctype") %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="author" content="Olivia Wu">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Task 2</title>
    </head>
    <body>
        <h1>State: <%= request.getAttribute("searchState") %></h1>
        <br>
        <h2>Population: <%= request.getAttribute("population") %></h2>
        <h2>Nickname: <%= request.getAttribute("nickname") %></h2>
        <h2>Capital: <%= request.getAttribute("capital") %></h2>
        <h2>Song: <%= request.getAttribute("song") %></h2>
        <h2>Flower: </h2>
        <img src="<%= request.getAttribute("imgFlower") %>" alt="">
        <br>
        <p>Credit: https://statesymbolsusa.org/categories/flower</p>
        <br>
        <h2>Flag: </h2>
        <img src="<%= request.getAttribute("imgFlag") %>" alt="">
        <br>
        <p>Credit: https://states101.com/flags</p>
        <br>
        <button><a href="/Project1Task2-1.0-SNAPSHOT/">Continue</a></button>
    </body>
</html>
