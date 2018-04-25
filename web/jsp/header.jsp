<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Константин Сусло
  Date: 04.03.2018
  Time: 14:36
  To change this template use files | Settings | files Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/css/headerStyle.css">
</head>
<body>
    <div class="header">
        <div class="title">
            TODO
        </div>
        <div class="userName">
            <c:if test="${user ne null}">
                ${user.login},
                <a href="/logout">Logout</a>
            </c:if>
        </div>
    </div>
</body>
</html>
