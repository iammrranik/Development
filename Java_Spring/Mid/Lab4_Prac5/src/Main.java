import java.io.*;

public class Main {
    public static void main (String[] args){
        File file = new File("src/data.txt");
        if (!file.exists()) {
            System.out.println("ভুল: 'data.txt' ফাইলটি পাওয়া যায়নি!");
            System.out.println("দয়া করে ফাইলটি এখানে রাখুন: " + file.getAbsolutePath());
            return;
        }
        try {
            FileReader fileReader = new FileReader(file);
            int data;
            while ( (data = fileReader.read()) != -1) {
                System.out.println((char)data);
            }
//            InputStream inputStream = new FileInputStream(file);
//            file.canWrite();
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }
}
