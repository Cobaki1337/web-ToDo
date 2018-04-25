function initFileModel(taskFile, idTask) {
    document.getElementById("withFile").style.display = "block";
    document.getElementById("withoutFile").style.display = "block";

    showFileModelForm();
    if (taskFile === 0){
        withoutFileForm(idTask);
    }else {
        withFileForm(idTask);
    }
}

function downloadFile(idTask) {
    post('/downloadFile',{'fileId':idTask});
}

function removeFile(idTask) {
    var xhr = new XMLHttpRequest();
    xhr.addEventListener('load', uploadComplete, false);
    var params = "fileId=" + encodeURIComponent(idTask);
    xhr.open('POST', '/removeFile', true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send(params);
    function uploadComplete() {
        document.getElementById("fileName").innerHTML = "File has been removed.";
        initTodayTable();
    }
}

function uploadFile(idTask) {
    var fileChooser = document.getElementById('chooseFile');
    var flag = true;

    if (fileChooser.files[0]) {
        var file = fileChooser.files[0];
        var formData = new FormData();
        formData.append('fileId', idTask);
        formData.append('file', file);
        var xhr = new XMLHttpRequest();
        xhr.upload.addEventListener("progress", uploadProgress, false);
        xhr.addEventListener('load', uploadComplete, false);
        xhr.open('POST', '/uploadFile', true);
        xhr.send(formData);
        fileChooser.value = "";

        var filename = file.name;

        function uploadProgress(event) {
            var progress = Math.round(event.loaded / event.total * 100);
            document.getElementById('progressPercents').innerHTML = progress + "";
        }

        function uploadComplete() {
            initTodayTable();
            document.getElementById('progressPercents').innerHTML = "";
        }
    }
}

function withFileForm(idTask) {
    document.getElementById("withoutFile").style.display = "none";
    document.getElementById("downloadFile").setAttribute("onclick", "downloadFile(" + idTask + ")");
    document.getElementById("removeFile").setAttribute("onclick", "removeFile(" + idTask + ")");
    document.getElementById("fileName").innerHTML = getFileName(idTask);
}

function withoutFileForm(idTask) {
    document.getElementById("withFile").style.display = "none";
    document.getElementById("uploadFile").setAttribute("onclick", "uploadFile(" + idTask + ")");
}

function post(path, params, method) {
    method = method || "post";

    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);

    for(var key in params) {
        if(params.hasOwnProperty(key)) {
            var hiddenField = document.createElement("input");
            hiddenField.setAttribute("type", "hidden");
            hiddenField.setAttribute("name", key);
            hiddenField.setAttribute("value", params[key]);

            form.appendChild(hiddenField);
        }
    }

    document.body.appendChild(form);
    form.submit();
}

function getFileName(idTask) {
    var xhr = new XMLHttpRequest();
    var params = "fileId=" + encodeURIComponent(idTask);
    xhr.open('POST', '/getFileName', false);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send(params);
    return xhr.responseText;
}

function removeAllFiles() {
    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/removeAllFiles', false);
    xhr.send();
}

//----------------------------------------------