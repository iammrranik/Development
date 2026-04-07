import java.time.LocalDate;

public abstract class Employee {

    private int id;
    private String name, dob, email;
    private LocalDate joiningDate;

    public Employee(int id, String name , String dob, String email, LocalDate joiningDate) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.joiningDate = joiningDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public abstract int getMaxVacation();

    public abstract int getMaxSick();

    public int calculateLeave(int leaves) {
        int year = this.getJoiningDate().getYear();
        LocalDate endOfYear = LocalDate.of(year, 12, 31);

        long daysWorked = (endOfYear.toEpochDay() - this.getJoiningDate().toEpochDay() ) + 1;

        int daysInYear;
        if(this.getJoiningDate().isLeapYear()) {
            daysInYear = 366;
        }else{
            daysInYear = 365;
        }

        double result = (double)(daysWorked * leaves)/ daysInYear;

        double decimalPart = result - (int) result;
        if (decimalPart < 0.5) {
            return (int) result;
        } else {
            return (int) result + 1;
        }
    }

    @Override
    public String toString() {
        return  "-------------------------------------------" + "\n" +
                "ID: " + this.getId() + "\n" +
                "Name: " + this.getName() + "\n" +
                "Email: " + this.getEmail() + "\n" +
                "Joining Date: " + this.getJoiningDate() + "\n" +
                "Vacation Leave: " + calculateLeave(getMaxVacation()) + "\n" +
                "Sick Leave: " + calculateLeave(getMaxSick()) + "\n" +
                "-------------------------------------------" + "\n";
    }



}


