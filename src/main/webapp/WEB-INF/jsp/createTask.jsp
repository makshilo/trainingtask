<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Create Task</title>
</head>
<body>
<button onclick="window.location.href='/controller?command=tasksPage'">Back</button><br>
<form action="<c:url value="/controller?command=createTask"/>" method="post">
    <label for="tname">Name:</label>
    <input maxlength="50" required type="text" id="tname" name="tname"><br><br>
    <label for="proj">Project:</label>
    <select name="proj" id="proj" required>
        <option selected value="">None</option>
        <c:forEach var="project" items="${requestScope.projects}">
            <option value="${project.id}">
                    ${project.name}
            </option>
        </c:forEach>
    </select><br><br>
    <label for="work">Work:</label>
    <input required type="number" id="work" name="work" value="0"><br><br>
    <label for="start">Start:</label>
    <input required type="date" id="start" name="start"><br><br>
    <label for="end">End:</label>
    <input required type="date" id="end" name="end"><br><br>
    <label for="exec">Executor:</label>
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
    <label for="stat">Status:</label>
    <select name="stat" id="stat">
        <option value="NOT_STARTED">Not started</option>
        <option value="IN_PROGRESS">In progress</option>
        <option value="DONE">Done</option>
        <option value="PAUSED">Paused</option>
    </select><br><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
