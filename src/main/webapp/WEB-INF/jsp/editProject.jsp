<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <link rel="stylesheet" href="../../css/general.css">
    <link rel="stylesheet" href="../../css/buttons.css">
    <link rel="stylesheet" href="../../css/inputs.css">
    <link rel="stylesheet" href="../../css/labels.css">

    <title>Изменить проект</title>
</head>
<body>
<header>
        <button class="home-button" onclick="window.location.href='/'"></button>
</header>
<button class="back-button" onclick="window.location.href='/controller?command=projectsPage'">Отмена</button><br>
<form action="<c:url value="/controller?command=editProject&id=${requestScope.project.id}"/>" method="post">
    <label for="pname">Наименование:</label><br>
    <input maxlength="100" required type="text" id="pname" name="pname" oninvalid="this.setCustomValidity('Заполните поле')"
           oninput="setCustomValidity('')" value="${fn:escapeXml(requestScope.project.name)}"><br>
    <label for="descr">Описание:</label><br>
    <textarea maxlength="1000" id="descr" name="descr" rows="4" cols="50">${fn:escapeXml(requestScope.project.description)}</textarea><br><br>
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
            <td><button class="table-button" onclick="window.location.href='/controller?command=taskEditPage&id=${task.id}&projectLock'">Изменить</button></td>
            <td><button class="table-button" onclick="window.location.href='/controller?command=deleteTask&id=${task.id}'">Удалить</button></td>
        </tr>
    </c:forEach>
</table>
<button class="add-button" onclick="window.location.href='/controller?command=taskCreatePage&currentProject=${requestScope.project.id}&projectLock'">Добавить</button>
</body>
</html>
