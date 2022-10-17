<!DOCTYPE html>
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
    <a class="home-button" href=".."></a>
</header>
<a class="button" href="<c:url value="/controller?command=projectsPage"/>">Отмена</a><br>

<c:set scope="request" var="id" value="${requestScope.project.id}"/>

<c:choose>
    <c:when test="${requestScope.pageMode == 'create'}">
        <c:url value="/controller?command=createProject" var="action"/>
        <c:set scope="application" var="mode" value="create"/>
    </c:when>
    <c:when test="${requestScope.pageMode == 'edit'}">
        <c:url value="/controller?command=editProject&id=${param.id}" var="action"/>
        <c:set scope="application" var="mode" value="edit"/>
    </c:when>
</c:choose>

<form action="${action}" method="post">
    <label for="projectName">Наименование:</label><br>
    <c:choose>
        <c:when test="${requestScope.project.name != null}">
            <c:set var="projectName" value="${requestScope.project.name}"/>
        </c:when>
        <c:otherwise>
            <c:set var="projectName" value="${param.projectName}"/>
        </c:otherwise>
    </c:choose>
    <input maxlength="100" type="text" id="projectName" name="projectName" value="${fn:escapeXml(projectName)}">
    <c:if test="${requestScope.validationError == 'projectNameNull'}">Заполните поле</c:if>
    <c:if test="${requestScope.validationError == 'projectIsFound'}">Такой проект уже существует</c:if><br><br>
    <label for="description">Описание:</label><br>
    <c:choose>
        <c:when test="${requestScope.project.description != null}">
            <c:set var="description" value="${requestScope.project.description}"/>
        </c:when>
        <c:otherwise>
            <c:set var="description" value="${param.description}"/>
        </c:otherwise>
    </c:choose>
    <textarea maxlength="1000" id="description" name="description" rows="4" cols="50">${fn:escapeXml(description)}</textarea>
    <c:if test="${requestScope.validationError == 'projectDescriptionNull'}">Заполните поле</c:if><br><br>
    <input type="submit" value="Сохранить">
</form>
<c:if test="${mode == 'edit'}">
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
</c:if>
</body>
</html>
