<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="../css/general.css">
    <link rel="stylesheet" href="../css/buttons.css">
    <link rel="stylesheet" href="../css/tables.css">
    <title>Список задач</title>
</head>
<body>
<header>
    <a class="home-button" href=".."></a>
</header>
<a class="button" href="..">Назад</a><br>
<h3>Задачи</h3>
<table>
    <tr>
        <th>Статус</th>
        <th>Наименование</th>
        <th>Наименование проекта</th>
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
            <td><c:out value="${requestScope.projects.get(task.projectId)}"/></td>
            <td><c:out value="${task.work}"/></td>
            <td><c:out value="${task.startDate}"/></td>
            <td><c:out value="${task.endDate}"/></td>
            <td><c:out value="${requestScope.employees.get(task.executorId)}"/></td>
            <td><a class="table-button" href="<c:url value="/tasks?action=taskEditPage&id=${task.id}"/>">Изменить</a></td>
            <td><a class="table-button" href="<c:url value="/tasks?action=deleteTask&id=${task.id}"/>">Удалить</a></td>
        </tr>
    </c:forEach>
</table>
<a class="add-button" href="<c:url value="/tasks?action=createTaskPage"/>">Добавить</a>
</body>
</html>
