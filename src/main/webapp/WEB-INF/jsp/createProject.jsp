<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <link rel="stylesheet" href="../../css/general.css">
    <link rel="stylesheet" href="../../css/buttons.css">
    <link rel="stylesheet" href="../../css/inputs.css">
    <link rel="stylesheet" href="../../css/labels.css">
    <title>Добавить проект проект</title>
</head>
<body>
<header>
        <button class="home-button" onclick="window.location.href='/'"></button>
</header>
<button class="back-button" onclick="window.location.href='/controller?command=projectsPage'">Отмена</button><br>
<form action="<c:url value="/controller?command=createProject"/>" method="post">
    <label for="pname">Наименование:</label><br>
    <input value="${fn:escapeXml(requestScope.filledProject.getName())}" maxlength="100" required type="text"
           id="pname" name="pname" oninvalid="this.setCustomValidity('Заполните поле')" oninput="setCustomValidity('')">
    <c:if test="${requestScope.projectIsFound}">Такой проект уже существует</c:if><br><br>
    <label for="descr">Описание:</label><br>
    <textarea maxlength="1000" id="descr" name="descr" rows="4" cols="50">${fn:escapeXml(requestScope.filledProject.getDescription())}</textarea><br><br>
    <input class="submit-input" type="submit" value="Сохранить">
</form>
</body>
</html>
