import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileNavigator {
    private Map<String, List<FileData>> files;

    public FileNavigator(String path) {
        files = new HashMap<>();
    }

    public void add(String path) {
        File file = new File(path).getAbsoluteFile();
        if (file.exists() && file.isFile()) {
            String directory = file.getParent();
            if (files.containsKey(directory)) {
                List<FileData> fileList = files.get(directory);
                fileList.add(new FileData(file.getName(), file.getTotalSpace()));
            } else {
                List<FileData> fileList = new ArrayList<>();
                fileList.add(new FileData(file.getName(), file.getTotalSpace()));
                files.put(directory, fileList);
            }
        }
    }

    public List<String> getFiles(String directory){
        if (files.containsKey(directory)) {
            List<FileData> fileList = files.get(directory);
            List<String> fileNames = new ArrayList<>();
            for (FileData fileData : fileList) {
                fileNames.add(fileData.getFileName());
            }
            return fileNames;
        }else {
            return new ArrayList<>();
        }
    }

    public List<FileData> find(String path) {
        return files.getOrDefault(path, new ArrayList<>());
    }

    public List<FileData> filterBySize(long maxSize) {
        List<FileData> filteredFiles = new ArrayList<>();
        for (List<FileData> files : files.values()) {
            for (FileData file : files) {
                if (file.getSize() <= maxSize) {
                    filteredFiles.add(file);
                }
            }
        }
        return filteredFiles;
    }

    public void remove(String path) {
        files.remove(path);
    }

    public List<FileData> sortBySize() {
        List<FileData> allFiles = new ArrayList<>();
        for (List<FileData> files : files.values()) {
            allFiles.addAll(files);
        }
        allFiles.sort((f1, f2) -> Long.compare(f1.getSize(), f2.getSize()));
        return allFiles;
    }

    private boolean pathExists(String path) {
        return files.containsKey(path);
    }

    private boolean containsFile(@NotNull List<FileData> fileList, FileData fileData) {
        for (FileData fd : fileList) {
            if (fd.getFileName().equals(fileData.getFileName()) && fd.getFileName() != null && fd.getFileName().equals(fileData.getFileName())) {
                return true;
            }
        }
        return false;
    }
    private boolean isPathConsistent(String pathKey, String filePath) {
        return filePath.startsWith(pathKey);
    }
}