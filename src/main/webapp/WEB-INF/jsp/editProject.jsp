<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Изменить проект</title>
</head>
<body>
<button onclick="window.location.href='/controller?command=projectsPage'">Назад</button><br>
<form action="<c:url value="/controller?command=editProject&id=${requestScope.project.id}"/>" method="post">
    <label for="pname">Имя проекта:</label>
    <input maxlength="100" required type="text" id="pname" name="pname" oninvalid="this.setCustomValidity('Заполните поле')"
           oninput="setCustomValidity('')" value=${fn:escapeXml(requestScope.project.name)}>
    <c:if test="${requestScope.projectNameBusy}">Имя проекта занято</c:if><br><br>
    <label for="descr">Описание:</label>
    <textarea maxlength="1000" id="descr" name="descr" rows="4" cols="50">${fn:escapeXml(requestScope.project.description)}</textarea><br><br>
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
            <td><c:out value="${requestScope.status.get(task.status)}"/></td>
            <td><c:out value="${task.name}"/></td>
            <td><c:out value="${requestScope.projects.get(task.projectId)}"/></td>
            <td><c:out value="${task.work}"/></td>
            <td><c:out value="${task.startDate}"/></td>
            <td><c:out value="${task.endDate}"/></td>
            <td><c:out value="${requestScope.employees.get(task.executorId)}"/></td>
            <td><button onclick="window.location.href='/controller?command=taskEditPage&id=${task.id}&projectLock'">Изменить</button></td>
            <td><button onclick="window.location.href='/controller?command=deleteTask&id=${task.id}'">Удалить</button></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
