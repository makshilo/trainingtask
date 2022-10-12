<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" href="../css/general.css">
    <link rel="stylesheet" href="../css/buttons.css">
    <link rel="stylesheet" href="../css/inputs.css">
    <link rel="stylesheet" href="../css/labels.css">
    <title>Изменить задачу</title>
</head>
<body>
<header>
    <a class="home-button" href="<c:url value=".."/>"></a>
</header>
<a class="button" href="<c:url value="/controller?command=tasksPage"/>">Отмена</a><br><br>

<c:set scope="request" var="id" value="${requestScope.task.id}"/>

<form action="<c:url value="/controller?command=editTask&id=${param.id}"/>" method="post">
    <label for="taskName">Наименование:</label><br>
    <c:choose>
        <c:when test="${requestScope.task.name != null}">
            <c:set var="taskName" value="${requestScope.task.name}"/>
        </c:when>
        <c:otherwise>
            <c:set var="taskName" value="${fn:escapeXml(param.taskName)}"/>
        </c:otherwise>
    </c:choose>
    <input maxlength="50" type="text" id="taskName" name="taskName" value="${taskName}">
    <c:if test="${requestScope.taskNameNull}">Заполните поле</c:if><br><br>
    <label for="project">Наименование проекта:</label><br>
    <c:choose>
        <c:when test="${requestScope.projectLock == true}">
            <select disabled name="project" id="project">
                <option selected
                        value="${requestScope.currentProject.id}">${fn:escapeXml(requestScope.currentProject.name)}</option>
            </select><br><br>
            <input type="hidden" name="project" value="${fn:escapeXml(requestScope.currentProject.id)}">
        </c:when>
        <c:otherwise>
            <select name="project" id="project">
                <c:forEach var="project" items="${requestScope.projects}">
                    <option value="${project.id}"
                            <c:if test="${project.id == requestScope.task.projectId}">selected</c:if>
                            <c:if test="${project.id == param.project}">selected</c:if>>
                            ${fn:escapeXml(project.name)}
                    </option>
                </c:forEach>
            </select>
            <c:if test="${requestScope.projectNull}">Заполните поле</c:if><br><br>
        </c:otherwise>
    </c:choose>
    <label for="work">Работа:</label><br>
    <c:choose>
        <c:when test="${requestScope.task.name != null}">
            <c:set var="work" value="${requestScope.task.work}"/>
        </c:when>
        <c:otherwise>
            <c:set var="work" value="${fn:escapeXml(param.work)}"/>
        </c:otherwise>
    </c:choose>
    <input style="width: 100px;" type="text" id="work" name="work" value="${work}">
    <c:if test="${requestScope.workNull}">Заполните поле</c:if>
    <c:if test="${requestScope.workNotInteger}">Введённое значение должно быть целым числом</c:if>
    <c:if test="${requestScope.workNegative}">Введённое значение не должно быть отрицательным</c:if><br><br>
    <label>Дата начала</label><br>
    <c:choose>
        <c:when test="${requestScope.task.startDate != null}">
            <c:set var="startYear" value="${requestScope.task.startDate.toString().substring(0,4)}"/>
            <c:set var="startMonth" value="${requestScope.task.startDate.toString().substring(5,7)}"/>
            <c:set var="startDay" value="${requestScope.task.startDate.toString().substring(8,10)}"/>
        </c:when>
        <c:otherwise>
            <c:set var="startYear" value="${param.startYear}"/>
            <c:set var="startMonth" value="${param.startMonth}"/>
            <c:set var="startDay" value="${param.startDay}"/>
        </c:otherwise>
    </c:choose>
    <label for="startYear">Год:</label>
    <input style="width: 100px;" value="${startYear}" type="text" id="startYear" name="startYear">
    <c:if test="${requestScope.startYearNull}">Заполните поле</c:if>
    <c:if test="${requestScope.invalidStartYear}">Введённое значение не соответствует формату: гггг</c:if>
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
    <c:if test="${requestScope.startMonthNull}">Заполните поле</c:if>
    <label for="startDay">День:</label>
    <input style="width: 100px;" value="${startDay}" type="text" id="startDay" name="startDay">
    <c:if test="${requestScope.startDayNull}">Заполните поле</c:if>
    <c:if test="${requestScope.invalidStartDay}">Введённое значение не соответствует формату: дд</c:if>
    <c:if test="${requestScope.wrongStartDate}">Введённая дата не существует</c:if><br><br>
    <label>Дата окончания</label><br>
    <c:choose>
        <c:when test="${requestScope.task.endDate != null}">
            <c:set var="endYear" value="${requestScope.task.endDate.toString().substring(0,4)}"/>
            <c:set var="endMonth" value="${requestScope.task.endDate.toString().substring(5,7)}"/>
            <c:set var="endDay" value="${requestScope.task.endDate.toString().substring(8,10)}"/>
        </c:when>
        <c:otherwise>
            <c:set var="endYear" value="${param.endYear}"/>
            <c:set var="endMonth" value="${param.endMonth}"/>
            <c:set var="endDay" value="${param.endDay}"/>
        </c:otherwise>
    </c:choose>
    <label for="endYear">Год:</label>
    <input style="width: 100px;" value="${endYear}" type="text" id="endYear" name="endYear">
    <c:if test="${requestScope.endYearNull}">Заполните поле</c:if>
    <c:if test="${requestScope.invalidEndYear}">Введённое значение не соответствует формату: гггг</c:if>
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
    <c:if test="${requestScope.endMonthNull}">Заполните поле</c:if>
    <label for="endDay">День:</label>
    <input style="width: 100px;" value="${endDay}" type="text" id="endDay" name="endDay">
    <c:if test="${requestScope.endDayNull}">Заполните поле</c:if>
    <c:if test="${requestScope.invalidEndDay}">Введённое значение не соответствует формату: дд</c:if>
    <c:if test="${requestScope.wrongEndDate}">Введённая дата не существует</c:if><br><br>
    <c:if test="${requestScope.dateCollision}">Дата начала больше даты окончания<br><br></c:if>
    <label for="status">Статус:</label><br>
    <c:choose>
        <c:when test="${requestScope.task.status != null}">
            <c:set var="status" value="${requestScope.task.status}"/>
        </c:when>
        <c:otherwise>
            <c:set var="status" value="${param.status}"/>
        </c:otherwise>
    </c:choose>
    <select name="status" id="status">
        <option value="NOT_STARTED" <c:if test="${status == 'NOT_STARTED'}">selected</c:if> >Не начата</option>
        <option value="IN_PROGRESS" <c:if test="${status == 'IN_PROGRESS'}">selected</c:if> >В процессе</option>
        <option value="DONE" <c:if test="${status == 'DONE'}">selected</c:if> >Завершена</option>
        <option value="PAUSED" <c:if test="${status == 'PAUSED'}">selected</c:if> >Отложена</option>
    </select>
    <c:if test="${requestScope.statusNull}">Заполните поле</c:if><br><br>
    <label for="executor">Исполнитель:</label><br>
    <c:choose>
        <c:when test="${requestScope.task.executorId != null}">
            <c:set var="executor" value="${requestScope.task.executorId}"/>
        </c:when>
        <c:otherwise>
            <c:set var="executor" value="${param.executor}"/>
        </c:otherwise>
    </c:choose>
    <select name="executor" id="executor">
        <c:forEach var="employee" items="${requestScope.employees}">
            <option value="${employee.id}"
                    <c:if test="${employee.id == executor}">selected</c:if>>
                    ${fn:escapeXml(employee.lastName)}
                    ${fn:escapeXml(employee.firstName)}
                    ${fn:escapeXml(employee.patronymic)}
            </option>
        </c:forEach>
    </select>
    <c:if test="${requestScope.executorNull}">Заполните поле</c:if><br><br>
    <input type="submit" value="Сохранить">
</form>
</body>
</html>
