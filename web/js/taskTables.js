function initSomedayTable() {
    var date = "someday";
    var tasks = getTasks(date);

    var container = document.getElementById("taskTable");

    for (var i = container.childNodes.length - 1; i > 0; i --){
        container.removeChild(container.childNodes[i]);
    }

    if (tasks.length === 0){
        var emptyTasks = document.createElement("h4");
        container.appendChild(emptyTasks);
        emptyTasks.innerHTML = "You don't have any tasks.";
    }else {
        var div = document.createElement("div");
        var h3 = document.createElement("h3");
        var span = document.createElement("span");

        div.setAttribute("class", "d2");

        var table = document.createElement("table");
        var tbody = document.createElement("tbody");
        var headerTr = document.createElement("tr");
        var taskName = document.createElement("th");
        var taskContent = document.createElement("th");
        var taskDate = document.createElement("th");
        var taskFile = document.createElement("th");

        headerTr.setAttribute("id", "headerTr");

        table.setAttribute("id", "table");
        table.setAttribute("class", "table");
        table.setAttribute("value", "someday");

        tbody.setAttribute("class", "taskTableBody");
        tbody.setAttribute("id", "taskTableBody");

        container.appendChild(div);
        container.appendChild(table);

        table.appendChild(headerTr);
        table.appendChild(tbody);

        headerTr.appendChild(taskName);
        headerTr.appendChild(taskContent);
        headerTr.appendChild(taskDate);
        headerTr.appendChild(taskFile);

        div.appendChild(h3);
        h3.appendChild(span);

        span.innerHTML = "SOMEDAY TASKS";
        taskName.innerHTML = "Task name";
        taskContent.innerHTML = "Task content";
        taskDate.innerHTML = "Task date";
        taskFile.innerHTML = "Task file";

        for (var key in tasks) {
            if (document.getElementById("recycle" + tasks[key].id) == null) {
                var tr = document.createElement("tr");
                var td1 = document.createElement("td");
                var td2 = document.createElement("td");
                var td3 = document.createElement("td");
                var td4 = document.createElement("td");
                var idTask = document.createElement("input");
                var file = document.createElement("a");
                var imgTrue = document.createElement("img");
                var imgFalse = document.createElement("img");

                imgTrue.setAttribute("height", "50");
                imgFalse.setAttribute("height", "50");

                tr.setAttribute("class", "tr");

                if (!checkDate(tasks[key].taskDate)){
                    tr.setAttribute("id", "overdue");
                }

                td1.setAttribute("class", "td");
                td2.setAttribute("class", "td");
                td3.setAttribute("class", "td");
                td4.setAttribute("class", "td");

                idTask.setAttribute("type", "hidden");
                idTask.setAttribute("id", tasks[key].id);
                idTask.setAttribute("value", JSON.stringify(tasks[key]));

                file.setAttribute("href", 'JavaScript:initFileModel(' + tasks[key].taskFile + ', ' + tasks[key].id +')');

                imgFalse.setAttribute("src", "/images/ankreuzen-1740989__340.png");
                imgTrue.setAttribute("src", "/images/Галочка-зеленая.jpg");

                tbody.appendChild(tr);

                if (tasks[key].taskFile === "1"){
                    file.appendChild(imgTrue);
                }else {
                    file.appendChild(imgFalse);
                }

                tr.appendChild(td1);
                tr.appendChild(td2);
                tr.appendChild(td3);
                tr.appendChild(td4);
                tr.appendChild(idTask);

                td4.appendChild(file);

                td1.innerHTML = tasks[key].taskName;
                td2.innerHTML = tasks[key].taskContent;
                td3.innerHTML = tasks[key].taskDate;
            }
        }
    }
}

function initTomorrowTable() {
    var date = "tomorrow";
    var tasks = getTasks(date);

    var container = document.getElementById("taskTable");

    for (var i = container.childNodes.length - 1; i > 0; i --){
        container.removeChild(container.childNodes[i]);
    }

    if (tasks.length === 0){
        var emptyTasks = document.createElement("h4");
        container.appendChild(emptyTasks);
        emptyTasks.innerHTML = "You don't have any tasks tomorrow.";
    }else {
        var div = document.createElement("div");
        var h3 = document.createElement("h3");
        var span = document.createElement("span");

        div.setAttribute("class", "d2");

        var table = document.createElement("table");
        var tbody = document.createElement("tbody");
        var headerTr = document.createElement("tr");
        var taskName = document.createElement("th");
        var taskContent = document.createElement("th");
        var taskFile = document.createElement("th");

        headerTr.setAttribute("id", "headerTr");
        tbody.setAttribute("class", "taskTableBody");

        table.setAttribute("id", "table");
        table.setAttribute("class", "table");
        table.setAttribute("value", "tomorrow");

        tbody.setAttribute("class", "taskTableBody");
        tbody.setAttribute("id", "taskTableBody");

        container.appendChild(div);
        container.appendChild(table);

        table.appendChild(headerTr);
        table.appendChild(tbody);

        headerTr.appendChild(taskName);
        headerTr.appendChild(taskContent);
        headerTr.appendChild(taskFile);

        div.appendChild(h3);
        h3.appendChild(span);

        span.innerHTML = "TOMORROW TASKS";
        taskName.innerHTML = "Task name";
        taskContent.innerHTML = "Task content";
        taskFile.innerHTML = "Task file";


        for (var key in tasks) {
            var tr = document.createElement("tr");
            var td1 = document.createElement("td");
            var td2 = document.createElement("td");
            var td3 = document.createElement("td");
            var idTask = document.createElement("input");
            var file = document.createElement("a");
            var imgTrue = document.createElement("img");
            var imgFalse = document.createElement("img");

            imgTrue.setAttribute("height", "50");
            imgFalse.setAttribute("height", "50");

            tr.setAttribute("class", "tr");

            td1.setAttribute("class", "td");
            td2.setAttribute("class", "td");
            td3.setAttribute("class", "td");

            idTask.setAttribute("type", "hidden");
            idTask.setAttribute("id", tasks[key].id);
            idTask.setAttribute("value", JSON.stringify(tasks[key]));

            file.setAttribute("href", 'JavaScript:initFileModel(' + tasks[key].taskFile + ', ' + tasks[key].id +')');

            imgFalse.setAttribute("src", "/images/ankreuzen-1740989__340.png");
            imgTrue.setAttribute("src", "/images/Галочка-зеленая.jpg");

            tbody.appendChild(tr);

            if (tasks[key].taskFile === "1"){
                file.appendChild(imgTrue);
            }else {
                file.appendChild(imgFalse);
            }

            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
            tr.appendChild(idTask);

            td3.appendChild(file);

            td1.innerHTML = tasks[key].taskName;
            td2.innerHTML = tasks[key].taskContent;
        }
    }
}

function initTodayTable() {
    var date = "today";
    var tasks = getTasks(date);

    var container = document.getElementById("taskTable");

    for (var i = container.childNodes.length - 1; i > 0; i --){
        container.removeChild(container.childNodes[i]);
    }

    if (tasks.length === 0){
        var emptyTasks = document.createElement("h4");
        container.appendChild(emptyTasks);
        emptyTasks.innerHTML = "You don't have any tasks today.";
    }else {
        var div = document.createElement("div");
        var h3 = document.createElement("h3");
        var span = document.createElement("span");

        div.setAttribute("class", "d2");

        var table = document.createElement("table");
        var tbody = document.createElement("tbody");
        var headerTr = document.createElement("tr");
        var taskName = document.createElement("th");
        var taskContent = document.createElement("th");
        var taskFile = document.createElement("th");

        headerTr.setAttribute("id", "headerTr");

        tbody.setAttribute("class", "taskTableBody");
        tbody.setAttribute("id", "taskTableBody");

        table.setAttribute("id", "table");
        table.setAttribute("class", "table");
        table.setAttribute("value", "today");

        container.appendChild(div);
        container.appendChild(table);

        table.appendChild(headerTr);
        table.appendChild(tbody);

        headerTr.appendChild(taskName);
        headerTr.appendChild(taskContent);
        headerTr.appendChild(taskFile);

        div.appendChild(h3);
        h3.appendChild(span);

        span.innerHTML = "TODAY TASKS";
        taskName.innerHTML = "Task name";
        taskContent.innerHTML = "Task content";
        taskFile.innerHTML = "Task file";

        for (var key in tasks) {
            var tr = document.createElement("tr");
            var td1 = document.createElement("td");
            var td2 = document.createElement("td");
            var td3 = document.createElement("td");
            var idTask = document.createElement("input");
            var file = document.createElement("a");
            var imgTrue = document.createElement("img");
            var imgFalse = document.createElement("img");

            imgTrue.setAttribute("height", "50");
            imgFalse.setAttribute("height", "50");

            tr.setAttribute("class", "tr");

            td1.setAttribute("class", "td");
            td2.setAttribute("class", "td");
            td3.setAttribute("class", "td");

            idTask.setAttribute("type", "hidden");
            idTask.setAttribute("id", tasks[key].id);
            idTask.setAttribute("value", JSON.stringify(tasks[key]));

            file.setAttribute("href", 'JavaScript:initFileModel(' + tasks[key].taskFile + ', ' + tasks[key].id +')');

            imgFalse.setAttribute("src", "/images/ankreuzen-1740989__340.png");
            imgTrue.setAttribute("src", "/images/Галочка-зеленая.jpg");

            tbody.appendChild(tr);

            if (tasks[key].taskFile === "1"){
                file.appendChild(imgTrue);
            }else {
                file.appendChild(imgFalse);
            }

            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
            tr.appendChild(idTask);

            td3.appendChild(file);

            td1.innerHTML = tasks[key].taskName;
            td2.innerHTML = tasks[key].taskContent;
        }
    }
}

//server path
function getTasks(date) {
    try {
        var xhr = new XMLHttpRequest();
        var params = "date=" + date;
        xhr.open('POST', '/main', false);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.send(params);
        var tasks = xhr.responseText;
        tasks = JSON.parse(tasks);
        return tasks;
    }catch (e){
        alert("Name: " + e.name + '\n' + "Message: " + e.message + '\n' + "Stack: " + e.stack);
    }
}

function addTask() {
    var taskName = document.getElementById("taskName").value;
    var taskDate = document.getElementById("taskDate").value;
    var taskContent = document.getElementById("taskContent").value;

    if (taskName === "" || taskDate === "" || taskContent === ""){
        document.getElementById("errorArea").style.display = "block";
        document.getElementById("errorMessage").innerHTML = "Empty fields!";
        return;
    }

    if (!checkDate(taskDate)){
        document.getElementById("errorArea").style.display = "block";
        document.getElementById("errorMessage").innerHTML = "Wrong date!";
        return;
    }

    var task = {
        taskName : taskName,
        taskDate : taskDate,
        taskContent : taskContent
    };

    var xhr = new XMLHttpRequest();
    var params = "task=" + encodeURIComponent(JSON.stringify(task));
    xhr.open('POST', '/addTask', false);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send(params);
    document.getElementById("taskName").value = "";
    document.getElementById("taskContent").value = "";
    initTodayTable();
    document.getElementById("errorArea").style.display = "none";
}


