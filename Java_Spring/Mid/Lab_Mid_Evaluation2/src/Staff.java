import java.time.LocalDate;

public class Staff extends Employee {

    public Staff(int id, String name , String dob, String email, LocalDate joiningDate) {
        super(id, name, dob, email, joiningDate);
    }

    @Override
    public int getMaxVacation() {
        return 10;
    }


    @Override
    public int getMaxSick() {
        return 7;
    }



}

