<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="../css/general.css">
    <link rel="stylesheet" href="../css/buttons.css">
    <link rel="stylesheet" href="../css/tables.css">
    <title>Список сотрудников</title>
</head>
<body>
<header>
    <a class="home-button" href=".."></a>
</header>
<a class="button" href="..">Назад</a><br>
<h3>Сотрудники</h3>
<table>
    <tr>
        <th>Фамилия</th>
        <th>Имя</th>
        <th>Отчество</th>
        <th>Должность</th>
    </tr>
    <c:forEach var="employee" items="${requestScope.employees}">
        <tr>
            <td><c:out value="${employee.lastName}"/></td>
            <td><c:out value="${employee.firstName}"/></td>
            <td><c:out value="${employee.patronymic}"/></td>
            <td><c:out value="${employee.position}"/></td>
            <td><a class="table-button" href="<c:url value="/employees?action=editEmployeePage&id=${employee.id}"/>">Изменить</a></td>
            <td><a class="table-button" href="<c:url value="/employees?action=deleteEmployee&id=${employee.id}"/>">Удалить</a></td>
        </tr>
    </c:forEach>
</table>
<a class="add-button" href="<c:url value="/employees?action=createEmployeePage"/>">Добавить</a>
</body>
</html>