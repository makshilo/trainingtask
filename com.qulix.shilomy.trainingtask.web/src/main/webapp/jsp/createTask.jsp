<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <link rel="stylesheet" href="../css/general.css">
    <link rel="stylesheet" href="../css/buttons.css">
    <link rel="stylesheet" href="../css/inputs.css">
    <link rel="stylesheet" href="../css/labels.css">
    <title>Добавить задачу</title>
</head>
<body>
<header>
    <a class="home-button" href="<c:url value=".."/>"></a>
</header>
<a class="button" href="<c:url value="/controller?command=tasksPage"/>">Отмена</a><br><br>
<form action="<c:url value="/controller?command=createTask"/>" method="post">
    <label for="taskName">Наименование:</label><br>
    <input maxlength="50" value="${fn:escapeXml(param.taskName)}" type="text" id="taskName" name="taskName">
    <c:if test="${requestScope.taskNameNull}">Заполните поле</c:if><br><br>
    <label for="project">Наименование проекта:</label><br>
    <c:choose>
        <c:when test="${requestScope.projectLock == true}">
            <select disabled name="project" id="project">
                <option selected
                        value="${requestScope.currentProject.id}"> ${fn:escapeXml(requestScope.currentProject.name)}</option>
            </select><br><br>
            <input type="hidden" name="project" value="${fn:escapeXml(requestScope.currentProject.id)}">
        </c:when>
        <c:otherwise>
            <select name="project" id="project">
                <c:forEach var="project" items="${requestScope.projects}">
                    <option value="${project.id}"
                            <c:if test="${project.id == param.project}">selected</c:if>>
                            ${fn:escapeXml(project.name)}
                    </option>
                </c:forEach>
            </select>
            <c:if test="${requestScope.projectNull}">Заполните поле</c:if><br><br>
        </c:otherwise>
    </c:choose>
    <label for="work">Работа:</label><br>
    <input style="width: 100px;" type="text" id="work" name="work" value="${fn:escapeXml(param.work)}">
    <c:if test="${requestScope.workNull}">Заполните поле</c:if>
    <c:if test="${requestScope.workNotInteger}">Введённое значение должно быть целым числом</c:if>
    <c:if test="${requestScope.workNegative}">Введённое значение не должно быть отрицательным</c:if><br><br>
    <label>Дата начала</label><br>
    <label for="startYear">Год:</label>
    <input style="width: 100px;" value="${fn:escapeXml(param.startYear)}" type="text"  id="startYear" name="startYear">
    <c:if test="${requestScope.startYearNull}">Заполните поле</c:if>
    <c:if test="${requestScope.invalidStartYear}">Введённое значение не соответствует формату: гггг</c:if>
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
    <c:if test="${requestScope.startMonthNull}">Заполните поле</c:if>
    <label for="startDay">День:</label>
    <input style="width: 100px;" value="${fn:escapeXml(param.startDay)}" type="text" id="startDay" name="startDay">
    <c:if test="${requestScope.startDayNull}">Заполните поле</c:if>
    <c:if test="${requestScope.invalidStartDay}">Введённое значение не соответствует формату: дд</c:if>
    <c:if test="${requestScope.wrongStartDate}">Введённая дата не существует</c:if><br><br>
    <label>Дата окончания</label><br>
    <label for="endYear">Год:</label>
    <input style="width: 100px;" value="${fn:escapeXml(param.endYear)}" type="text" id="endYear" name="endYear">
    <c:if test="${requestScope.endYearNull}">Заполните поле</c:if>
    <c:if test="${requestScope.invalidEndYear}">Введённое значение не соответствует формату: гггг</c:if>
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
    <c:if test="${requestScope.endMonthNull}">Заполните поле</c:if>
    <label for="endDay">День:</label>
    <input style="width: 100px;" value="${fn:escapeXml(param.endDay)}" type="text" id="endDay" name="endDay">
    <c:if test="${requestScope.endDayNull}">Заполните поле</c:if>
    <c:if test="${requestScope.invalidEndDay}">Введённое значение не соответствует формату: дд</c:if>
    <c:if test="${requestScope.wrongEndDate}">Введённая дата не существует</c:if><br><br>
    <c:if test="${requestScope.dateCollision}">Дата начала больше даты окончания<br><br></c:if>
    <label for="status">Статус:</label><br>
    <select name="status" id="status">
        <option <c:if test="${param.status == 'NOT_STARTED'}">selected</c:if> value="NOT_STARTED">Не начата</option>
        <option <c:if test="${param.status == 'IN_PROGRESS'}">selected</c:if> value="IN_PROGRESS">В процессе</option>
        <option <c:if test="${param.status == 'DONE'}">selected</c:if> value="DONE">Завершена</option>
        <option <c:if test="${param.status == 'PAUSED'}">selected</c:if> value="PAUSED">Отложена</option>
    </select>
    <c:if test="${requestScope.statusNull}">Заполните поле</c:if><br><br>
    <label for="executor">Исполнитель:</label><br>
    <select name="executor" id="executor">
        <c:forEach var="employee" items="${requestScope.employees}">
            <option value="${employee.id}"
                    <c:if test="${employee.id == param.executor}">selected</c:if>>
                    ${employee.lastName}
                    ${employee.firstName}
                    ${employee.patronymic}
            </option>
        </c:forEach>
    </select>
    <c:if test="${requestScope.executorNull}">Заполните поле</c:if><br><br>
    <input type="submit" value="Сохранить">
</form>
</body>
</html>
