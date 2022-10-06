<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <link rel="stylesheet" href="../css/general.css">
    <link rel="stylesheet" href="../css/buttons.css">
    <link rel="stylesheet" href="../css/inputs.css">
    <link rel="stylesheet" href="../css/labels.css">

    <title>Изменить проект</title>
</head>
<body>
<header>
    <a class="home-button" href="<c:url value=".."/>"></a>
</header>
<a class="button" href="<c:url value="/controller?command=projectsPage"/>">Отмена</a><br>

<c:set scope="request" var="id" value="${requestScope.project.id}"/>

<form action="<c:url value="/controller?command=editProject&id=${param.id}"/>" method="post">
    <label for="projectName">Наименование:</label><br>
    <c:choose>
        <c:when test="${requestScope.project.name != null}">
            <c:set var="projectName" value="${requestScope.project.name}"/>
        </c:when>
        <c:otherwise>
            <c:set var="projectName" value="${fn:escapeXml(param.projectName)}"/>
        </c:otherwise>
    </c:choose>
    <input maxlength="100" type="text" id="projectName" name="projectName" value="${projectName}">
    <c:if test="${requestScope.projectNameNull}">Заполните поле</c:if><br><br>
    <label for="description">Описание:</label><br>
    <c:choose>
        <c:when test="${requestScope.project.description != null}">
            <c:set var="description" value="${requestScope.project.description}"/>
        </c:when>
        <c:otherwise>
            <c:set var="description" value="${fn:escapeXml(param.description)}"/>
        </c:otherwise>
    </c:choose>
    <textarea maxlength="1000" id="description" name="description" rows="4" cols="50">${description}</textarea>
    <c:if test="${requestScope.projectDescriptionNull}">Заполните поле</c:if><br><br>
    <input type="submit" value="Сохранить">
</form>
<table>
    <tr>
        <th>Статус</th>
        <th>Наименование</th>
        <th>Работа</th>
        <th>Дата начала</th>
        <th>Дата окончания</th>
        <th>Исполнитель</th>
    </tr>
    <c:forEach var="task" items="${requestScope.tasks}">
        <tr>
            <td>
                <c:choose>
                    <c:when test="${task.status == 'NOT_STARTED'}">Не начата</c:when>
                    <c:when test="${task.status == 'IN_PROGRESS'}">Выполняется</c:when>
                    <c:when test="${task.status == 'DONE'}">Готова</c:when>
                    <c:when test="${task.status == 'PAUSED'}">Приостановлена</c:when>
                </c:choose>
            </td>
            <td><c:out value="${task.name}"/></td>
            <td><c:out value="${task.work}"/></td>
            <td><c:out value="${task.startDate}"/></td>
            <td><c:out value="${task.endDate}"/></td>
            <td><c:out value="${requestScope.employees.get(task.executorId)}"/></td>
            <td><a class="table-button" href="<c:url value="/controller?command=taskEditPage&id=${task.id}&projectLock"/>">Изменить</a></td>
            <td><a class="table-button" href="<c:url value="/controller?command=deleteTask&id=${task.id}"/>">Удалить</a></td>
        </tr>
    </c:forEach>
</table>
<a class="add-button" href="<c:url value="/controller?command=taskCreatePage&currentProject=${requestScope.project.id}&projectLock"/>">Добавить</a>
</body>
</html>
