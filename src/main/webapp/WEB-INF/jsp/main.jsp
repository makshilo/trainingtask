<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Главная страница</title>
        <link rel="stylesheet" href="../../css/general.css">
        <link rel="stylesheet" href="../../css/buttons.css">
    </head>
    <header>
        <button class="home-button" onclick="window.location.href='/'"></button>
    </header>
    <body>
        <h1 style="color:white">Добро пожаловать на главную страницу</h1>
        <button class="projects-button" onclick="window.location.href='<c:url value="/controller?command=projectsPage"/>'">Проекты</button>
        <button class="tasks-button" onclick="window.location.href='<c:url value="/controller?command=tasksPage"/>'">Задачи</button>
        <button class="employees-button" onclick="window.location.href='<c:url value="/controller?command=employeesPage"/>'">Сотрудники</button>
    </body>
</html>
