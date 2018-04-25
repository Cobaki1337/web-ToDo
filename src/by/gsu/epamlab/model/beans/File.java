package by.gsu.epamlab.model.beans;

public class File {
    private String id;
    private String fileName;
    private String filePath;

    public File(){
        super();
    }

    public File(String id, String name, String path) {
        this.id = id;
        this.fileName = name;
        this.filePath = path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
