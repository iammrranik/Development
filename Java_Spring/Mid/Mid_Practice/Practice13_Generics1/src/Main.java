class Box<T> {
    private T box;

    public T getBox() {
        return box;
    }

    public void setBox(T box) {
        this.box = box;
    }
}

public class Main {
    public static void main(String[] args) {
        Box<Integer> box = new Box<>();
        box.setBox(1); // Box is now type safe -> Compile time error
        System.out.println(box.getBox());
    }
}