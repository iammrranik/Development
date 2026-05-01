//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    var company1=new Company("Anik Telecom");

    var department=new Company.Department("SQA");

    var employee1=company1.new Employee("Anika", Position.MANAGER, department);
    var employee2=company1.new Employee("Ani", Position.DEVELOPER, department);
    var employee3=company1.new Employee("Nik", Position.HR, department);

    Company.Employee[] employees= {employee1, employee2, employee3};

//    employee1.showEmployee();
//    employee2.showEmployee();

    for(var employee: employees){
        employee.showEmployee();
    }
}
