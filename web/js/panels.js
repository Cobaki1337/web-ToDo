function mainControlPanel() {
    var container = document.getElementById("controlPanel");

    for (var i = container.childNodes.length - 1; i > 0; i --){
        container.removeChild(container.childNodes[i]);
    }

    var addTask = document.createElement("a");
    var selectTask = document.createElement("a");

    addTask.setAttribute("class", "button orange");
    addTask.setAttribute("href", "JavaScript:showAddingForm()");
    addTask.innerHTML = "Add task";

    selectTask.setAttribute("class", "button orange");
    selectTask.setAttribute("href", "JavaScript:selectTasks()");
    selectTask.innerHTML = "Select tasks";

    container.appendChild(addTask);
    container.appendChild(selectTask);
}

function additionalControlPanel() {
    var container = document.getElementById("controlPanel");

    for (var i = container.childNodes.length - 1; i > 0; i --){
        container.removeChild(container.childNodes[i]);
    }

    var completeTask = document.createElement("a");
    var removeTask = document.createElement("a");
    var cancellation = document.createElement("a");

    completeTask.setAttribute("class", "button orange");
    completeTask.setAttribute("href", "JavaScript:completeTask()");
    completeTask.innerHTML = "Complete";

    removeTask.setAttribute("class", "button orange");
    removeTask.setAttribute("href", "JavaScript:removeTask()");
    removeTask.innerHTML = "Remove";

    cancellation.setAttribute("class", "button dsgnmoo");
    cancellation.setAttribute("href", "JavaScript:cancelSelection()");
    cancellation.innerHTML = "Cancel";

    container.appendChild(completeTask);
    container.appendChild(removeTask);
    container.appendChild(cancellation);
}

function recycleBinPanel() {
    var container = document.getElementById("controlPanel");

    for (var i = container.childNodes.length - 1; i > 0; i --){
        container.removeChild(container.childNodes[i]);
    }

    var recoveryAll = document.createElement("a");
    var recovery = document.createElement("a");
    var removeAll = document.createElement("a");
    var remove = document.createElement("a");
    var cancellation = document.createElement("a");

    recoveryAll.setAttribute("class", "button orange");
    recoveryAll.setAttribute("href", "JavaScript:recoveryAllTasksFromBin()");
    recoveryAll.innerHTML = "Recovery ALL";

    recovery.setAttribute("class", "button orange");
    recovery.setAttribute("href", "JavaScript:recoveryTaskFromBin()");
    recovery.innerHTML = "Recovery";

    removeAll.setAttribute("class", "button orange");
    removeAll.setAttribute("href", "JavaScript:removeAllFromBin()");
    removeAll.innerHTML = "Remove ALL";

    remove.setAttribute("class", "button orange");
    remove.setAttribute("href", "JavaScript:removeTaskFromBin()");
    remove.innerHTML = "Remove";

    cancellation.setAttribute("class", "button dsgnmoo");
    cancellation.setAttribute("href", "JavaScript:cancelSelection()");
    cancellation.innerHTML = "Cancel";

    container.appendChild(recoveryAll);
    container.appendChild(recovery);
    container.appendChild(removeAll);
    container.appendChild(remove);
    container.appendChild(cancellation);
}

function completedPanel() {
    var container = document.getElementById("controlPanel");

    for (var i = container.childNodes.length - 1; i > 0; i --){
        container.removeChild(container.childNodes[i]);
    }

    var removeAll = document.createElement("a");
    var remove = document.createElement("a");
    var cancellation = document.createElement("a");

    removeAll.setAttribute("class", "button orange");
    removeAll.setAttribute("href", "JavaScript:removeAllFromCompleted()");
    removeAll.innerHTML = "Remove ALL";

    remove.setAttribute("class", "button orange");
    remove.setAttribute("href", "JavaScript:removeTaskFromCompleted()");
    remove.innerHTML = "Remove";

    cancellation.setAttribute("class", "button dsgnmoo");
    cancellation.setAttribute("href", "JavaScript:cancelSelection()");
    cancellation.innerHTML = "Cancel";

    container.appendChild(removeAll);
    container.appendChild(remove);
    container.appendChild(cancellation);
}