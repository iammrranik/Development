public class Company {
    String companyId;

    public Company(String companyId){
        this.setCompanyId(companyId);
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public static class Department{
        String department;

        public Department(String department){
            this.setDepartment(department);
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public void displayDepartment(){
            System.out.print("Department: "+department);
        }
    }

    public class Employee{
        String name;
        Position position;
        Department department;


        public Employee(String name, Position position, Department department){
            this.setName(name);
            this.setPosition(position);
            this.setDepartment(department);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Position getPosition() {
            return position;
        }

        public void setPosition(Position position) {
            this.position = position;
        }

        public Department getDepartment() {
            return department;
        }

        public void setDepartment(Department department) {
            this.department = department;
        }

        public void showEmployee(){
            System.out.print("Name: "+getName()+ " , Company: "+ getCompanyId()+" ,Position: "+ getPosition());
            System.out.print(", ");
            department.displayDepartment();
            System.out.println();
        }

    }
}
