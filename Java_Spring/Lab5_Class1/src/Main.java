import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "X", 20));
        students.add(new Student(2, "Y", 21));
        students.add(new Student(3, "Z", 19));

        Collections.sort(students);
        System.out.println(students);

        students.sort(new StudentIdSort());
        System.out.println(students);
    }
}
