<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Create employee</title>
</head>
<body>
<form action="<c:url value="/controller?command=createEmployee"/>" method="post">
    <label for="fname">First name:</label>
    <input required type="text" id="fname" name="fname"><br><br>
    <label for="lname">Last name:</label>
    <input type="text" id="lname" name="lname"><br><br>
    <label for="patro">Patronymic:</label>
    <input type="text" id="patro" name="patro"><br><br>
    <label for="posit">Position:</label>
    <input type="text" id="posit" name="posit"><br><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
