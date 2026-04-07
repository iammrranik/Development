public class Main {
    static void main(String[] args) {
        int a=1;
        Integer b = 1; // Autoboxing
        Integer c = Integer.valueOf(1); // Behind the scene (Boxing)
        int d = c; // Autounboxing
        int e = b.intValue(); // Behind the scene (Unboxing)

        Integer x = 1;
        Integer y = 2;
        Integer z = null; // Wrapper class can be null but primitive data type can not
        System.out.println(x+y);

    }
}
