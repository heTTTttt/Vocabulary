import java.util.List;

public class Main {
    public static void main(String[] args) {

        FileNavigator fileNavigator = new FileNavigator();

        fileNavigator.add(new FileData("file1.txt", 1024,"C:/GitRep/Vocabulary"));
        fileNavigator.add(new FileData("file2.txt", 2048,"C:/GitRep/Vocabulary"));
        fileNavigator.add(new FileData("file3.txt", 3072,"C:/GitRep/Vocabulary"));

        // Пошук файлів за шляхом "C:/GitRep/Vocabulary"
        List<FileData> files = fileNavigator.find("C:/GitRep/Vocabulary");
        System.out.println("Files in C:/GitRep/Vocabulary: ");
        for (FileData file : files){
            System.out.println(file.getFileName());
        }

        System.out.println("Files from C:/GitRep/Vocabulary: "+ "\n" + fileNavigator.find("C:/GitRep/Vocabulary")); // цей рядок зроблено для наочності.

        System.out.println("--------------------------------");

        // Фільтрація файлів за розміром
        List<FileData> filteredFiles = fileNavigator.filterBySize(2048);
        System.out.println("Files with size <= 2048:");
        for (FileData file : filteredFiles){
            System.out.println(file.getFileName());
        }

        System.out.println("--------------------------------");

        // Видалення шляху "C:/GitRep/Vocabulary"
        System.out.println("remove file");
        fileNavigator.remove("C:/GitRep/Vocabulary/file1.txt");
        List<FileData> file1 = fileNavigator.find("C:/GitRep/Vocabulary/file1.txt");
        if (file1.isEmpty()){
            System.out.println("Видалення успішне");
        }else {
            System.out.println("Видалення невдале");
        }

        System.out.println("--------------------------------");

        // Спроба додати FileData з невідповідним шляхом до існуючого ключа
        fileNavigator.add(new FileData("file3.txt", 200,"C:/GitRep/Vocabulary"));

        System.out.println("--------------------------------");

        System.out.println("Sort by size");
        List<FileData> sortBySizeFiles = fileNavigator.sortBySize();
        for (FileData file : sortBySizeFiles){
            System.out.println("File name " + file.getFileName() + " with size " + file.getSize());
        }
    }
}
