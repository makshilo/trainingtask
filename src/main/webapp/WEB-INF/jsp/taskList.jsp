<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Список задач</title>
</head>
<body>
<button onclick="window.location.href='/'">Назад</button><br>
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
            <td><c:out value="${requestScope.status.get(task.status)}"/></td>
            <td><c:out value="${task.name}"/></td>
            <td><c:out value="${requestScope.projects.get(task.projectId)}"/></td>
            <td><c:out value="${task.work}"/></td>
            <td><c:out value="${task.startDate}"/></td>
            <td><c:out value="${task.endDate}"/></td>
            <td><c:out value="${requestScope.employees.get(task.executorId)}"/></td>
            <td><button onclick="window.location.href='/controller?command=taskEditPage&id=${task.id}'">Изменить</button></td>
            <td><button onclick="window.location.href='/controller?command=deleteTask&id=${task.id}'">Удалить</button></td>
        </tr>
    </c:forEach>
</table>
<button onclick="window.location.href='/controller?command=taskCreatePage'">Добавить</button>
</body>
</html>
