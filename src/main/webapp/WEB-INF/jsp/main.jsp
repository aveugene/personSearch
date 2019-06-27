<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ncc
  Date: 27.06.2019
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Поиск человека</title>
    </head>
    <body>
        <form method="get">
            <input type="hidden" name="action" value="search">
            <input type="text" name="first_name" value="">
            <input type="text" name="last_name" value="">
            <input type="text" name="patronymic" value="">
            <input type="text" name="city_name" value="">
            <input type="text" name="model" value="">
            <input type="text" name="license" value="">
            <button type="submit">Найти</button>
        </form>

        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
            <tr>
                <th>Имя</th>
                <th>Фамилия</th>
                <th>Отчество</th>
                <th>Город</th>
                <th>Автомобиль</th>
            </tr>
            </thead>
            <c:forEach items="${persons}" var="person">
                <jsp:useBean id="person" type="ru.rit.personsearch.to.PersonTo"/>
                <tr>
                    <td>${person.firstName}</td>
                    <td>${person.lastName}</td>
                    <td>${person.patronymic}</td>
                    <td>${person.city.name}</td>
                    <td>
                        <table>
                            <c:if test="${person.cars.size() == 0}">
                                <tr>
                                    <td>
                                        Нет автомобиля
                                    </td>
                                </tr>
                            </c:if>
                            <c:forEach items="${person.cars}" var="car">
                                <jsp:useBean id="car" type="ru.rit.personsearch.model.Car"/>
                                <tr>
                                    <td>${car.model}</td>
                                    <td>${car.license}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
