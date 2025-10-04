<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Drivers</title>
</head>
<body>
<h2>Driver Filter</h2>

<div>
    <form action="searchDrivers" method="get">
        <input type="hidden" name="param" value="salary"/>
        <button type="submit">With Salary</button>
    </form>

    <form action="searchDrivers" method="get">
        <input type="hidden" name="param" value="car"/>
        <button type="submit">With Car</button>
    </form>

    <form action="searchDrivers" method="get">
        <input type="hidden" name="param" value="country"/>
        <button type="submit">With Country</button>
    </form>
</div>

<hr/>

<c:if test="${not empty users}">
    <table border="1" cellpadding="5" cellspacing="0">
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Age</th>
            <th>Salary</th>
            <th>Car</th>
            <th>Country</th>
        </tr>
        <c:forEach var="u" items="${users}">
            <tr>
                <td>${u.id}</td>
                <td>${u.firstName}</td>
                <td>${u.lastName}</td>
                <td>${u.age}</td>
                <td><c:choose>
                    <c:when test="${u.salary != null}">${u.salary}</c:when>
                    <c:otherwise>-</c:otherwise>
                </c:choose></td>
                <td><c:choose>
                    <c:when test="${u.car != null}">${u.car}</c:when>
                    <c:otherwise>-</c:otherwise>
                </c:choose></td>
                <td><c:choose>
                    <c:when test="${u.country != null}">${u.country}</c:when>
                    <c:otherwise>-</c:otherwise>
                </c:choose></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<c:if test="${empty users}">
    <p>No drivers found.</p>
</c:if>

</body>
</html>
