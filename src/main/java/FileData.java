public class FileData {
    private String fileName;
    private long size;
    private String path;

    public FileData(String fileName, long size, String path) {
        this.fileName = fileName;
        this.size = size;
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public long getSize() {
        return size;
    }

    public String getPath() {
        return path;
    }

    public void addFile(String fileName, long size){
        this.fileName = fileName;
        this.size = size;
    }

    public String toString(){
        return "File: " + fileName + ", Size: " + size + ", Path: " + path;
    }
}
