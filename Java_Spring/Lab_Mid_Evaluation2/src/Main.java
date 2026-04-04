import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        List<Employee> employeeList = new ArrayList<>();

        employeeList.add(
                new Staff(
                        1,
                        "Rowfun",
                        "1998-05-10",
                        "rowfun1@gmail.com",
                        LocalDate.of(2025, 1, 1)
                )
        );

        employeeList.add(
                new Staff(
                        2,
                        "Mohammad",
                        "1999-08-12",
                        "mohammad22@gmail.com",
                        LocalDate.of(2025, 12, 12)
                )
        );

        employeeList.add(
                new Officer(
                        3,
                        "Anik",
                        "1997-02-15",
                        "anik3@gmail.com",
                        LocalDate.of(2025, 1, 1)
                )
        );

        employeeList.add(
                new Officer(
                        4,
                        "Minhaj",
                        "1996-11-20",
                        "minhaj43@gmail.com",
                        LocalDate.of(2025, 12, 12)
                )
        );

        for (Employee employee: employeeList) {
            System.out.println(employee);
        }



    }



}

