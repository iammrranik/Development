public class Main{

    public static void printShapes(Shape... shapes){
        for(Shape shape: shapes){
            shape.calculateArea();
            System.out.println(shape.toString());
        }
    }

    public static void main(String[] args){
        Shape circle1=new Circle(10);
        Shape circle2=new Circle(20);
        Shape rectangle1=new Rectangle(2,4);

        printShapes(circle1, circle2, rectangle1);

    }
}