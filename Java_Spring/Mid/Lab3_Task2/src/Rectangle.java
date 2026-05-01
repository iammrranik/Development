public class Rectangle implements Shape {

    double x,y;
    double area;

    public Rectangle(double x, double y){
        this.setX(x);
        this.setY(y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public void calculateArea() {
        this.area= (this.getX())*(this.getY());
    }

    @Override
    public double getArea(){
        return area;
    }

    @Override
    public String toString(){
        return "Area of rectangle: "+this.getArea();
    }
}
