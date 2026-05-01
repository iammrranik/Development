//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println("This is our first program.");

        Student student1= new Student(1, "X");
        Student student2=new Student();
        student2.setId(2);
        student2.setName("X");

        Student student3= new Student(1, "X");

        System.out.println(student1);
        System.out.println(student2);

        System.out.println(student1.getClass());

        boolean is1equal2= student1==student2;
        boolean is1equal3= student1==student3;
        boolean is2equal3= student2==student3;

        System.out.println("is1equal2 = "+ is1equal2);
        System.out.println("is1equal3 = "+ is1equal3);
        System.out.println("is2equal3 = "+ is2equal3);

        is1equal2= student1.equals(student2);
        is1equal3= student1.equals(student3);
        is2equal3= student2.equals(student3);

        System.out.println("is1equal2 = "+ is1equal2);
        System.out.println("is1equal3 = "+ is1equal3);
        System.out.println("is2equal3 = "+ is2equal3);

        System.out.println(student1.hashCode());
        System.out.println(student2.hashCode());
        System.out.println(student1.hashCode());
        System.out.println(student2.hashCode());
        System.out.println(student1.hashCode());
        System.out.println(student2.hashCode());
        System.out.println(student1.hashCode());
        System.out.println(student2.hashCode());

        Day d = Day.SATURDAY;
        System.out.println(d);
        System.out.println(d.name());
        System.out.println(d.ordinal());
        System.out.println(d.toString());

        Day[] days = Day.values();

        for(Day d1: days){
            System.out.println(d1);
        }

        Day sunday=Day.valueOf("SUNDAY");
        System.out.println(Day.valueOf("SUNDAY"));



    }

}
