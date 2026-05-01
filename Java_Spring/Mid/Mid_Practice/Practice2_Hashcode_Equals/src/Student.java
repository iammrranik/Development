public class Student {
    private int id;
    private String name;

    public Student(){
        System.out.println("This is a default constructor.");
    }

    public Student(int id, String name){
        System.out.println("This is a parameterized constructor.");
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }



}
