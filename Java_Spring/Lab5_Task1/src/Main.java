import java.util.ArrayList;
<<<<<<< Updated upstream
import java.util.Collections;
=======
>>>>>>> Stashed changes
import java.util.Comparator;
import java.util.List;

public class Main{
    static void main(String[] args) {
<<<<<<< Updated upstream
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "A", 18, 3.75f));
        students.add(new Student(2, "B", 20, 3.8f));
        students.add(new Student(3, "C", 19, 3.50f));

        Collections.sort(students);
        System.out.println(students);

        students.sort(new StudentCgpaSort());
        System.out.println(students);
    }
}


class Student implements Comparable<Student>{
    private int id;
    private String name;
    private int age;
    private float cgpa;

    public Student(int id, String name, int age, float cgpa){
        this.setId(id);
        this.setName(name);
        this.setAge(age);
        this.setCgpa(cgpa);
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getCgpa() {
        return cgpa;
    }

    public void setCgpa(float cgpa) {
        this.cgpa = cgpa;
    }

    @Override
    public String toString(){
        return "Student("+ "Id: " + this.getId() +
                ", Name: " + this.getName() +
                ", Age: "+ this.getAge() +
                ", CGPA: " + this.getCgpa() +
                ")";
    }


    @Override
    public int compareTo(Student student){
        return Integer.compare(this.age, student.age);
    }


}



class StudentCgpaSort implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        return Float.compare(o2.getCgpa(), o1.getCgpa());
    }

}
=======
        List<Integer> list = new ArrayList<>();

        list.add(10);
        list.add(40);
        list.add(50);
        list.add(20);
        list.add(40);

        @Override
        void Comparator()

        list.sort();

        for(Integer item: list){
            System.out.println(item);
        }
    }
}
>>>>>>> Stashed changes
