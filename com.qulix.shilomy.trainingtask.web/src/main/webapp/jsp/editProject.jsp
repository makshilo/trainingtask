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
<a class="button" href="${pageContext.request.contextPath}/projects">Отмена</a><br>

<c:set scope="request" var="id" value="${requestScope.project.id}"/>

<c:set var="action" value="${pageContext.request.contextPath.concat(requestScope.pageMode == 'edit' ?
'/editProject?id='.concat(param.id) : '/createProject')}"/>

<c:set var="mode" scope="application" value="${requestScope.pageMode == 'edit' ? 'edit' : 'create'}"/>

<form action="${action}" method="post">
    <label for="projectName">Наименование:</label><br>

    <c:set var="projectName" value="${requestScope.project.name != null ? requestScope.project.name : param.projectName}"/>

    <input maxlength="100" type="text" id="projectName" name="projectName" value="${fn:escapeXml(projectName)}">

    <c:out value="${requestScope.errorMessages.get('nameNull')}"/><br><br>

    <label for="description">Описание:</label><br>

    <c:set var="description" value="${requestScope.project.description != null ? requestScope.project.description : param.description}"/>

    <textarea maxlength="1000" id="description" name="description" rows="4" cols="50">${fn:escapeXml(description)}</textarea>

    <c:out value="${requestScope.errorMessages.get('descriptionNull')}"/><br><br>

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
                <td>
                    <a class="table-button" href="${pageContext.request.contextPath}/editTask?id=${task.id}&currentProject=${requestScope.project.id}&projectLock">Изменить</a>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/deleteTask?id=${task.id}" method="post">
                        <button class="table-button" name="delete" type="submit">Удалить</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a class="add-button" href="${pageContext.request.contextPath}/createTask?currentProject=${requestScope.project.id}&projectLock">Добавить</a>
</c:if>
</body>
</html>
