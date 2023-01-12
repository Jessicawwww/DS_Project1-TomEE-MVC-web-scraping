<%--
  Created by IntelliJ IDEA.
  User: olivia
  Date: 9/23/22
  Time: 10:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ds.project1task3.ClickerModel"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
        <title>task3</title>
    </head>
    <body>
    <h1>Distributed Systems Class Clicker</h1>
    <br>
    <%
        HashMap<String,Integer> map = (HashMap<String, Integer>) request.getAttribute("result");
        if (map.size()!=0) { %>
    <p>The results from the survey are as follows</p>
    <br>

    <% for (Map.Entry<String, Integer> node: map.entrySet()) {
    %>
    <p><%= node.getKey()%>:<span><%= node.getValue()%></span></p>
    <% }%>
    <br>
    <p>These results have now been cleared</p>
    <% } else { %>
    <p>There are currently no results</p>
    <% }%>

    </body>
</html>

