<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<h4><a href="addMeal">Add Meal</a></h4>
<h4>
    <table cellpadding="10" border="1" cellspacing="0">
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach var="meal" items="${mealsTo}">
            <c:if test="${meal.excess.equals(true)}">
                <tr style="color:Red;">
            </c:if>
            <c:if test="${meal.excess.equals(false)}">
                <tr style="color:Green;">
            </c:if>
            <td><c:out value="${meal.dateTime}"/></td>
            <td><c:out value="${meal.description}"/></td>
            <td><c:out value="${meal.calories}"/></td>
            <td>
                <h4><a href="editMeal">Update</a></h4></td>
            <td><h4><a href="delete">Delete</a></h4></td>
            </span>
            </tr>
        </c:forEach>
    </table>
</h4>
</body>
</html>