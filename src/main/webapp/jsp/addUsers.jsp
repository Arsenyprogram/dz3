<%--
  Created by IntelliJ IDEA.
  User: arsenijabramov
  Date: 28.09.2025
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Добавление пользователей</title>
</head>
<body>
<h2>Добавьте пользователей</h2>
<form action="/users" method="post">
  <c:forEach var="i" begin="1" end="${usersCount}">
    <fieldset>
      <legend>Пользователь ${i}</legend>
      <input type="text" name="firstName${i}" placeholder="Имя" required><br>
      <input type="text" name="lastName${i}" placeholder="Фамилия" required><br>
      <input type="number" name="age${i}" placeholder="Возраст" required><br>

    </fieldset>
    <br>
  </c:forEach>
  <input type="submit" value="Сохранить">
</form>
</body>
</html>
