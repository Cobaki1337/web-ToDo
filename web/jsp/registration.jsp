<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Константин Сусло
  Date: 27.02.2018
  Time: 17:45
  To change this template use files | Settings | files Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="/css/authenticationStyle.css">
</head>
<body>
<c:import url="header.jsp"/>
<div class="authBody">
    <div class="main">
        <h2>Registration</h2>
        <form name="registration" action="/registration" method="post">
            <label>User Name :</label>
            <input type="text" name="login" id="login"/>
            <label>Password :</label>
            <input type="password" name="password" id="password"/>
            <label>Repeat password :</label>
            <input type="password" name="repeatPassword" id="repeatPassword"/>
            <input type="submit" value="Registration" id="submit"/>
        </form>
        <span>
            </b>
            <c:if test="${error ne null}">
                <b class="error">Error : </b>
                ${error}
                <br/>
            </c:if>
        </span>
    </div>
</div>
<c:import url="/html/footer.html"/>
</body>
</html>
