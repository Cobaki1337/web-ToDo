<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Константин Сусло
  Date: 26.02.2018
  Time: 8:36
  To change this template use files | Settings | files Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="/css/authenticationStyle.css">
</head>
<body>
    <c:import url="header.jsp"/>
    <div class="authBody">
            <div class="main">
                <h2>Login</h2>
                <form name="login" action="/login" method="post">
                    <label>User Name :</label>
                    <input type="text" name="login" id="login"/>
                    <label>Password :</label>
                    <input type="password" name="password" id="password"/>
                    <input type="submit" value="Login" id="submit"/>
                </form>
                <span>
                    <b class="valid">If you don't have an account:
                    <a href="jsp/registration.jsp">Registration</a>
                    <br/>
                    </b>
                    <c:if test="${registration ne null}">
                        <hr>
                        <b class="valid">${registration}</b>
                        <br/>
                    </c:if>
                    <c:if test="${error ne null}">
                        <hr>
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

