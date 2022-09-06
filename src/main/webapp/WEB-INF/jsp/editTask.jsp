<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <link rel="stylesheet" href="../../css/general.css">
    <link rel="stylesheet" href="../../css/buttons.css">
    <link rel="stylesheet" href="../../css/inputs.css">
    <link rel="stylesheet" href="../../css/labels.css">
    <title>Изменить задачу</title>
</head>
<body>
<header>
        <button class="home-button" onclick="window.location.href='/'"></button>
</header>
<button class="back-button" onclick="window.location.href='/controller?command=tasksPage'">Назад</button><br><br>
<form action="<c:url value="/controller?command=editTask&id=${requestScope.task.id}"/>" method="post">
    <label for="tname">Имя:</label><br>
    <input maxlength="50" type="text" id="tname" name="tname" value="${fn:escapeXml(requestScope.task.name)}"><br><br>
    <label for="proj">Проект:</label><br>
    <c:choose>
        <c:when test="${requestScope.projectLock == true}">
            <select disabled name="proj" id="proj">
                <option selected
                        value="${requestScope.currentProject.id}">${fn:escapeXml(requestScope.currentProject.name)}</option>
            </select><br><br>
            <input type="hidden" name="proj" value="${fn:escapeXml(requestScope.currentProject.id)}">
        </c:when>
        <c:otherwise>
            <select name="proj" id="proj">
                <c:forEach var="project" items="${requestScope.projects}">
                    <option value="${project.id}"
                            <c:if test="${project.id == requestScope.task.projectId}">selected</c:if>>
                            ${fn:escapeXml(project.name)}
                    </option>
                </c:forEach>
            </select><br><br>
        </c:otherwise>
    </c:choose>
    <label for="work">Работа:</label><br>
    <input required type="number" min="0" id="work" name="work" oninvalid="this.setCustomValidity('Заполните поле')"
           oninput="setCustomValidity('')" value="${fn:escapeXml(requestScope.task.work)}"><br><br>
    <label>Дата начала</label><br>
    <label for="startYear">Год:</label>
    <input required value="${requestScope.task.startDate.toString().substring(0,4)}" type="number" min="1" max="9999" id="startYear" name="startYear"
           oninvalid="this.setCustomValidity('Заполните поле')" oninput="setCustomValidity('')">
    <label for="startMonth">Месяц:</label>
    <select class="month-select" id="startMonth" name="startMonth">
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
    <c:if test="${requestScope.dateCollision}">Неверная дата</c:if><br><br>
    <label>Дата окончания</label><br>
    <label for="endYear">Год:</label>
    <input required value="${requestScope.task.endDate.toString().substring(0,4)}" type="number" min="1" max="9999" id="endYear" name="endYear"
           oninvalid="this.setCustomValidity('Заполните поле')" oninput="setCustomValidity('')">
    <label for="endMonth">Месяц:</label>
    <select class="month-select" id="endMonth" name="endMonth">
        <option <c:if test="${requestScope.task.endDate.toString().substring(5,7) == '01'}">selected</c:if> value="01">Январь</option>
        <option <c:if test="${requestScope.task.endDate.toString().substring(5,7) == '02'}">selected</c:if> value="02">Февраль</option>
        <option <c:if test="${requestScope.task.endDate.toString().substring(5,7) == '03'}">selected</c:if> value="03">Март</option>
        <option <c:if test="${requestScope.task.endDate.toString().substring(5,7) == '04'}">selected</c:if> value="04">Апрель</option>
        <option <c:if test="${requestScope.task.endDate.toString().substring(5,7) == '05'}">selected</c:if> value="05">Май</option>
        <option <c:if test="${requestScope.task.endDate.toString().substring(5,7) == '06'}">selected</c:if> value="06">Июнь</option>
        <option <c:if test="${requestScope.task.endDate.toString().substring(5,7) == '07'}">selected</c:if> value="07">Июль</option>
        <option <c:if test="${requestScope.task.endDate.toString().substring(5,7) == '08'}">selected</c:if> value="08">Август</option>
        <option <c:if test="${requestScope.task.endDate.toString().substring(5,7) == '09'}">selected</c:if> value="09">Сентябрь</option>
        <option <c:if test="${requestScope.task.endDate.toString().substring(5,7) == '10'}">selected</c:if> value="10">Октябрь</option>
        <option <c:if test="${requestScope.task.endDate.toString().substring(5,7) == '11'}">selected</c:if> value="11">Ноябрь</option>
        <option <c:if test="${requestScope.task.endDate.toString().substring(5,7) == '12'}">selected</c:if> value="12">Декабрь</option>
    </select>
    <label for="endDay">День:</label>
    <input required value="${requestScope.task.endDate.toString().substring(8,10)}" type="number" id="endDay"
           name="endDay" min="1" max="31" oninvalid="this.setCustomValidity('Заполните поле')" oninput="setCustomValidity('')"><br><br>
    <label for="exec">Исполнитель:</label><br>
    <select name="exec" id="exec">
        <c:forEach var="employee" items="${requestScope.employees}">
        <option value="${employee.id}" <c:if test="${employee.id == requestScope.task.executorId}">selected</c:if>>
                    ${fn:escapeXml(employee.lastName)}
                    ${fn:escapeXml(employee.firstName)}
                    ${fn:escapeXml(employee.patronymic)}
            </option>
        </c:forEach>
    </select><br><br>
    <label for="stat">Статус:</label><br>
    <select name="stat" id="stat">
        <option <c:if test="${requestScope.task.status == 'NOT_STARTED'}">selected</c:if> value="NOT_STARTED">Не начата</option>
        <option <c:if test="${requestScope.task.status == 'IN_PROGRESS'}">selected</c:if> value="IN_PROGRESS">Выполняется</option>
        <option <c:if test="${requestScope.task.status == 'DONE'}">selected</c:if> value="DONE">Готова</option>
        <option <c:if test="${requestScope.task.status == 'PAUSED'}">selected</c:if> value="PAUSED">Приостановлена</option>
    </select><br><br>
    <input type="submit" value="Сохранить">
</form>
</body>
</html>
