<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="../../css/general.css">
    <link rel="stylesheet" href="../../css/buttons.css">
    <link rel="stylesheet" href="../../css/tables.css">
    <title>Список проектов</title>
</head>
<body>
<header>
    <button class="home-button" onclick="window.location.href='/'"></button>
</header>
<button class="back-button" onclick="window.location.href='/'">Назад</button><br>
<h3>Проекты</h3>
<table>
    <tr>
        <th>Наименование</th>
        <th>Описание</th>
    </tr>
    <c:forEach var="project" items="${requestScope.projects}">
        <tr>
            <td><c:out value="${project.name}"/></td>
            <td><c:out value="${project.description}"/></td>
            <td><button class="table-button" onclick="window.location.href='/controller?command=projectEditPage&id=${project.id}'">Изменить</button></td>
            <td><button class="table-button" onclick="window.location.href='/controller?command=deleteProject&id=${project.id}'">Удалить</button></td>
        </tr>
    </c:forEach>
</table>
<button class="add-button" onclick="window.location.href='${pageContext.request.contextPath}/controller?command=projectCreatePage'">Добавить</button>
</body>
</html>