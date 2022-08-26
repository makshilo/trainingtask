<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="../../css/general.css">
    <link rel="stylesheet" href="../../css/buttons.css">
    <title>Список задач</title>
</head>
<body>
<header>
        <button class="home-button" onclick="window.location.href='/'"></button>
</header>
<button class="back-button" onclick="window.location.href='/'">Назад</button><br>
<h3>Задачи</h3>
<table>
    <tr>
        <th>Статус</th>
        <th>Имя</th>
        <th>Проект</th>
        <th>Работа</th>
        <th>Начало</th>
        <th>Конец</th>
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
            <td><button class="table-button" onclick="window.location.href='/controller?command=taskEditPage&id=${task.id}'">Изменить</button></td>
            <td><button class="table-button" onclick="window.location.href='/controller?command=deleteTask&id=${task.id}'">Удалить</button></td>
        </tr>
    </c:forEach>
</table>
<button class="add-button" onclick="window.location.href='/controller?command=taskCreatePage'">Добавить</button>
</body>
</html>
