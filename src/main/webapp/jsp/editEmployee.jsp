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
        <button class="home-button" onclick="window.location.href='..'"></button>
</header>
<button class="back-button" onclick="window.location.href='/controller?command=employeesPage'">Назад</button><br>
<h1>Страница изменения сотрудника</h1>

<c:set scope="request" var="id" value="${requestScope.employee.id}"/>

<form action="<c:url value="/controller?command=editEmployee&id=${param.id}"/>" method="post">
    <label for="firstName">Имя:</label><br>
    <c:choose>
        <c:when test="${requestScope.employee.firstName != null}">
            <c:set var="firstName" value="${requestScope.employee.firstName}"/>
        </c:when>
        <c:otherwise>
            <c:set var="firstName" value="${fn:escapeXml(param.firstName)}"/>
        </c:otherwise>
    </c:choose>
    <input maxlength="50" type="text" id="firstName" name="firstName" value="${firstName}">
    <c:if test="${requestScope.firstNameNull}">Заполните поле</c:if><br><br>
    <label for="lastName">Фамилия:</label><br>
    <c:choose>
        <c:when test="${requestScope.employee.lastName != null}">
            <c:set var="lastName" value="${requestScope.employee.lastName}"/>
        </c:when>
        <c:otherwise>
            <c:set var="lastName" value="${fn:escapeXml(param.lastName)}"/>
        </c:otherwise>
    </c:choose>
    <input maxlength="50" type="text" id="lastName" name="lastName" value="${lastName}">
    <c:if test="${requestScope.lastNameNull}">Заполните поле</c:if><br><br>
    <label for="patronymic">Отчество:</label><br>
    <c:choose>
        <c:when test="${requestScope.employee.patronymic != null}">
            <c:set var="patronymic" value="${requestScope.employee.patronymic}"/>
        </c:when>
        <c:otherwise>
            <c:set var="patronymic" value="${fn:escapeXml(param.patronymic)}"/>
        </c:otherwise>
    </c:choose>
    <input maxlength="50" type="text" id="patronymic" name="patronymic" value="${patronymic}">
    <c:if test="${requestScope.patronymicNull}">Заполните поле</c:if><br><br>
    <label for="position">Должность:</label><br>
    <c:choose>
        <c:when test="${requestScope.employee.position != null}">
            <c:set var="position" value="${requestScope.employee.position}"/>
        </c:when>
        <c:otherwise>
            <c:set var="position" value="${fn:escapeXml(param.position)}"/>
        </c:otherwise>
    </c:choose>
    <input maxlength="50" type="text" id="position" name="position" value="${position}">
    <c:if test="${requestScope.positionNull}">Заполните поле</c:if><br><br>
    <input type="submit" value="Сохранить">
</form>
</body>
</html>
