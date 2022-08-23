<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Изменить задачу</title>
</head>
<body>
<button onclick="window.location.href='/controller?command=tasksPage'">Назад</button><br><br>
<form action="<c:url value="/controller?command=editTask&id=${requestScope.task.id}"/>" method="post">
    <label for="tname">Имя:</label>
    <input maxlength="50" type="text" id="tname" name="tname" value=${fn:escapeXml(requestScope.task.name)}><br><br>
    <label for="proj">Проект:</label>
    <select name="proj" id="proj">
        <c:forEach var="project" items="${requestScope.projects}">
            <option value="${project.id}" <c:if test="${project.id} == ${requestScope.currentProject.id}">selected</c:if>>
                    ${fn:escapeXml(project.name)}
            </option>
        </c:forEach>
    </select><br><br>
    <label for="work">Работа:</label>
    <input required type="number" id="work" name="work" oninvalid="this.setCustomValidity('Заполните поле')"
           oninput="setCustomValidity('')" value=${fn:escapeXml(requestScope.task.work)}><br><br>
    <fieldset>
        <legend>Дата начала</legend>
        <label for="startYear">Год:</label>
        <input required value="${requestScope.task.startDate.toString().substring(0,4)}" type="number" id="startYear" name="startYear"
               oninvalid="this.setCustomValidity('Заполните поле')" oninput="setCustomValidity('')">
        <label for="startMonth">Месяц:</label>
        <select id="startMonth" name="startMonth">
            <option <c:if test="${requestScope.task.startDate.toString().substring(5,7) == '01'}">selected</c:if> value="01">Январь</option>
            <option <c:if test="${requestScope.task.startDate.toString().substring(5,7) == '02'}">selected</c:if> value="02">Февраль</option>
            <option <c:if test="${requestScope.task.startDate.toString().substring(5,7) == '03'}">selected</c:if> value="03">Март</option>
            <option <c:if test="${requestScope.task.startDate.toString().substring(5,7) == '04'}">selected</c:if> value="04">Апрель</option>
            <option <c:if test="${requestScope.task.startDate.toString().substring(5,7) == '05'}">selected</c:if> value="05">Май</option>
            <option <c:if test="${requestScope.task.startDate.toString().substring(5,7) == '06'}">selected</c:if> value="06">Июнь</option>
            <option <c:if test="${requestScope.task.startDate.toString().substring(5,7) == '07'}">selected</c:if> value="07">Июль</option>
            <option <c:if test="${requestScope.task.startDate.toString().substring(5,7) == '08'}">selected</c:if> value="08">Август</option>
            <option <c:if test="${requestScope.task.startDate.toString().substring(5,7) == '09'}">selected</c:if> value="09">Сентябрь</option>
            <option <c:if test="${requestScope.task.startDate.toString().substring(5,7) == '10'}">selected</c:if> value="10">Октябрь</option>
            <option <c:if test="${requestScope.task.startDate.toString().substring(5,7) == '11'}">selected</c:if> value="11">Ноябрь</option>
            <option <c:if test="${requestScope.task.startDate.toString().substring(5,7) == '12'}">selected</c:if> value="12">Декабрь</option>
        </select>
        <label for="startDay">День:</label>
        <input required value="${requestScope.task.startDate.toString().substring(8,10)}" type="number" id="startDay"
               name="startDay" min="1" max="31" oninvalid="this.setCustomValidity('Заполните поле')" oninput="setCustomValidity('')">
    </fieldset>
    <fieldset>
        <legend>Дата окончания</legend>
        <label for="endYear">Год:</label>
        <input required value="${requestScope.task.endDate.toString().substring(0,4)}" type="number" id="endYear" name="endYear"
               oninvalid="this.setCustomValidity('Заполните поле')" oninput="setCustomValidity('')">
        <label for="endMonth">Месяц:</label>
        <select id="endMonth" name="endMonth">
            <option <c:if test="${requestScope.task.startDate.toString().substring(5,7) == '01'}">selected</c:if> value="01">Январь</option>
            <option <c:if test="${requestScope.task.startDate.toString().substring(5,7) == '02'}">selected</c:if> value="02">Февраль</option>
            <option <c:if test="${requestScope.task.startDate.toString().substring(5,7) == '03'}">selected</c:if> value="03">Март</option>
            <option <c:if test="${requestScope.task.startDate.toString().substring(5,7) == '04'}">selected</c:if> value="04">Апрель</option>
            <option <c:if test="${requestScope.task.startDate.toString().substring(5,7) == '05'}">selected</c:if> value="05">Май</option>
            <option <c:if test="${requestScope.task.startDate.toString().substring(5,7) == '06'}">selected</c:if> value="06">Июнь</option>
            <option <c:if test="${requestScope.task.startDate.toString().substring(5,7) == '07'}">selected</c:if> value="07">Июль</option>
            <option <c:if test="${requestScope.task.startDate.toString().substring(5,7) == '08'}">selected</c:if> value="08">Август</option>
            <option <c:if test="${requestScope.task.startDate.toString().substring(5,7) == '09'}">selected</c:if> value="09">Сентябрь</option>
            <option <c:if test="${requestScope.task.startDate.toString().substring(5,7) == '10'}">selected</c:if> value="10">Октябрь</option>
            <option <c:if test="${requestScope.task.startDate.toString().substring(5,7) == '11'}">selected</c:if> value="11">Ноябрь</option>
            <option <c:if test="${requestScope.task.startDate.toString().substring(5,7) == '12'}">selected</c:if> value="12">Декабрь</option>
        </select>
        <label for="endDay">День:</label>
        <input required value="${requestScope.task.endDate.toString().substring(8,10)}" type="number" id="endDay"
               name="endDay" min="1" max="31" oninvalid="this.setCustomValidity('Заполните поле')" oninput="setCustomValidity('')">
    </fieldset><br>
    <label for="exec">Исполнитель:</label>
    <select name="exec" id="exec">
        <c:forEach var="employee" items="${requestScope.employees}">
        <option value="${employee.id}" <c:if test="${employee.id} == ${requestScope.currentExecutor.id}">selected</c:if>>
                    ${fn:escapeXml(employee.lastName)}
                    ${fn:escapeXml(employee.firstName)}
                    ${fn:escapeXml(employee.patronymic)}
            </option>
        </c:forEach>
    </select><br><br>
    <label for="stat">Статус:</label>
    <select name="stat" id="stat">
        <option <c:if test="${requestScope.task.status} == 'NOT_STARTED'">selected</c:if> value="NOT_STARTED">Не начата</option>
        <option <c:if test="${requestScope.task.status} == 'IN_PROGRESS'">selected</c:if> value="IN_PROGRESS">Выполняется</option>
        <option <c:if test="${requestScope.task.status} == 'DONE'">selected</c:if> value="DONE">Готова</option>
        <option <c:if test="${requestScope.task.status} == 'PAUSED'">selected</c:if> value="PAUSED">Приостановлена</option>
    </select><br><br>
    <input type="submit" value="Сохранить">
</form>
</body>
</html>
