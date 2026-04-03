import java.util.Arrays;

public class Main{
    static void main(String[] args) {
        Day day1 = Day.FRIDAY;
        System.out.println(day1 + " " +day1.name() + " " + day1.ordinal() + " " +
                day1.toString()+ " " + day1.hashCode() + " " + day1.getDay());

        Day[] days = Day.values();
        for(Day day: days){
            System.out.print(day+ " ");
        }
        System.out.println();

        System.out.println(days[2]);

        System.out.println(Day.valueOf("TUESDAY"));

    }
}