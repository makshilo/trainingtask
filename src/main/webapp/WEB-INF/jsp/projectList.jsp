<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Список проектов</title>
</head>
<body>
<button onclick="window.location.href='/'">Назад</button><br>
<h3>Проекты</h3>
<table>
    <tr>
        <th>Имя проекта</th>
        <th>Описание</th>
    </tr>
    <c:forEach var="project" items="${requestScope.projects}">
        <tr>
            <td><c:out value="${project.name}"/></td>
            <td><c:out value="${project.description}"/></td>
            <td><button onclick="window.location.href='/controller?command=projectEditPage&id=${project.id}'">Изменить</button></td>
            <td><button onclick="window.location.href='/controller?command=deleteProject&id=${project.id}'">Удалить</button></td>
        </tr>
    </c:forEach>
</table>
<button onclick="window.location.href='${pageContext.request.contextPath}/controller?command=projectCreatePage'">Добавить</button>
</body>
</html>