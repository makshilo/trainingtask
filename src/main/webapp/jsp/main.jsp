<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Главная страница</title>
        <link rel="stylesheet" href="../css/general.css">
        <link rel="stylesheet" href="../css/buttons.css">
    </head>
    <header>
        <a class="home-button" href="<c:url value=".."/>"></a>
    </header>
    <body>
        <h1 style="color:white">Добро пожаловать на главную страницу</h1>
        <a class="main-menu-button" style="background-image: url(../css/img/projects.png)" href="<c:url value="/controller?command=projectsPage"/>">Проекты</a>
        <a class="main-menu-button" style="background-image: url(../css/img/tasks.png)" href="<c:url value="/controller?command=tasksPage"/>">Задачи</a>
        <a class="main-menu-button" style="background-image: url(../css/img/employees.png)" href="<c:url value="/controller?command=employeesPage"/>">Сотрудники</a>
    </body>
</html>
