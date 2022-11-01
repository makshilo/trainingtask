<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Главная страница</title>
        <link rel="stylesheet" href="../css/general.css">
        <link rel="stylesheet" href="../css/buttons.css">
    </head>
    <header>
        <a class="home-button" href=".."></a>
    </header>
    <body>
        <h1 style="color:white">Добро пожаловать на главную страницу</h1>
        <a class="main-menu-button" style="background-image: url(../css/img/projects.png)" href="${pageContext.request.contextPath}/projects">Проекты</a>
        <a class="main-menu-button" style="background-image: url(../css/img/tasks.png)" href="${pageContext.request.contextPath}/tasks">Задачи</a>
        <a class="main-menu-button" style="background-image: url(../css/img/employees.png)" href="${pageContext.request.contextPath}/employees">Сотрудники</a>
    </body>
</html>
