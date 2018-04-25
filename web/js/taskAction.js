function showAddingForm() {
    hideFileModelForm();
    document.getElementById('additionTask').style.display = "block";
}

function hideAddingForm() {
    document.getElementById('additionTask').style.display = "none";
}

function showFileModelForm() {
    hideAddingForm();
    document.getElementById('additionFile').style.display = "block";
}

function hideFileModelForm() {
    document.getElementById('additionFile').style.display = "none";
}

function cancelSelection() {
    document.getElementById("mainPanel").style.display = "block";
    var tableName = document.getElementById("table").getAttribute("value");
    var headerTr = document.getElementById("headerTr");
    var tr = document.getElementsByClassName("tr");

    var th = headerTr.childNodes;
    headerTr.removeChild(th[th.length - 1]);

    for (var i = 0; i < tr.length; i ++){
        var elements = tr[i].childNodes;
        var lastTd = elements[elements.length - 1];
        tr[i].removeChild(lastTd);
    }
    mainControlPanel();
    switch (tableName){
        case "today":{
            initTodayTable();
            break;
        }
        case "tomorrow":{
            initTomorrowTable();
            break;
        }
        case "someday":{
            initSomedayTable();
            break;
        }
        case "recycleBin":{
            initRecycleBin();
            break;
        }
        case "completed":{
            initCompletedTable();
            break;
        }
    }
}

function removeTask() {
    var tbody = document.getElementById("taskTableBody");
    var tr = document.getElementsByClassName("tr");

    for (var i = 0; i < tr.length; i ++){
        var elements = tr[i].childNodes;
        var lastTd = elements[elements.length - 1];
        var preLastTd = elements[elements.length - 2];
        var checkbox = lastTd.childNodes[0];
        if(checkbox.checked){
            //addRecycleTask(preLastTd.value);
            addRecycleTask(preLastTd.id);
            tbody.removeChild(tr[i]);
            i --;
        }
    }
    cancelSelection();
}

function selectTasks() {
    var table = document.getElementById("table");
    var checkSelecting = document.getElementById("selecting");

    if (table != null && checkSelecting == null){
        document.getElementById("mainPanel").style.display = "none";
        switch (table.getAttribute("value")){
            case "recycleBin":{
                recycleBinPanel();
                break;
            }
            case "completed":{
                completedPanel();
                break;
            }
            default:{
                additionalControlPanel();
            }
        }

        var tr = document.getElementsByClassName("tr");
        var headerTr = document.getElementById("headerTr");

        var selecting = document.createElement("th");

        selecting.setAttribute("id", "selecting");

        headerTr.appendChild(selecting);

        selecting.innerHTML = "Select element";

        for (var i = 0; i < tr.length; i ++){
            var td = document.createElement("td");
            var checkbox = document.createElement("input");

            td.setAttribute("class", "td");

            checkbox.setAttribute("type", "checkbox");
            checkbox.setAttribute("class", "checkbox");

            tr[i].appendChild(td);
            td.appendChild(checkbox);
        }
    }
}

function checkDate(inputDate) {
    var nowDate = new Date();
    var month = nowDate.getMonth() + 1;
    var day = nowDate.getDate();
    var year = nowDate.getFullYear().toString();

    var stringDate = year + "-" + month + "-" + day;

    var currentDate = new Date(stringDate);
    var date = new Date(inputDate);

    if (currentDate <= date){
        return true;
    }else {
        return false;
    }
}