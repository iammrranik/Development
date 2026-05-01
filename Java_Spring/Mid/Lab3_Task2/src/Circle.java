public class Circle implements Shape{

    double radius;
    double area;

    public Circle(double radius){
        this.setRadius(radius);
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public void calculateArea() {
        double PI=3.1416;
        this.area= PI* (radius*radius);
    }

    @Override
    public double getArea(){
        return area;
    }

    @Override
    public String toString(){
        return "Area of circle: "+this.getArea();
    }
}
