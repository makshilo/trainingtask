<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <link rel="stylesheet" href="../css/general.css">
    <link rel="stylesheet" href="../css/buttons.css">
    <link rel="stylesheet" href="../css/inputs.css">
    <link rel="stylesheet" href="../css/labels.css">

    <title>Изменить сотрудника</title>
</head>
<body>
<header>
    <a class="home-button" href=".."></a>
</header>
<a class="button" href="${pageContext.request.contextPath}/employees">Отмена</a><br>
<h1>Страница изменения сотрудника</h1>

<c:set scope="request" var="id" value="${requestScope.employee.id}"/>

<c:set var="action" value="${pageContext.request.contextPath.concat(requestScope.pageMode == 'edit' ?
'/editEmployee?id='.concat(param.id) : 'createEmployee')}"/>

<form action="${action}" method="post">
    <label for="firstName">Имя:</label><br>

    <c:set var="firstName" value="${requestScope.employee.firstName != null ? requestScope.employee.firstName : param.firstName}"/>

    <input maxlength="50" type="text" id="firstName" name="firstName" value="${fn:escapeXml(firstName)}"><br><br>
    <label for="lastName">Фамилия:</label><br>

    <c:set var="lastName" value="${requestScope.employee.lastName != null ? requestScope.employee.lastName : param.lastName}"/>

    <input maxlength="50" type="text" id="lastName" name="lastName" value="${fn:escapeXml(lastName)}"><br><br>
    <label for="patronymic">Отчество:</label><br>

    <c:set var="patronymic" value="${requestScope.employee.patronymic != null ? requestScope.employee.patronymic : param.patronymic}"/>

    <input maxlength="50" type="text" id="patronymic" name="patronymic" value="${fn:escapeXml(patronymic)}"><br><br>
    <label for="position">Должность:</label><br>

    <c:set var="position" value="${requestScope.employee.position != null ? requestScope.employee.position : param.position}"/>

    <input maxlength="50" type="text" id="position" name="position" value="${fn:escapeXml(position)}"><br><br>
    <a style="margin-left: 10px"><c:out value="${requestScope.validationError}"/></a><br><br>
    <input type="submit" value="Сохранить">
</form>
</body>
</html>
