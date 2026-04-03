import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Predicate<Integer> isEven2 = integer -> integer % 2 == 0;
        boolean res = isEven2.test(1);
        System.out.println(res);

        Function<Integer, Integer> triple = integer -> integer * 3;
        int res2 = triple.apply(30);
        System.out.println(res2);

        Function<Integer, Double> triple2 = integer -> Double.valueOf(integer * 3);
        double res3 = triple.apply(30);
        System.out.println(res3);

        Consumer<Integer> consume = x -> System.out.println(x);
        consume.accept(20);

        Supplier<Integer> supply = () -> 200;
        int val = supply.get();
        System.out.println(val);

        List<Integer> nums = List.of(100, 21, 331 ,41 ,150);
        nums.stream().forEach(consume);

        long totalEven = nums.stream()
                .filter(isEven2)
                .map(triple)
                .count();

        long totalEven2 = nums.stream()
                .filter(x -> x % 2 == 0)
                .map(x -> x * 3)
                .count();
        nums.stream()
                .filter(x -> x % 2 == 0)
                .map(x -> x * 3)
                .forEach(System.out::println);

        System.out.println(totalEven);
        System.out.println(totalEven2);

    }
}