<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Константин Сусло
  Date: 26.02.2018
  Time: 21:52
  To change this template use files | Settings | files Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Start</title>
</head>
<body>
    <c:import url="header.jsp"/>
    <div class="authentication">
        <form action="/start" method="post">
            <input type="submit" value="Login">
        </form>
    </div>
    <c:import url="/html/footer.html"/>
</body>
</html>
