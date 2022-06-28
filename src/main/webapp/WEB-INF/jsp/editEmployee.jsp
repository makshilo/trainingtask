<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Employee edit page</title>
</head>
<body>
<button onclick="window.location.href='/controller?command=employeesPage'">Back</button><br>
<h1>Employee edit page</h1>
<form action="<c:url value="/controller?command=editEmployee&id=${requestScope.employee.id}"/>" method="post">
    <label for="fname">First name:</label>
    <input type="text" id="fname" name="fname" value=${requestScope.employee.firstName}><br><br>
    <label for="lname">Last name:</label>
    <input type="text" id="lname" name="lname" value=${requestScope.employee.lastName}><br><br>
    <label for="patro">Patronymic:</label>
    <input type="text" id="patro" name="patro" value=${requestScope.employee.patronymic}><br><br>
    <label for="posit">Position:</label>
    <input type="text" id="posit" name="posit" value=${requestScope.employee.position}><br><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
