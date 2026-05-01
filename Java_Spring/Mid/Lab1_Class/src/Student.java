import java.util.Objects;

public class Student {
    private int id;
    private String name;

    public Student(){
        System.out.println("This is the default constructor of Student");
    }

    public Student(int id, String name){
        System.out.println("This is the parameterized constructor of Student");
        this.id=id;
        this.name=name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString(){
        return id+" "+name;
    }

    @Override
    protected void finalize(){
        System.out.println("This is a finalize call.");
    }

    @Override
    public boolean equals(Object obj) {
        Student obj1 = (Student)obj;
        return this.name.equals(obj1.name) && this.id==obj1.id;
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, name);
    }



}

