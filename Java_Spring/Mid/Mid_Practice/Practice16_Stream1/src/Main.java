import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        /*
        int count = 0;
        for (Integer integer : numbers) {
            if (integer % 2 == 0) {
                count++;
            }
        }
        System.out.println(count);
        */

        System.out.println(numbers.stream().filter(x -> x%2==0).count());


        numbers.stream()
            .filter(x -> x%2==0)
                .map(x -> x*x)
                .forEach(System.out::println);



    }
}