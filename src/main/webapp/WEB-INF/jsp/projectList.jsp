<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Project list page</title>
</head>
<body>
<button onclick="window.location.href='/controller'">Back</button><br><br>
<h3>Projects</h3>
<table>
    <tr>
        <th>Project name</th>
        <th>Description</th>
    </tr>
    <c:forEach var="project" items="${requestScope.projects}">
        <tr>
            <td>${project.name}</td>
            <td>${project.description}</td>
            <td><button onclick="window.location.href='/controller?command=projectEditPage&id=${project.id}'">Edit</button></td>
            <td><button onclick="window.location.href='/controller?command=deleteProject&id=${project.id}'">Delete</button></td>
        </tr>
    </c:forEach>
</table>
<button onclick="window.location.href='${pageContext.request.contextPath}/controller?command=projectCreatePage'">Add</button>
</body>
</html>