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
                <c:forEach var="status" items="${requestScope.status}">
                    <c:if test="${status == task.status}">${status.getStatus()}</c:if>
                </c:forEach>
            </td>
            <td><c:out value="${task.name}"/></td>
            <td><c:out value="${requestScope.projects.get(task.projectId)}"/></td>
            <td><c:out value="${task.work}"/></td>
            <td><c:out value="${task.startDate}"/></td>
            <td><c:out value="${task.endDate}"/></td>
            <td><c:out value="${requestScope.employees.get(task.executorId)}"/></td>
            <td><a class="table-button" href="${pageContext.request.contextPath}/editTaskPage?id=${task.id}">Изменить</a></td>
            <td>
                <form action="${pageContext.request.contextPath}/deleteTask?id=${task.id}" method="post">
                    <button class="table-button" name="delete" type="submit">Удалить</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<a class="add-button" href="${pageContext.request.contextPath}/createTaskPage">Добавить</a>
</body>
</html>
