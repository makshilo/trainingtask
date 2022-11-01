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
<a class="button" href="<c:url value="/employees"/>">Отмена</a><br>
<h1>Страница изменения сотрудника</h1>

<c:set scope="request" var="id" value="${requestScope.employee.id}"/>

<c:choose>
    <c:when test="${requestScope.pageMode == 'create'}">
        <c:url value="/createEmployee" var="action"/>
    </c:when>
    <c:when test="${requestScope.pageMode == 'edit'}">
        <c:url value="/editEmployee?id=${param.id}" var="action"/>
    </c:when>
</c:choose>

<form action="${action}" method="post">
    <label for="firstName">Имя:</label><br>
    <c:choose>
        <c:when test="${requestScope.employee.firstName != null}">
            <c:set var="firstName" value="${requestScope.employee.firstName}"/>
        </c:when>
        <c:otherwise>
            <c:set var="firstName" value="${param.firstName}"/>
        </c:otherwise>
    </c:choose>
    <input maxlength="50" type="text" id="firstName" name="firstName" value="${fn:escapeXml(firstName)}">
    <c:if test="${requestScope.validationError == 'firstNameNull'}">Заполните поле</c:if><br><br>
    <label for="lastName">Фамилия:</label><br>
    <c:choose>
        <c:when test="${requestScope.employee.lastName != null}">
            <c:set var="lastName" value="${requestScope.employee.lastName}"/>
        </c:when>
        <c:otherwise>
            <c:set var="lastName" value="${param.lastName}"/>
        </c:otherwise>
    </c:choose>
    <input maxlength="50" type="text" id="lastName" name="lastName" value="${fn:escapeXml(lastName)}">
    <c:if test="${requestScope.validationError == 'lastNameNull'}">Заполните поле</c:if><br><br>
    <label for="patronymic">Отчество:</label><br>
    <c:choose>
        <c:when test="${requestScope.employee.patronymic != null}">
            <c:set var="patronymic" value="${requestScope.employee.patronymic}"/>
        </c:when>
        <c:otherwise>
            <c:set var="patronymic" value="${param.patronymic}"/>
        </c:otherwise>
    </c:choose>
    <input maxlength="50" type="text" id="patronymic" name="patronymic" value="${fn:escapeXml(patronymic)}">
    <c:if test="${requestScope.validationError == 'patronymicNull'}">Заполните поле</c:if><br><br>
    <label for="position">Должность:</label><br>
    <c:choose>
        <c:when test="${requestScope.employee.position != null}">
            <c:set var="position" value="${requestScope.employee.position}"/>
        </c:when>
        <c:otherwise>
            <c:set var="position" value="${param.position}"/>
        </c:otherwise>
    </c:choose>
    <input maxlength="50" type="text" id="position" name="position" value="${fn:escapeXml(position)}">
    <c:if test="${requestScope.validationError == 'positionNull'}">Заполните поле</c:if><br><br>
    <input type="submit" value="Сохранить">
</form>
</body>
</html>
