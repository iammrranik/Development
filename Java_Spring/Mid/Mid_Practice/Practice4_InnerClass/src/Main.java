public class Main {
    static void main(String[] args) {
        Car car1 = new Car("BMW"); // Member Inner Class
        Car.Engine engine = car1.new Engine();
        engine.start();
        engine.stop();

        Car car2 = new Car("Toyota"); // Member Inner Class
        Car.Engine engine1 = car2.new Engine();
        engine1.stop();
        engine1.start();

        Computer computer = new Computer("Dell", "Latest", "Windows 11"); // Member Inner Class
        System.out.println(computer.getOs().toString());
        Computer.USB usb1 = new Computer.USB("USB-C"); // Static Inner Class
        Computer.USB usb2 = new Computer.USB("USB-2.0"); // Static Inner Class
        Computer.USB usb3 = new Computer.USB("USB-3.0"); // Static Inner Class
        System.out.println(usb1.toString());
        System.out.println(usb2.toString());
        System.out.println(usb3.toString());

        ShoppingCart shoppingCart = new ShoppingCart(150);
        CreditCard creditCard = new CreditCard("1212"); // Replace of "Payment.java" Interface
        shoppingCart.processPayment(creditCard);
        shoppingCart.processPayment(new Payment() { // Anonymous Inner Class where we need to implement interface without creating its separate class
            @Override
            public void pay(double amount) {
                System.out.println("Paid " + amount + " using Credit Card");
            }
        });
        shoppingCart.processPayment(new Payment() { // Anonymous Inner Class where we need to implement interface without creating its separate class
            @Override
            public void pay(double amount) {
                System.out.println("Paid " + amount + " using Paypal");
            }
        });

        Hotel hotel = new Hotel("Aniks Hotel", 10, 5);
        hotel.reserveRoom("Anika", 2); // Local Inner Class
        hotel.reserveRoom("", 2); // Local Inner Class
        hotel.reserveRoom("Morjina", 5); // Local Inner Class

    }
}