import java.util.*;

class Util {

    // 1. ANY TYPE → print (accept anything) but no adding
    static void printList(List<?> list) {
        for (Object o : list) {
            System.out.print(o + " ");
        }
        System.out.println();
    }

    // 2. READ ONLY → sum numbers
    static double sum(List<? extends Number> list) {
        double total = 0;
        for (Number n : list) {
            total += n.doubleValue();
        }
        return total;
    }

    // 3️. WRITE ONLY → add integers
    static void addNumbers(List<? super Integer> list) {
        list.add(10);
        list.add(20);
    }
}

public class Main {
    public static void main(String[] args) {

        // ANY TYPE (? )
        List<String> words = Arrays.asList("A", "B", "C");
        Util.printList(words);

        // READ ONLY (? extends T)
        List<Integer> nums = Arrays.asList(1, 2, 3);
        System.out.println(Util.sum(nums));

        // WRITE ONLY (? super T)
        List<Number> numberList = new ArrayList<>();
        Util.addNumbers(numberList);

        numberList.add(100); // Also works

        System.out.println(numberList);
    }
}