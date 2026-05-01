public class Student implements Person {
    int id;
    String name;
    Department department;

    public Student(int id, String name, Department department){
            this.setId(id);
            this.setName(name);
            this.setDepartment(department);
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

    public Department getDepartment(){
        return department;
    }

    public void setDepartment(Department department){
        this.department=department;
    }


    public void displayInfo(){
        System.out.println("Id: "+this.getId());
        System.out.println("Name: "+this.getName());
        System.out.println("Department: "+this.getDepartment());
    }

}
