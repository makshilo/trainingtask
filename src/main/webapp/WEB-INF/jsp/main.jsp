<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
    <head>
        <title>Main page</title>
    </head>
    <body>
        <h1>Welcome to the main page</h1>
        <a href="<c:url value="/controller?command=projectsPage"/>">Go to project list</a><br><br>
        <a href="<c:url value="/controller?command=tasksPage"/>">Go to task list</a><br><br>
        <a href="<c:url value="/controller?command=employeesPage"/>">Go to employee list</a><br><br>
    </body>
</html>
