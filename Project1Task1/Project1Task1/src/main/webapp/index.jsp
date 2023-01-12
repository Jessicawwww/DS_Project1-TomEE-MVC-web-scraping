<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Task1 Compute Hashes</title>
    </head>
    <body>
        <p>Input text string below and choose the name of hash you want. </p>
        <form action="getHashesForString" method="POST">
            <label for="letter">Type the string.</label>
            <input type="text" name="searchWord" value="" /><br>
            <br>
            MD5:<input type="radio" name="encrypt" value="MD5" checked>
            SHA-256:<input type="radio" name="encrypt"  value="SHA-256">
            <br>
            <input type="submit" value="Click Here to Submit." />
        </form>
    </body>
</html>