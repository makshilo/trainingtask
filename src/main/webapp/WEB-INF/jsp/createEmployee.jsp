<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Добавить сотрудника</title>
</head>
<body>
<button onclick="window.location.href='/controller?command=employeesPage'">Назад</button><br>
<form action="<c:url value="/controller?command=createEmployee"/>" accept-charset="UTF-8" method="post">
    <label for="fname">Имя:</label>
    <input required maxlength="50" type="text" id="fname" name="fname" oninvalid="this.setCustomValidity('Заполните поле')" oninput="setCustomValidity('')"><br><br>
    <label for="lname">Фамилия:</label>
    <input maxlength="50" type="text" id="lname" name="lname"><br><br>
    <label for="patro">Отчество:</label>
    <input maxlength="50" type="text" id="patro" name="patro"><br><br>
    <label for="posit">Должность:</label>
    <input maxlength="50" type="text" id="posit" name="posit"><br><br>
    <input type="submit" value="Сохранить">
</form>
</body>
</html>
