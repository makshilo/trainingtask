<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create employee</title>
</head>
<body>
<button onclick="window.location.href='/controller?command=employeesPage'">Back</button><br>
<form action="<c:url value="/controller?command=createEmployee"/>" method="post">
    <label for="fname">First name:</label>
    <input required maxlength="50" type="text" id="fname" name="fname"><br><br>
    <label for="lname">Last name:</label>
    <input maxlength="50" type="text" id="lname" name="lname"><br><br>
    <label for="patro">Patronymic:</label>
    <input maxlength="50" type="text" id="patro" name="patro"><br><br>
    <label for="posit">Position:</label>
    <input maxlength="50" type="text" id="posit" name="posit"><br><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
