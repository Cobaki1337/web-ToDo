function initRecycleBin() {
    var container = document.getElementById("taskTable");
    var recycleTasks = getRecycleTask();

    for (var i = container.childNodes.length - 1; i > 0; i --){
        container.removeChild(container.childNodes[i]);
    }

    if (recycleTasks.length === 0){
        var emptyTasks = document.createElement("h4");
        container.appendChild(emptyTasks);
        emptyTasks.innerHTML = "You don't have any tasks in the recycle bin.";
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
        table.setAttribute("value", "recycleBin");

        container.appendChild(div);
        container.appendChild(table);

        table.appendChild(headerTr);
        table.appendChild(tbody);

        headerTr.appendChild(taskName);
        headerTr.appendChild(taskContent);
        headerTr.appendChild(taskDate);

        div.appendChild(h3);
        h3.appendChild(span);

        span.innerHTML = "RECYCLE BIN";
        taskName.innerHTML = "Task name";
        taskContent.innerHTML = "Task content";
        taskDate.innerHTML = "Task date";


        for (var key in recycleTasks) {
            var tr = document.createElement("tr");
            var td1 = document.createElement("td");
            var td2 = document.createElement("td");
            var td3 = document.createElement("td");
            var idTask = document.createElement("input");

            tr.setAttribute("class", "tr");

            idTask.setAttribute("type", "hidden");
            idTask.setAttribute("id", recycleTasks[key].id);
            idTask.setAttribute("value", JSON.stringify(recycleTasks[key]));

            td1.setAttribute("class", "td");
            td2.setAttribute("class", "td");
            td3.setAttribute("class", "td");

            tbody.appendChild(tr);

            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
            tr.appendChild(idTask);

            td1.innerHTML = recycleTasks[key].taskName;
            td2.innerHTML = recycleTasks[key].taskContent;
            td3.innerHTML = recycleTasks[key].taskDate;
        }
    }
}

function recoveryTaskFromBin() {
    var tbody = document.getElementById("taskTableBody");
    var tr = document.getElementsByClassName("tr");

    for (var i = 0; i < tr.length; i ++){
        var elements = tr[i].childNodes;
        var lastTd = elements[elements.length - 1];
        var preLastTd = elements[elements.length - 2];
        var checkbox = lastTd.childNodes[0];
        if(checkbox.checked){
            recoveryRecycleTask(preLastTd.id);
            tbody.removeChild(tr[i]);
            i --;
        }
    }
    cancelSelection();
}

function recoveryAllTasksFromBin() {
    document.getElementById("mainPanel").style.display = "block";
    recoveryAllRecycleTasks();
    initRecycleBin();
    mainControlPanel();
}

function removeTaskFromBin() {
    var tbody = document.getElementById("taskTableBody");
    var tr = document.getElementsByClassName("tr");

    for (var i = 0; i < tr.length; i ++){
        var elements = tr[i].childNodes;
        var lastTd = elements[elements.length - 1];
        var preLastTd = elements[elements.length - 2];
        var checkbox = lastTd.childNodes[0];
        if(checkbox.checked){
            removeRecycleTask(preLastTd.id);
            tbody.removeChild(tr[i]);
            i --;
        }
    }
    cancelSelection();
}


function removeAllFromBin() {
    document.getElementById("mainPanel").style.display = "block";
    removeAllRecycleTasks();
    initRecycleBin();
    mainControlPanel();
}

//server path

function recoveryRecycleTask(taskId) {
    var xhr = new XMLHttpRequest();
    var params = "taskId=" + encodeURIComponent(taskId);
    xhr.open('POST', '/recoveryRecycleBinTask', false);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send(params);
}

function recoveryAllRecycleTasks() {
    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/recoveryAllRecycleBinTasks', false);
    xhr.send();
}

function addRecycleTask(taskId) {
    var xhr = new XMLHttpRequest();
    var params = "taskId=" + encodeURIComponent(taskId);
    xhr.open('POST', '/addRecycleTask', false);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send(params);
}

function getRecycleTask() {
    try {
        var xhr = new XMLHttpRequest();
        xhr.open('POST', '/getRecycleTasks', false);
        xhr.send();
        var recycleTasks = xhr.responseText;
        recycleTasks = JSON.parse(recycleTasks);
        return recycleTasks;
    }catch (e){
        alert("Name: " + e.name + '\n' + "Message: " + e.message + '\n' + "Stack: " + e.stack);
    }
}

function removeAllRecycleTasks() {
    removeAllFiles();
    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/removeAllRecycleTasks', false);
    xhr.send();
}

function removeRecycleTask(taskId) {
    //removeFile(taskId);

    var xhr = new XMLHttpRequest();
    var params = "taskId=" + encodeURIComponent(taskId);
    xhr.open('POST', '/removeRecycleBinTask', false);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send(params);

    var fileXhr = new XMLHttpRequest();
    var parameters = "fileId=" + encodeURIComponent(taskId);
    fileXhr.open('POST', '/removeFile', false);
    fileXhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    fileXhr.send(parameters);
}