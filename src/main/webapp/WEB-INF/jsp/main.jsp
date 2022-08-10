<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Главная страница</title>
    </head>
    <body>
        <h1>Добро пожаловать на главную страницу</h1>
        <a href="<c:url value="/controller?command=projectsPage"/>">Перейти к списку проектов</a><br><br>
        <a href="<c:url value="/controller?command=tasksPage"/>">Перейти к списку задач</a><br><br>
        <a href="<c:url value="/controller?command=employeesPage"/>">Перейти к списку сотрудников</a><br><br>
    </body>
</html>
