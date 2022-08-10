<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Добавить проект проект</title>
</head>
<body>
<button onclick="window.location.href='/controller?command=projectsPage'">Назад</button><br>
<form action="<c:url value="/controller?command=createProject"/>" method="post">
    <label for="pname">Имя проекта:</label>
    <input maxlength="100" required type="text" id="pname" name="pname"><br><br>
    <label for="descr">Описание:</label>
    <textarea maxlength="1000" id="descr" name="descr" rows="4" cols="50"></textarea><br><br>
    <input type="submit" value="Сохранить">
</form>
</body>
</html>
