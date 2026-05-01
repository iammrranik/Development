import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Main{
    public static void main (String[] args) {
        long startTime=System.currentTimeMillis();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);

        long millis = System.currentTimeMillis();
        // মিলিসেকেন্ডকে তারিখে রূপান্তর
        var date = Instant.ofEpochMilli(millis)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        // নিজের ইচ্ছেমতো ফরম্যাট তৈরি করা
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        System.out.println("বর্তমান সময়: " + date.format(formatter));


    }
}