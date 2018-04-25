function initCompletedTable() {
    var completedTasks = getCompletedTasks();

    var container = document.getElementById("taskTable");

    for (var i = container.childNodes.length - 1; i > 0; i --){
        container.removeChild(container.childNodes[i]);
    }

    if (completedTasks.length === 0){
        var emptyTasks = document.createElement("h4");
        container.appendChild(emptyTasks);
        emptyTasks.innerHTML = "You don't have any completed tasks.";
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

        headerTr.setAttribute("id", "headerTr");

        tbody.setAttribute("class", "taskTableBody");
        tbody.setAttribute("id", "taskTableBody");

        table.setAttribute("id", "table");
        table.setAttribute("class", "table");
        table.setAttribute("value", "completed");

        container.appendChild(div);
        container.appendChild(table);

        table.appendChild(headerTr);
        table.appendChild(tbody);

        headerTr.appendChild(taskName);
        headerTr.appendChild(taskContent);
        headerTr.appendChild(taskDate);

        div.appendChild(h3);
        h3.appendChild(span);

        span.innerHTML = "COMPLETED";
        taskName.innerHTML = "Task name";
        taskContent.innerHTML = "Task content";
        taskDate.innerHTML = "Task date";


        for (var key in completedTasks) {
            var tr = document.createElement("tr");
            var td1 = document.createElement("td");
            var td2 = document.createElement("td");
            var td3 = document.createElement("td");
            var idTask = document.createElement("input");

            tr.setAttribute("class", "tr");

            td1.setAttribute("class", "td");
            td2.setAttribute("class", "td");
            td3.setAttribute("class", "td");

            idTask.setAttribute("type", "hidden");
            idTask.setAttribute("id", completedTasks[key].id);
            idTask.setAttribute("value", JSON.stringify(completedTasks[key]));

            tbody.appendChild(tr);

            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
            tr.appendChild(idTask);

            td1.innerHTML = completedTasks[key].taskName;
            td2.innerHTML = completedTasks[key].taskContent;
            td3.innerHTML = completedTasks[key].taskDate;
        }
    }
}

//server path

function removeTaskFromCompleted() {
    var tbody = document.getElementById("taskTableBody");
    var tr = document.getElementsByClassName("tr");

    for (var i = 0; i < tr.length; i ++){
        var elements = tr[i].childNodes;
        var lastTd = elements[elements.length - 1];
        var preLastTd = elements[elements.length - 2];
        var checkbox = lastTd.childNodes[0];
        if(checkbox.checked){
            removeCompletedTask(preLastTd.id);
            tbody.removeChild(tr[i]);
            i --;
        }
    }
    cancelSelection();
}

function removeAllFromCompleted() {
    document.getElementById("mainPanel").style.display = "block";
    removeAllCompletedTasks();
    initCompletedTable();
    mainControlPanel();
}

function completeTask() {
    var tbody = document.getElementById("taskTableBody");
    var tr = document.getElementsByClassName("tr");

    for (var i = 0; i < tr.length; i ++){
        var elements = tr[i].childNodes;
        var lastTd = elements[elements.length - 1];
        var preLastTd = elements[elements.length - 2];
        var checkbox = lastTd.childNodes[0];
        if(checkbox.checked){
            addCompletedTask(preLastTd.id);
            tbody.removeChild(tr[i]);
            i --;
        }
    }
    cancelSelection();
}

//server path

function addCompletedTask(taskId) {
    //removeFile(taskId);

    var xhr = new XMLHttpRequest();
    var params = "taskId=" + encodeURIComponent(taskId);
    xhr.open('POST', '/addCompletedTask', false);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send(params);

    var fileXhr = new XMLHttpRequest();
    var parameters = "fileId=" + encodeURIComponent(taskId);
    fileXhr.open('POST', '/removeFile', false);
    fileXhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    fileXhr.send(parameters);
}

function getCompletedTasks() {
    try {
        var xhr = new XMLHttpRequest();
        xhr.open('POST', '/getCompletedTasks', false);
        xhr.send();
        var completedTasks = xhr.responseText;
        completedTasks = JSON.parse(completedTasks);
        return completedTasks;
    }catch (e){
        alert("Name: " + e.name + '\n' + "Message: " + e.message + '\n' + "Stack: " + e.stack);
    }
}

function removeAllCompletedTasks() {
    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/removeAllCompletedTasks', false);
    xhr.send();
}

function removeCompletedTask(taskId) {
    var xhr = new XMLHttpRequest();
    var params = "taskId=" + encodeURIComponent(taskId);
    xhr.open('POST', '/removeCompletedTask', false);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send(params);
}