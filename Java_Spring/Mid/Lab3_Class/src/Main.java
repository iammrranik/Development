
import Lab3_Class.Person;

import static Person.show;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    Integer i1=new Integer(10);
    Integer i2= 20; // Autoboxing
    System.out.println(i1);

    int i3=i1; //Auto unboxing

    List<Integer> list=new ArrayList<>();
    list.add(20);

    // readability issue - static
//    Person.show();

    show(); // after importing I can use this
    // If there is multiple static methods in classes then I t will create ambiquity
    // So not using imports is better now for clean code


}
