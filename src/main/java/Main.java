import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileNavigator fileNavigator = new FileNavigator();

        fileNavigator.add(new FileData("file1.txt", 1024, "/path/to/file-> C:/GitRep/Vocabulary"));
        fileNavigator.add(new FileData("file2.txt", 2048, "/path/to/file-> C:/GitRep/Vocabulary"));
        fileNavigator.add(new FileData("file3.txt", 3074, "/path/to/file-> C:/GitRep/Vocabulary"));

        // Пошук файлів за шляхом "C:/GitRep/Vocabulary"
        List<FileData> files = fileNavigator.find("/path/to/file-> C:/GitRep/Vocabulary");
        System.out.println("Files in /path/to/file-> C:/GitRep/Vocabulary");
        for (FileData file : files){
            System.out.println(file.getFileName());
        }

        System.out.println("--------------------------------");

        // Фільтрація файлів за розміром
        List<FileData> filteredFiles = fileNavigator.filterBySize(2048);
        System.out.println("Files with size <= 2048:");
        for (FileData file : filteredFiles){
            System.out.println(file.getFileName());
        }

        System.out.println("--------------------------------");

        // Видалення шляху "C:/GitRep/Vocabulary"
        fileNavigator.remove("/path/to/file-> C:/GitRep/Vocabulary");

        System.out.println("--------------------------------");

        // Спроба додати FileData з невідповідним шляхом до існуючого ключа
        fileNavigator.add(new FileData("file4.txt", 1024, "/path/to/file-> C:/GitRep/Vocabulary"));
    }
}
