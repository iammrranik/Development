import java.util.Arrays;

class Calculator<N1 extends Number, N2 extends Number, N3 extends Number> {
    N1 val1;
    N2 val2;
    N3 val3;

    Calculator(){}

    Calculator(N1 val1, N2 val2, N3 val3) {
        this.val1 = val1;
        this.val2 = val2;
        this.val3 = val3;
    }

    public Double add() {
        return val1.doubleValue() + val2.doubleValue() + val3.doubleValue();
    }

    public Double add(N1 val1, N2 val2, N3 val3) {
        return val1.doubleValue() + val2.doubleValue() + val3.doubleValue();
    }
}

public class Main {

    <T> void printArray(T[] arr) {
        for(T element : arr) {
            System.out.print(element + " ");
        }
    }

    public static void main(String[] args) {
        Calculator<Integer, Float, Double> calculator = new Calculator<>(5, 10.10f, 100d);
        System.out.println(calculator.add());

        int[] intArray = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(intArray));
        String[] stringArray = {"a", "b", "c", "d", "e"};
        System.out.println(Arrays.toString(stringArray));
    }
}