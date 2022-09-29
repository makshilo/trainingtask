<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="../../css/general.css">
    <link rel="stylesheet" href="../../css/buttons.css">
    <link rel="stylesheet" href="../../css/inputs.css">
    <link rel="stylesheet" href="../../css/labels.css">

    <title>Добавить сотрудника</title>
</head>
<body>
<header>
        <button class="home-button" onclick="window.location.href='/'"></button>
</header>
<button class="back-button" onclick="window.location.href='/controller?command=employeesPage'">Назад</button><br>
<form action="<c:url value="/controller?command=createEmployee"/>" accept-charset="UTF-8" method="post">
    <label for="firstName">Имя:</label><br>
    <input maxlength="50" type="text" id="firstName" name="firstName" value="${param.firstName}">
    <c:if test="${requestScope.firstNameNull}">Заполните поле</c:if><br><br>
    <label for="lastName">Фамилия:</label><br>
    <input maxlength="50" type="text" id="lastName" name="lastName" value="${param.lastName}">
    <c:if test="${requestScope.lastNameNull}">Заполните поле</c:if><br><br>
    <label for="patronymic">Отчество:</label><br>
    <input maxlength="50" type="text" id="patronymic" name="patronymic" value="${param.patronymic}">
    <c:if test="${requestScope.patronymicNull}">Заполните поле</c:if><br><br>
    <label for="position">Должность:</label><br>
    <input maxlength="50" type="text" id="position" name="position" value="${param.position}">
    <c:if test="${requestScope.positionNull}">Заполните поле</c:if><br><br>
    <input type="submit" value="Сохранить">
</form>
</body>
</html>
