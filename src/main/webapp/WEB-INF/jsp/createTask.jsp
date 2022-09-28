<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <link rel="stylesheet" href="../../css/general.css">
    <link rel="stylesheet" href="../../css/buttons.css">
    <link rel="stylesheet" href="../../css/inputs.css">
    <link rel="stylesheet" href="../../css/labels.css">
    <title>Добавить задачу</title>
</head>
<body>
<header>
        <button class="home-button" onclick="window.location.href='/'"></button>
</header>
<button class="back-button" onclick="window.location.href='/controller?command=tasksPage'">Отмена</button><br><br>
<form action="<c:url value="/controller?command=createTask"/>" method="post">
    <label for="tname">Наименование:</label><br>
    <input maxlength="50" value="<c:out value="${param.tname}"/>" required type="text" id="tname" name="tname"
           oninvalid="this.setCustomValidity('Заполните поле')" oninput="setCustomValidity('')"><br><br>
    <label for="proj">Наименование проекта:</label><br>
    <c:choose>
        <c:when test="${requestScope.projectLock == true}">
            <select disabled name="proj" id="proj">
                <option selected
                        value="${requestScope.currentProject.id}"> ${fn:escapeXml(requestScope.currentProject.name)}</option>
            </select><br><br>
            <input type="hidden" name="proj" value="${fn:escapeXml(requestScope.currentProject.id)}">
        </c:when>
        <c:otherwise>
            <select name="proj" id="proj">
                <c:forEach var="project" items="${requestScope.projects}">
                    <option value="${project.id}"
                            <c:if test="${project.id == param.proj}">selected</c:if>>
                            ${fn:escapeXml(project.name)}
                    </option>
                </c:forEach>
            </select><br><br>
        </c:otherwise>
    </c:choose>
    <label for="work">Работа:</label><br>
    <input required type="number" id="work" name="work" value="${fn:escapeXml(param.work)}" min="0"
           oninvalid="this.setCustomValidity('Заполните поле')" oninput="setCustomValidity('')"><br><br>
    <label>Дата начала</label><br>
    <label for="startYear">Год:</label>
    <input required value="${param.startYear}"
           type="number" min="1" max="9999" id="startYear" name="startYear"
           oninvalid="this.setCustomValidity('Заполните поле')" oninput="setCustomValidity('')">
    <c:if test="${requestScope.invalidStartYear}">Неверный год</c:if>
    <label for="startMonth">Месяц:</label>
    <select class="month-select" id="startMonth" name="startMonth">
        <option <c:if test="${param.startMonth == '01'}">selected</c:if> value="01">Январь</option>
        <option <c:if test="${param.startMonth == '02'}">selected</c:if> value="02">Февраль</option>
        <option <c:if test="${param.startMonth == '03'}">selected</c:if> value="03">Март</option>
        <option <c:if test="${param.startMonth == '04'}">selected</c:if> value="04">Апрель</option>
        <option <c:if test="${param.startMonth == '05'}">selected</c:if> value="05">Май</option>
        <option <c:if test="${param.startMonth == '06'}">selected</c:if> value="06">Июнь</option>
        <option <c:if test="${param.startMonth == '07'}">selected</c:if> value="07">Июль</option>
        <option <c:if test="${param.startMonth == '08'}">selected</c:if> value="08">Август</option>
        <option <c:if test="${param.startMonth == '09'}">selected</c:if> value="09">Сентябрь</option>
        <option <c:if test="${param.startMonth == '10'}">selected</c:if> value="10">Октябрь</option>
        <option <c:if test="${param.startMonth == '11'}">selected</c:if> value="11">Ноябрь</option>
        <option <c:if test="${param.startMonth == '12'}">selected</c:if> value="12">Декабрь</option>
    </select>
    <label for="startDay">День:</label>
    <input required value="${param.startDay}" type="number" id="startDay"
           name="startDay" min="1" max="31" oninvalid="this.setCustomValidity('Заполните поле')" oninput="setCustomValidity('')">
    <c:if test="${requestScope.invalidStartDay}">Неверный день</c:if>
    <c:if test="${requestScope.wrongStartDate}">Неверная дата начала</c:if><br><br>
    <label>Дата окончания</label><br>
    <label for="endYear">Год:</label>
    <input required value="${param.endYear}" type="number" min="1" max="9999" id="endYear" name="endYear"
           oninvalid="this.setCustomValidity('Заполните поле')" oninput="setCustomValidity('')">
    <c:if test="${requestScope.invalidEndYear}">Неверный год</c:if>
    <label for="endMonth">Месяц:</label>
    <select class="month-select" id="endMonth" name="endMonth">
        <option <c:if test="${param.endMonth == '01'}">selected</c:if> value="01">Январь</option>
        <option <c:if test="${param.endMonth == '02'}">selected</c:if> value="02">Февраль</option>
        <option <c:if test="${param.endMonth == '03'}">selected</c:if> value="03">Март</option>
        <option <c:if test="${param.endMonth == '04'}">selected</c:if> value="04">Апрель</option>
        <option <c:if test="${param.endMonth == '05'}">selected</c:if> value="05">Май</option>
        <option <c:if test="${param.endMonth == '06'}">selected</c:if> value="06">Июнь</option>
        <option <c:if test="${param.endMonth == '07'}">selected</c:if> value="07">Июль</option>
        <option <c:if test="${param.endMonth == '08'}">selected</c:if> value="08">Август</option>
        <option <c:if test="${param.endMonth == '09'}">selected</c:if> value="09">Сентябрь</option>
        <option <c:if test="${param.endMonth == '10'}">selected</c:if> value="10">Октябрь</option>
        <option <c:if test="${param.endMonth == '11'}">selected</c:if> value="11">Ноябрь</option>
        <option <c:if test="${param.endMonth == '12'}">selected</c:if> value="12">Декабрь</option>
    </select>
    <label for="endDay">День:</label>
    <input required value="${param.endDay}" type="number" id="endDay"
           name="endDay" min="1" max="31" oninvalid="this.setCustomValidity('Заполните поле')" oninput="setCustomValidity('')">
    <c:if test="${requestScope.invalidEndDay}">Неверный день</c:if>
    <c:if test="${requestScope.wrongEndDate}">Неверная дата окончания</c:if><br><br>
    <c:if test="${requestScope.dateCollision}">Дата начала больше даты окончания<br><br></c:if>
    <label for="stat">Статус:</label><br>
    <select name="stat" id="stat">
        <option <c:if test="${param.stat == 'NOT_STARTED'}">selected</c:if> value="NOT_STARTED">Не начата</option>
        <option <c:if test="${param.stat == 'IN_PROGRESS'}">selected</c:if> value="IN_PROGRESS">В процессе</option>
        <option <c:if test="${param.stat == 'DONE'}">selected</c:if> value="DONE">Завершена</option>
        <option <c:if test="${param.stat == 'PAUSED'}">selected</c:if> value="PAUSED">Отложена</option>
    </select><br><br>
    <label for="exec">Исполнитель:</label><br>
    <select name="exec" id="exec" required oninvalid="this.setCustomValidity('Выберите исполнителя')" oninput="setCustomValidity('')">
        <c:forEach var="employee" items="${requestScope.employees}">
            <option value="${employee.id}"
                    <c:if test="${employee.id == param.exec}">selected</c:if>>
                    ${employee.lastName}
                    ${employee.firstName}
                    ${employee.patronymic}
            </option>
        </c:forEach>
    </select><br><br>
    <input type="submit" value="Сохранить">
</form>
</body>
</html>
