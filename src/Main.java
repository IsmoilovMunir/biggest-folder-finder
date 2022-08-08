import java.io.File;
import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
//        System.out.println(getHumanReadableSize(240640));
//        System.out.println(0);


        String folderPath = "/Users/munirismoilov/Desktop";
        File file = new File(folderPath);
        Node root = new Node(file);
        long start = System.currentTimeMillis();
        FolderSizeCalculator calculator = new FolderSizeCalculator(root);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(calculator);
        System.out.println(root);
        long duration = System.currentTimeMillis() - start;
        System.out.println(duration + " ms");

    }

    public static long getFolderSize(File folder) {
        if (folder.isFile()) {
            return folder.length();
        }
        long sum = 0;
        File[] files = folder.listFiles();
        for (File file : files) {
            sum += getFolderSize(file);
        }
        return sum;
    }
}