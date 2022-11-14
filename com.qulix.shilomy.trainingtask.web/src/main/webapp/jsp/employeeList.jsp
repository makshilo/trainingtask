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
            <td><a class="table-button" href="${pageContext.request.contextPath}/editEmployee?id=${employee.id}">Изменить</a></td>
            <td>
                <form action="${pageContext.request.contextPath}/deleteEmployee?id=${employee.id}" method="post">
                    <button class="table-button" name="delete" type="submit">Удалить</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<a class="add-button" href="${pageContext.request.contextPath}/createEmployee">Добавить</a>
</body>
</html>