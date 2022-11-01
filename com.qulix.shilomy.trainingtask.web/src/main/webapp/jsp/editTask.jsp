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
    <a class="home-button" href=".."></a>
</header>
<a class="button" href="${pageContext.request.contextPath}/tasks">Отмена</a><br><br>

<c:set scope="request" var="id" value="${requestScope.task.id}"/>

<c:set var="action" value="${pageContext.request.contextPath.concat(requestScope.pageMode == 'edit' ?
'/editTask?id='.concat(param.id) : '/createTask')}"/>

<form action="${action}" method="post">
    <label for="taskName">Наименование:</label><br>

    <c:set var="taskName" value="${requestScope.task.name != null ? requestScope.task.name : param.taskName}"/>

    <input maxlength="50" type="text" id="taskName" name="taskName" value="${fn:escapeXml(taskName)}"><br><br>
    <label for="project">Наименование проекта:</label><br>

    <c:choose>
        <c:when test="${requestScope.projectLock == true}">
            <select disabled name="project" id="project">
                <option selected
                        value="${requestScope.currentProject.id}">${requestScope.currentProject.name}</option>
            </select><br><br>
            <input type="hidden" name="project" value="${requestScope.currentProject.id}">
        </c:when>
        <c:otherwise>
            <select name="project" id="project">
                <c:forEach var="project" items="${requestScope.projects}">
                    <option value="${project.id}"
                            <c:if test="${project.id == requestScope.task.projectId}">selected</c:if>
                            <c:if test="${project.id == param.project}">selected</c:if>>
                            ${project.name}
                    </option>
                </c:forEach>
            </select><br><br>
        </c:otherwise>
    </c:choose>

    <label for="work">Работа:</label><br>

    <c:set var="work" value="${requestScope.task.name != null ? requestScope.task.work : param.work}"/>

    <input style="width: 100px;" type="text" id="work" name="work" value="${fn:escapeXml(work)}"><br><br>

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

    <input style="width: 100px;" value="${fn:escapeXml(startYear)}" type="text" id="startYear" name="startYear">

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

    <input style="width: 100px;" value="${fn:escapeXml(startDay)}" type="text" id="startDay" name="startDay"><br><br>

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

    <input style="width: 100px;" value="${fn:escapeXml(endYear)}" type="text" id="endYear" name="endYear">

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

    <input style="width: 100px;" value="${fn:escapeXml(endDay)}" type="text" id="endDay" name="endDay"><br><br>

    <label for="status">Статус:</label><br>

    <c:set var="status" value="${requestScope.task.status != null ? requestScope.task.status : param.status}"/>

    <select name="status" id="status">
        <c:forEach var="status" items="${requestScope.taskStatus}">
            <option value="${status}"
                    <c:if test="${status == param.status}">selected</c:if>>
                    ${status.getStatus()}
            </option>
        </c:forEach>
    </select><br><br>

    <label for="executor">Исполнитель:</label><br>

    <c:set var="executor" value="${requestScope.task.executorId != null ? requestScope.task.executorId : param.executor}"/>

    <select name="executor" id="executor">
        <c:forEach var="employee" items="${requestScope.employees}">
            <option value="${employee.id}"
                    <c:if test="${employee.id == executor}">selected</c:if>>
                    ${employee.lastName}
                    ${employee.firstName}
                    ${employee.patronymic}
            </option>
        </c:forEach>
    </select><br><br>

    <a style="margin-left: 10px"><c:out value="${requestScope.validationError}"/></a><br><br>

    <input type="submit" value="Сохранить">
</form>
</body>
</html>
