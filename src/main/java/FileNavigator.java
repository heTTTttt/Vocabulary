import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileNavigator {
    private Map<String, List<FileData>> filesByPath;

    public FileNavigator() {
        filesByPath = new HashMap<>();
    }

    public void add(FileData file) {
        String path = file.getPath();
        if (!pathExists(path)) {
            filesByPath.put(path, new ArrayList<>());
        }
        List<FileData> files = filesByPath.get(path);
        if (!containsFile(files, file)) {
            if (isPathConsistent(path, file.getPath())) {
                files.add(file);
            } else {
                System.out.println("Error: File path is not consistent with the path key.");
            }
        } else {
            System.out.println("Error: File with the same name and path already exists.");
        }
    }

    public List<FileData> find(String path) {
        return filesByPath.getOrDefault(path, new ArrayList<>());
    }

    public List<FileData> filterBySize(long maxSize) {
        List<FileData> filteredFiles = new ArrayList<>();
        for (List<FileData> files : filesByPath.values()) {
            for (FileData file : files) {
                if (file.getSize() <= maxSize) {
                    filteredFiles.add(file);
                }
            }
        }
        return filteredFiles;
    }

    public void remove(String path) {
        filesByPath.remove(path);
    }

    public List<FileData> sortBySize() {
        List<FileData> allFiles = new ArrayList<>();
        for (List<FileData> files : filesByPath.values()) {
            allFiles.addAll(files);
        }
        allFiles.sort((f1, f2) -> Long.compare(f1.getSize(), f2.getSize()));
        return allFiles;
    }

    private boolean pathExists(String path) {
        return filesByPath.containsKey(path);
    }

    private boolean containsFile(List<FileData> fileList, FileData fileData) {
        for (FileData fd : fileList) {
            if (fd.getPath().equals(fileData.getPath()) && fd.getFileName() != null && fd.getFileName().equals(fileData.getFileName())) {
                return true;
            }
        }
        return false;
    }
    private boolean isPathConsistent(String pathKey, String filePath) {
        return filePath.startsWith(pathKey);
    }
}