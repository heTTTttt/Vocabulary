import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        FileNavigator fileNavigator = new FileNavigator("C:\\GitRep\\Vocabulary");

        fileNavigator.add("C:\\GitRep\\Vocabulary\\file1.txt");
        fileNavigator.add("C:\\GitRep\\Vocabulary\\file2.txt");
        fileNavigator.add("C:\\GitRep\\Vocabulary\\file3.txt");

        // Пошук файлів за шляхом "C:/GitRep/Vocabulary"
        List<FileData> files = fileNavigator.find("C:\\GitRep\\Vocabulary");
        System.out.println("Files in C:/GitRep/Vocabulary: ");
        for (FileData file : files){
            System.out.println(file.getFileName());
        }

        System.out.println();

        System.out.println("Files from C:/GitRep/Vocabulary: "+ "\n" + fileNavigator.find("C:\\GitRep\\Vocabulary")); // цей рядок зроблено для наочності.

        System.out.println("--------------------------------");

        // Фільтрація файлів за розміром
        List<FileData> filteredFiles = fileNavigator.filterBySize(2048);
        System.out.println("Files with size <= 2048:");
        for (FileData file : filteredFiles){
            System.out.println(file.getFileName());
        }

        System.out.println("--------------------------------");

        System.out.println("Додавання файлу за іншим ключем:");
        // Спроба додати FileData з невідповідним шляхом до існуючого ключа
        FileData fileData = new FileData("test3.txt", new File("/another/path/test3.txt").getAbsoluteFile().getTotalSpace());
        fileNavigator.add(fileData.toString());

        // Виведемо список файлів за шляхом C:\GitRep\Vocabulary
        List<String> fileList = fileNavigator.getFiles("C:\\GitRep\\Vocabulary");
        System.out.println(fileList.toString());

        System.out.println("--------------------------------");

        System.out.println("Sort by size");
        List<FileData> sortBySizeFiles = fileNavigator.sortBySize();
        for (FileData f : sortBySizeFiles){
            System.out.println("File name " + f.getFileName() + " with size " + f.getSize());
        }
        // Видалення шляху "C:/GitRep/Vocabulary"
        System.out.println(" Видалення шляху C:/GitRep/Vocabulary");
        System.out.println(fileNavigator.getFiles("C:\\GitRep\\Vocabulary"));
        System.out.println("remove the way");
        fileNavigator.remove("C:\\GitRep\\Vocabulary");
        List<FileData> file = fileNavigator.find("C:\\GitRep\\Vocabulary");
        if (file.isEmpty()){
            System.out.println("Видалення успішне");
        }else {
            System.out.println("Видалення невдале");
        }
        System.out.println(fileNavigator.getFiles("C:\\GitRep\\Vocabulary"));
    }
}
