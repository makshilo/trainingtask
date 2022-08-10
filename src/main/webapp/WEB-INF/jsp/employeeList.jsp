<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Employee list page</title>
</head>
<body>
<button onclick="window.location.href='/'">Back</button><br>
<h3>Employees</h3>
<table>
    <tr>
        <th>Last name</th>
        <th>First name</th>
        <th>Patronymic</th>
        <th>Position</th>
    </tr>
    <c:forEach var="employee" items="${requestScope.employees}">
        <tr>
            <td>${employee.lastName}</td>
            <td>${employee.firstName}</td>
            <td>${employee.patronymic}</td>
            <td>${employee.position}</td>
            <td><button onclick="window.location.href='/controller?command=employeeEditPage&id=${employee.id}'">Edit</button></td>
            <td><button onclick="window.location.href='/controller?command=deleteEmployee&id=${employee.id}'">Delete</button></td>
        </tr>
    </c:forEach>
</table>
<button onclick="window.location.href='/controller?command=employeeCreatePage'">Add</button>
</body>
</html>