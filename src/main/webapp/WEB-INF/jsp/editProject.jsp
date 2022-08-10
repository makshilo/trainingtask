<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Изменить проект</title>
</head>
<body>
<button onclick="window.location.href='/controller?command=projectsPage'">Назад</button><br>
<form action="<c:url value="/controller?command=editProject&id=${requestScope.project.id}"/>" method="post">
    <label for="pname">Имя проекта:</label>
    <input maxlength="100" required type="text" id="pname" name="pname" value=${requestScope.project.name}><br><br>
    <label for="descr">Описание:</label>
    <textarea maxlength="1000" id="descr" name="descr" rows="4" cols="50">${requestScope.project.description}</textarea><br><br>
    <input type="submit" value="Сохранить">
</form>
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
            <td>${task.status}</td>
            <td>${task.name}</td>
            <td>${requestScope.projects.get(task.projectId)}</td>
            <td>${task.work}</td>
            <td>${task.startDate}</td>
            <td>${task.endDate}</td>
            <td>${requestScope.employees.get(task.executorId)}</td>
            <td><button onclick="window.location.href='/controller?command=taskEditFromProjectPage&id=${task.id}'">Изменить</button></td>
            <td><button onclick="window.location.href='/controller?command=deleteTask&id=${task.id}'">Удалить</button></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
