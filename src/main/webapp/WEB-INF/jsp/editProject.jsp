<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Edit project</title>
</head>
<body>
<form action="<c:url value="/controller?command=editProject&id=${requestScope.project.id}"/>" method="post">
    <label for="pname">Project name:</label>
    <input type="text" id="pname" name="pname" value=${requestScope.project.name}><br><br>
    <label for="descr">Description:</label>
    <textarea id="descr" name="descr" rows="4" cols="50">${requestScope.project.description}</textarea><br><br>
    <input type="submit" value="Submit">
</form>
<table>
    <tr>
        <th>Name</th>
        <th>Project</th>
        <th>Work</th>
        <th>Start</th>
        <th>End</th>
        <th>Executor</th>
    </tr>
    <c:forEach var="task" items="${requestScope.tasks}">
        <tr>
            <td>${task.name}</td>
            <td>${task.projectId}</td>
            <td>${task.work}</td>
            <td>${task.startDate}</td>
            <td>${task.endDate}</td>
            <td>${task.executorId}</td>
            <td><button onclick="window.location.href='/controller?command=taskEditFromProjectPage&id=${task.id}'">Edit</button></td>
            <td><button onclick="window.location.href='/controller?command=deleteTask&id=${task.id}'">Delete</button></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
