<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit task</title>
</head>
<body>
<button onclick="window.location.href='/controller?command=tasksPage'">Back</button><br>
<form action="<c:url value="/controller?command=editTask&id=${requestScope.task.id}"/>" method="post">
    <label for="tname">Name:</label>
    <input maxlength="50" type="text" id="tname" name="tname" value=${requestScope.task.name}><br><br>
    <label for="proj">Project:</label>
    <select name="proj" id="proj">
        <option selected value="${requestScope.currentProject.id}"> ${requestScope.currentProject.name} </option>
        <option value=0>None</option>
        <c:forEach var="project" items="${requestScope.projects}">
            <option value="${project.id}">
                    ${project.name}
            </option>
        </c:forEach>
    </select><br><br>
    <label for="work">Work:</label>
    <textarea maxlength="1000" id="work" name="work" rows="4" cols="50">${requestScope.task.work}</textarea><br><br>
    <label for="start">Start:</label>
    <input type="date" id="start" name="start" value="${requestScope.task.startDate}"><br><br>
    <label for="end">End:</label>
    <input type="date" id="end" name="end" value="${requestScope.task.endDate}"><br><br>
    <label for="exec">Executor:</label>
    <select name="exec" id="exec">
        <option selected value=0>None</option>
        <option value="${requestScope.currentExecutor.id}">
            ${requestScope.currentExecutor.firstName}
            ${requestScope.currentExecutor.lastName}</option>
        <c:forEach var="employee" items="${requestScope.employees}">
            <option value="${employee.id}">
                    ${employee.lastName}
                    ${employee.firstName}
                    ${employee.patronymic}
            </option>
        </c:forEach>
    </select><br><br>
    <label for="stat">Status:</label>
    <select name="stat" id="stat">
        <option selected value="${requestScope.task.status}">
            ${requestScope.task.status}</option>
        <option value="NOT_STARTED">Not started</option>
        <option value="IN_PROGRESS">In progress</option>
        <option value="DONE">Done</option>
        <option value="PAUSED">Paused</option>
    </select><br><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
