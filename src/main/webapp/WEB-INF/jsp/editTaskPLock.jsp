<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Изменить задачу</title>
</head>
<body>
<button onclick="window.location.href='/controller?command=tasksPage'">Назад</button><br>

<form action="<c:url value="/controller?command=editTask&id=${requestScope.task.id}"/>" method="post">
    <label for="tname">Имя:</label>
    <input maxlength="50" type="text" id="tname" name="tname" value=${fn:escapeXml(requestScope.task.name)}><br><br>
    <label for="proj">Проект:</label>
    <select disabled name="proj" id="proj">
        <option selected value="${requestScope.currentProject.id}"> ${fn:escapeXml(requestScope.currentProject.name)}</option>
    </select>
    <input type="hidden" name="proj" value="${fn:escapeXml(requestScope.currentProject.id)}"><br><br>
    <label for="work">Работа:</label>
    <input required type="number" id="work" name="work" value=${fn:escapeXml(requestScope.task.work)}><br><br>
    <fieldset>
        <legend>Дата</legend>
        <label>Формат гггг-мм-дд</label><br><br>
        <label for="start">Начало:</label>
        <input required pattern="\d{4}-\d{2}-\d{2}" type="text" id="start" name="start" value="${fn:escapeXml(requestScope.task.startDate)}"><br><br>
        <label for="end">Конец:</label>
        <input required pattern="\d{4}-\d{2}-\d{2}" type="text" id="end" name="end" value="${fn:escapeXml(requestScope.task.endDate)}"><br><br>
    </fieldset>
    <label for="exec">Исполнитель:</label>
    <select required name="exec" id="exec">
        <option selected value="${requestScope.currentExecutor.id}">
            *${fn:escapeXml(requestScope.currentExecutor.firstName)}
            ${fn:escapeXml(requestScope.currentExecutor.lastName)}</option>
        <option value="">None</option>
        <c:forEach var="employee" items="${requestScope.employees}">
            <option value="${employee.id}">
                    ${fn:escapeXml(employee.lastName)}
                    ${fn:escapeXml(employee.firstName)}
                    ${fn:escapeXml(employee.patronymic)}
            </option>
        </c:forEach>
    </select><br><br>
    <label for="stat">Статус:</label>
    <select name="stat" id="stat">
        <option selected value="${requestScope.task.status}">
            *${requestScope.status.get(task.status)}</option>
        <option value="NOT_STARTED">Не начата</option>
        <option value="IN_PROGRESS">Выполняется</option>
        <option value="DONE">Готова</option>
        <option value="PAUSED">Приостановлена</option>
    </select><br><br>
    <input type="submit" value="Сохранить">
</form>
</body>
</html>