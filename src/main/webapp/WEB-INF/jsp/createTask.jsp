<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Добавить задачу</title>
</head>
<body>
<button onclick="window.location.href='/controller?command=tasksPage'">Назад</button><br><br>
<form action="<c:url value="/controller?command=createTask"/>" method="post">
    <label for="tname">Имя:</label>
    <input maxlength="50" required type="text" id="tname" name="tname" oninvalid="this.setCustomValidity('Заполните поле')" oninput="setCustomValidity('')"><br><br>
    <label for="proj">Проект:</label>
    <select name="proj" id="proj" required oninvalid="this.setCustomValidity('Выберите проект')" oninput="setCustomValidity('')">
        <option selected value="">Не выбрано</option>
        <c:forEach var="project" items="${requestScope.projects}">
            <option value="${project.id}">
                    ${project.name}
            </option>
        </c:forEach>
    </select><br><br>
    <label for="work">Работа:</label>
    <input required type="number" id="work" name="work" value="0" oninvalid="this.setCustomValidity('Заполните поле')" oninput="setCustomValidity('')"><br><br>
    <fieldset>
        <legend>Дата начала</legend>
        <label for="startYear">Год:</label>
        <input required value="1" type="number" id="startYear" name="startYear" oninvalid="this.setCustomValidity('Заполните поле')" oninput="setCustomValidity('')">
        <label for="startMonth">Месяц:</label>
        <select id="startMonth" name="startMonth">
            <option selected value="01">Январь</option>
            <option value="02">Февраль</option>
            <option value="03">Март</option>
            <option value="04">Апрель</option>
            <option value="05">Май</option>
            <option value="06">Июнь</option>
            <option value="07">Июль</option>
            <option value="08">Август</option>
            <option value="09">Сентябрь</option>
            <option value="10">Октябрь</option>
            <option value="11">Ноябрь</option>
            <option value="12">Декабрь</option>
        </select>
        <label for="startDay">День:</label>
        <input required value="1" type="number" id="startDay" name="startDay" min="1" max="31" oninvalid="this.setCustomValidity('Заполните поле')" oninput="setCustomValidity('')">
    </fieldset>
    <fieldset>
        <legend>Дата окончания</legend>
        <label for="endYear">Год:</label>
        <input required value="1" type="number" id="endYear" name="endYear" oninvalid="this.setCustomValidity('Заполните поле')" oninput="setCustomValidity('')">
        <label for="endMonth">Месяц:</label>
        <select id="endMonth" name="endMonth">
            <option selected value="01">Январь</option>
            <option value="02">Февраль</option>
            <option value="03">Март</option>
            <option value="04">Апрель</option>
            <option value="05">Май</option>
            <option value="06">Июнь</option>
            <option value="07">Июль</option>
            <option value="08">Август</option>
            <option value="09">Сентябрь</option>
            <option value="10">Октябрь</option>
            <option value="11">Ноябрь</option>
            <option value="12">Декабрь</option>
        </select>
        <label for="endDay">День:</label>
        <input required value="1" type="number" id="endDay" name="endDay" min="1" max="31" oninvalid="this.setCustomValidity('Заполните поле')" oninput="setCustomValidity('')">
    </fieldset><br>
    <label for="exec">Исполнитель:</label>
    <select name="exec" id="exec" required oninvalid="this.setCustomValidity('Выберите исполнителя')" oninput="setCustomValidity('')">
        <option selected value="">Не выбрано</option>
        <c:forEach var="employee" items="${requestScope.employees}">
            <option value="${employee.id}">
                ${employee.lastName}
                ${employee.firstName}
                ${employee.patronymic}
            </option>
        </c:forEach>
    </select><br><br>
    <label for="stat">Статус:</label>
    <select name="stat" id="stat">
        <option value="NOT_STARTED">Не начата</option>
        <option value="IN_PROGRESS">Выполняется</option>
        <option value="DONE">Готова</option>
        <option value="PAUSED">Приостановлена</option>
    </select><br><br>
    <input type="submit" value="Сохранить">
</form>
</body>
</html>
