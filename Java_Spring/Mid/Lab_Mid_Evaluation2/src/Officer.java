import java.time.LocalDate;

public class Officer extends Employee {

    public Officer(int id, String name , String dob, String email, LocalDate joiningDate) {
        super(id, name, dob, email, joiningDate);
    }

    @Override
    public int getMaxVacation() {
        return 15;
    }

    @Override
    public int getMaxSick() {
        return 10;
    }



}

