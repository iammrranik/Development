//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    Person[] personArray={new Student(1, "Anik", Department.CSE),
                            new Student(2, "Anika", Department.DS),
                            new Student(3, "Ani", Department.CNCS)
                        };

    for(Person person: personArray){
        person.displayInfo();
    }
}
