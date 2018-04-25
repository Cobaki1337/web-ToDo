<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Константин Сусло
  Date: 26.02.2018
  Time: 22:17
  To change this template use files | Settings | files Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
    <link rel="stylesheet" href="/css/elementLayout.css">
    <link rel="stylesheet" href="/css/tablesStyle.css">
    <script type="text/javascript" src="/js/recycleBin.js"></script>
    <script type="text/javascript" src="/js/completed.js"></script>
    <script type="text/javascript" src="/js/taskTables.js"></script>
    <script type="text/javascript" src="/js/taskAction.js"></script>
    <script type="text/javascript" src="/js/panels.js"></script>
    <script type="text/javascript" src="/js/files.js"></script>
</head>
<body>
<c:import url="header.jsp"/>
<c:import url="/html/mainPanel.html"/>


    <div class="showTasks" id="showTasks">
        <div class="taskTable" id="taskTable">

        </div>
        <div class="controlPanel" id="controlPanel">

        </div>
    </div>

    <div class="additionTask" id="additionTask" style="display: none;">
        <c:import url="/html/addTaskPanel.html"/>
    </div>

    <div class="additionFile" id="additionFile" style="display: none">
        <c:import url="/html/addFilePanel.html"/>
    </div>
<c:import url="/html/footer.html"/>

<script>
    mainControlPanel();
</script>
<script>
    initTodayTable();
</script>
</body>
</html>
