public class FileData {
    private String fileName;
    private long size;

    public FileData(String fileName, long size) {
        this.fileName = fileName;
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public long getSize() {
        return size;
    }

    public void addFile(String fileName, long size){
        this.fileName = fileName;
        this.size = size;
    }

    public String toString(){ return "File: " + fileName + ", Size: " + size; }
}
