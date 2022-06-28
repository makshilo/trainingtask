<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<button onclick="window.location.href='/'">Back</button><br>
<h3>Tasks</h3>
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
            <td><button onclick="window.location.href='/controller?command=taskEditPage&id=${task.id}'">Edit</button></td>
            <td><button onclick="window.location.href='/controller?command=deleteTask&id=${task.id}'">Delete</button></td>
        </tr>
    </c:forEach>
</table>
<button onclick="window.location.href='/controller?command=taskCreatePage'">Add</button>
</body>
</html>
