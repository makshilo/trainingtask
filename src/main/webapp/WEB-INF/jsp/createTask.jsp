<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Добавить задачу</title>
</head>
<body>
<button onclick="window.location.href='/controller?command=tasksPage'">Назад</button><br>
<form action="<c:url value="/controller?command=createTask"/>" method="post">
    <label for="tname">Имя:</label>
    <input maxlength="50" required type="text" id="tname" name="tname"><br><br>
    <label for="proj">Проект:</label>
    <select name="proj" id="proj" required>
        <option selected value="">None</option>
        <c:forEach var="project" items="${requestScope.projects}">
            <option value="${project.id}">
                    ${project.name}
            </option>
        </c:forEach>
    </select><br><br>
    <label for="work">Работа:</label>
    <input required type="number" id="work" name="work" value="0"><br><br>
    <fieldset>
        <legend>Дата</legend>
        <label>Формат гггг-мм-дд</label><br><br>
        <label for="start">Начало:</label>
        <input required pattern="\d{4}-\d{2}-\d{2}" type="text" id="start" name="start"><br><br>
        <label for="end">Конец:</label>
        <input required pattern="\d{4}-\d{2}-\d{2}" type="text" id="end" name="end"><br><br>
    </fieldset>
    <label for="exec">Исполнитель:</label>
    <select name="exec" id="exec" required>
        <option selected value="">None</option>
        <c:forEach var="employee" items="${requestScope.employees}">
            <option value="${employee.id}">
                ${employee.lastName}
                ${employee.firstName}
                ${employee.patronymic}
            </option>
        </c:forEach>
    </select><br><br>
    <label for="stat">Статус:</label>
    <select name="stat" id="stat">
        <option value="NOT_STARTED">Не начата</option>
        <option value="IN_PROGRESS">Выполняется</option>
        <option value="DONE">Готова</option>
        <option value="PAUSED">Приостановлена</option>
    </select><br><br>
    <input type="submit" value="Сохранить">
</form>
</body>
</html>
