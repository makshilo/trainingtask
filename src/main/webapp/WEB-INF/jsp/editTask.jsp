<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<button class="back-button" onclick="window.location.href='/controller?command=tasksPage'">Отмена</button><br><br>
<c:set scope="request" var="id" value="${requestScope.task.id}"/>
<form action="<c:url value="/controller?command=editTask&id=${param.id}"/>" method="post">
    <label for="tname">Наименование:</label><br>
    <input maxlength="50" type="text" id="tname" name="tname"
           <c:if test="${requestScope.task.name != null}">value="${fn:escapeXml(requestScope.task.name)}"</c:if>
           <c:if test="${requestScope.task.name == null}">value="${fn:escapeXml(param.tname)}"</c:if>><br><br>
    <label for="proj">Наименование проекта:</label><br>
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
                            <c:if test="${project.id == requestScope.task.projectId}">selected</c:if>
                            <c:if test="${project.id == param.proj}">selected</c:if>>
                            ${fn:escapeXml(project.name)}
                    </option>
                </c:forEach>
            </select><br><br>
        </c:otherwise>
    </c:choose>
    <label for="work">Работа:</label><br>
    <input required type="number" min="0" id="work" name="work" oninvalid="this.setCustomValidity('Заполните поле')"
           oninput="setCustomValidity('')"
           <c:if test="${requestScope.task.work != null}">value="${fn:escapeXml(requestScope.task.work)}"</c:if>
           <c:if test="${requestScope.task.work == null}">value="${fn:escapeXml(param.work)}"</c:if>><br><br>
    <label>Дата начала</label><br>
    <c:choose>
        <c:when test="${requestScope.task.startDate != null}">
            <c:set var="startYear" value="${requestScope.task.startDate.toString().substring(0,4)}"/>
            <c:set var="startMonth" value="${requestScope.task.startDate.toString().substring(5,7)}"/>
            <c:set var="startDay" value="${requestScope.task.startDate.toString().substring(8,10)}"/>
        </c:when>
        <c:when test="${requestScope.task.startDate == null}">
            <c:set var="startYear" value="${param.startYear}"/>
            <c:set var="startMonth" value="${param.startMonth}"/>
            <c:set var="startDay" value="${param.startDay}"/>
        </c:when>
    </c:choose>
    <label for="startYear">Год:</label>
    <input required value="${startYear}" type="number" min="1" max="9999" id="startYear" name="startYear"
           oninvalid="this.setCustomValidity('Заполните поле')" oninput="setCustomValidity('')">
    <c:if test="${requestScope.invalidStartYear}">Неверный год</c:if>
    <label for="startMonth">Месяц:</label>
    <select class="month-select" id="startMonth" name="startMonth">
        <option <c:if test="${startMonth == '01'}">selected</c:if> value="01">Январь</option>
        <option <c:if test="${startMonth == '02'}">selected</c:if> value="02">Февраль</option>
        <option <c:if test="${startMonth == '03'}">selected</c:if> value="03">Март</option>
        <option <c:if test="${startMonth == '04'}">selected</c:if> value="04">Апрель</option>
        <option <c:if test="${startMonth == '05'}">selected</c:if> value="05">Май</option>
        <option <c:if test="${startMonth == '06'}">selected</c:if> value="06">Июнь</option>
        <option <c:if test="${startMonth == '07'}">selected</c:if> value="07">Июль</option>
        <option <c:if test="${startMonth == '08'}">selected</c:if> value="08">Август</option>
        <option <c:if test="${startMonth == '09'}">selected</c:if> value="09">Сентябрь</option>
        <option <c:if test="${startMonth == '10'}">selected</c:if> value="10">Октябрь</option>
        <option <c:if test="${startMonth == '11'}">selected</c:if> value="11">Ноябрь</option>
        <option <c:if test="${startMonth == '12'}">selected</c:if> value="12">Декабрь</option>
    </select>
    <label for="startDay">День:</label>
    <input required value="${startDay}" type="number" id="startDay"
           name="startDay" min="1" max="31" oninvalid="this.setCustomValidity('Заполните поле')" oninput="setCustomValidity('')">
    <c:if test="${requestScope.invalidStartDay}">Неверный день</c:if>
    <c:if test="${requestScope.wrongStartDate}">Неверная дата начала</c:if><br><br>
    <label>Дата окончания</label><br>
    <c:choose>
        <c:when test="${requestScope.task.endDate != null}">
            <c:set var="endYear" value="${requestScope.task.endDate.toString().substring(0,4)}"/>
            <c:set var="endMonth" value="${requestScope.task.endDate.toString().substring(5,7)}"/>
            <c:set var="endDay" value="${requestScope.task.endDate.toString().substring(8,10)}"/>
        </c:when>
        <c:when test="${requestScope.task.endDate == null}">
            <c:set var="endYear" value="${param.endYear}"/>
            <c:set var="endMonth" value="${param.endMonth}"/>
            <c:set var="endDay" value="${param.endDay}"/>
        </c:when>
    </c:choose>
    <label for="endYear">Год:</label>
    <input required value="${endYear}" type="number" min="1" max="9999" id="endYear" name="endYear"
           oninvalid="this.setCustomValidity('Заполните поле')" oninput="setCustomValidity('')">
    <c:if test="${requestScope.invalidEndYear}">Неверный год</c:if>
    <label for="endMonth">Месяц:</label>
    <select class="month-select" id="endMonth" name="endMonth">
        <option <c:if test="${endMonth == '01'}">selected</c:if> value="01">Январь</option>
        <option <c:if test="${endMonth == '02'}">selected</c:if> value="02">Февраль</option>
        <option <c:if test="${endMonth == '03'}">selected</c:if> value="03">Март</option>
        <option <c:if test="${endMonth == '04'}">selected</c:if> value="04">Апрель</option>
        <option <c:if test="${endMonth == '05'}">selected</c:if> value="05">Май</option>
        <option <c:if test="${endMonth == '06'}">selected</c:if> value="06">Июнь</option>
        <option <c:if test="${endMonth == '07'}">selected</c:if> value="07">Июль</option>
        <option <c:if test="${endMonth == '08'}">selected</c:if> value="08">Август</option>
        <option <c:if test="${endMonth == '09'}">selected</c:if> value="09">Сентябрь</option>
        <option <c:if test="${endMonth == '10'}">selected</c:if> value="10">Октябрь</option>
        <option <c:if test="${endMonth == '11'}">selected</c:if> value="11">Ноябрь</option>
        <option <c:if test="${endMonth == '12'}">selected</c:if> value="12">Декабрь</option>
    </select>
    <label for="endDay">День:</label>
    <input required value="${endDay}" type="number" id="endDay"
           name="endDay" min="1" max="31" oninvalid="this.setCustomValidity('Заполните поле')" oninput="setCustomValidity('')">
    <c:if test="${requestScope.invalidEndDay}">Неверный день</c:if>
    <c:if test="${requestScope.wrongEndDate}">Неверная дата окончания</c:if><br><br>
    <c:if test="${requestScope.dateCollision}">Дата начала больше даты окончания<br><br></c:if>
    <label for="stat">Статус:</label><br>
    <c:choose>
        <c:when test="${requestScope.task.status != null}">
            <c:set var="status" value="${requestScope.task.status}"/>
        </c:when>
        <c:when test="${requestScope.task.status == null}">
            <c:set var="status" value="${param.stat}"/>
        </c:when>
    </c:choose>
    <select name="stat" id="stat">
        <option value="NOT_STARTED" <c:if test="${status == 'NOT_STARTED'}">selected</c:if> >Не начата</option>
        <option value="IN_PROGRESS" <c:if test="${status == 'IN_PROGRESS'}">selected</c:if> >В процессе</option>
        <option value="DONE" <c:if test="${status == 'DONE'}">selected</c:if> >Завершена</option>
        <option value="PAUSED" <c:if test="${status == 'PAUSED'}">selected</c:if> >Отложена</option>
    </select><br><br>
    <label for="exec">Исполнитель:</label><br>
    <c:choose>
        <c:when test="${requestScope.task.executorId != null}">
            <c:set var="executor" value="${requestScope.task.executorId}"/>
        </c:when>
        <c:when test="${requestScope.task.executorId == null}">
            <c:set var="executor" value="${param.exec}"/>
        </c:when>
    </c:choose>
    <select name="exec" id="exec">
        <c:forEach var="employee" items="${requestScope.employees}">
            <option value="${employee.id}"
                    <c:if test="${employee.id == executor}">selected</c:if>>
                    ${fn:escapeXml(employee.lastName)}
                    ${fn:escapeXml(employee.firstName)}
                    ${fn:escapeXml(employee.patronymic)}
            </option>
        </c:forEach>
    </select><br><br>
    <input type="submit" value="Сохранить">
</form>
</body>
</html>
