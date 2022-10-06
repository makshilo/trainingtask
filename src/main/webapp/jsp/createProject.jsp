<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <link rel="stylesheet" href="../css/general.css">
    <link rel="stylesheet" href="../css/buttons.css">
    <link rel="stylesheet" href="../css/inputs.css">
    <link rel="stylesheet" href="../css/labels.css">
    <title>Добавить проект проект</title>
</head>
<body>
<header>
    <a class="home-button" href="<c:url value=".."/>"></a>
</header>
<a class="button" href="<c:url value="/controller?command=projectsPage"/>">Отмена</a><br>
<form action="<c:url value="/controller?command=createProject"/>" method="post">
    <label for="projectName">Наименование:</label><br>
    <input value="${fn:escapeXml(param.projectName)}" maxlength="100" type="text"
           id="projectName" name="projectName">
    <c:if test="${requestScope.projectNameNull}">Заполните поле</c:if>
    <c:if test="${requestScope.projectIsFound}">Такой проект уже существует</c:if><br><br>
    <label for="description">Описание:</label><br>
    <textarea maxlength="1000" id="description" name="description" rows="4" cols="50">${fn:escapeXml(param.description)}</textarea>
    <c:if test="${requestScope.projectDescriptionNull}">Заполните поле</c:if><br><br>
    <input class="submit-input" type="submit" value="Сохранить">
</form>
</body>
</html>
