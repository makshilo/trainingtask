<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Create project</title>
</head>
<body>
<button onclick="window.location.href='/controller?command=projectsPage'">Back</button><br>
<form action="<c:url value="/controller?command=createProject"/>" method="post">
    <label for="pname">Project name:</label>
    <input maxlength="100" required type="text" id="pname" name="pname"><br><br>
    <label for="descr">Description:</label>
    <textarea maxlength="1000" id="descr" name="descr" rows="4" cols="50"></textarea><br><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
