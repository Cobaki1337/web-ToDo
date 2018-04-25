package by.gsu.epamlab.model.beans;

public class Task {
    private String id;
    private String idUser;
    private String taskName;
    private String taskDate;
    private String taskContent;
    private String taskFile;
    public Task(){
        super();
    }
    public Task(String id, String idUser, String taskName, String taskDate, String taskContent, String taskFile) {
        this.id = id;
        this.idUser = idUser;
        this.taskName = taskName;
        this.taskDate = taskDate;
        this.taskContent = taskContent;
        this.taskFile = taskFile;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getIdUser() {
        return idUser;
    }
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
    public String getTaskName() {
        return taskName;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public String getTaskDate() {
        return taskDate;
    }
    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }
    public String getTaskContent() {
        return taskContent;
    }
    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }
    public String getTaskFile() {
        return taskFile;
    }
    public void setTaskFile(String taskFile) {
        this.taskFile = taskFile;
    }
}
