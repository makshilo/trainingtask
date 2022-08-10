<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Список сотрудников</title>
</head>
<body>
<button onclick="window.location.href='/'">Назад</button><br>
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
            <td>${employee.lastName}</td>
            <td>${employee.firstName}</td>
            <td>${employee.patronymic}</td>
            <td>${employee.position}</td>
            <td><button onclick="window.location.href='/controller?command=employeeEditPage&id=${employee.id}'">Изменить</button></td>
            <td><button onclick="window.location.href='/controller?command=deleteEmployee&id=${employee.id}'">Удалить</button></td>
        </tr>
    </c:forEach>
</table>
<button onclick="window.location.href='/controller?command=employeeCreatePage'">Добавить</button>
</body>
</html>