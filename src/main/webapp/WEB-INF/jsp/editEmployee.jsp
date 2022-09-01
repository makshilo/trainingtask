<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <link rel="stylesheet" href="../../css/general.css">
    <link rel="stylesheet" href="../../css/buttons.css">
    <link rel="stylesheet" href="../../css/inputs.css">
    <link rel="stylesheet" href="../../css/labels.css">

    <title>Изменить сотрудника</title>
</head>
<body>
<header>
        <button class="home-button" onclick="window.location.href='/'"></button>
</header>
<button class="back-button" onclick="window.location.href='/controller?command=employeesPage'">Назад</button><br>
<h1>Страница изменения сотрудника</h1>
<form action="<c:url value="/controller?command=editEmployee&id=${requestScope.employee.id}"/>" method="post">
    <label for="fname">Имя:</label><br>
    <input required maxlength="50" type="text" id="fname" name="fname" oninvalid="this.setCustomValidity('Заполните поле')"
           oninput="setCustomValidity('')" value=${fn:escapeXml(requestScope.employee.firstName)}><br><br>
    <label for="lname">Фамилия:</label><br>
    <input maxlength="50" type="text" id="lname" name="lname" value=${fn:escapeXml(requestScope.employee.lastName)}><br><br>
    <label for="patro">Отчество:</label><br>
    <input maxlength="50" type="text" id="patro" name="patro" value=${fn:escapeXml(requestScope.employee.patronymic)}><br><br>
    <label for="posit">Должность:</label><br>
    <input maxlength="50" type="text" id="posit" name="posit" value=${fn:escapeXml(requestScope.employee.position)}><br><br>
    <input type="submit" value="Сохранить">
</form>
</body>
</html>
