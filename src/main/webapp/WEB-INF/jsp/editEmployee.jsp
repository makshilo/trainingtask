<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Изменить сотрудника</title>
</head>
<body>
<button onclick="window.location.href='/controller?command=employeesPage'">Назад</button><br>
<h1>Страница изменения сотрудника</h1>
<form action="<c:url value="/controller?command=editEmployee&id=${requestScope.employee.id}"/>" method="post">
    <label for="fname">Имя:</label>
    <input maxlength="50" type="text" id="fname" name="fname" value=${requestScope.employee.firstName}><br><br>
    <label for="lname">Фамилия:</label>
    <input maxlength="50" type="text" id="lname" name="lname" value=${requestScope.employee.lastName}><br><br>
    <label for="patro">Отчество:</label>
    <input maxlength="50" type="text" id="patro" name="patro" value=${requestScope.employee.patronymic}><br><br>
    <label for="posit">Должность:</label>
    <input maxlength="50" type="text" id="posit" name="posit" value=${requestScope.employee.position}><br><br>
    <input type="submit" value="Сохранить">
</form>
</body>
</html>
