<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="../css/general.css">
    <link rel="stylesheet" href="../css/buttons.css">
    <link rel="stylesheet" href="../css/tables.css">
    <title>Список проектов</title>
</head>
<body>
<header>
    <a class="home-button" href=".."></a>
</header>
<a class="button" href="..">Назад</a><br>
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
            <td><a class="table-button" href="${pageContext.request.contextPath}/editProjectPage?id=${project.id}">Изменить</a></td>
            <td>
                <form action="${pageContext.request.contextPath}/deleteProject?id=${project.id}" method="post">
                    <button class="table-button" name="delete" type="submit">Удалить</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<a class="add-button" href="${pageContext.request.contextPath}/createProjectPage">Добавить</a>
</body>
</html>