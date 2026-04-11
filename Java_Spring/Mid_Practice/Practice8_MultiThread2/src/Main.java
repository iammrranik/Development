
class Print implements Runnable {

    Thread t;

    public Print() {
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        for (int i = 6; i <= 10; i++) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}

public class Main {

    public static void loop(){
        for (int i = 1; i <= 5; i++) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        Print p1 = new Print();
        Print p2 = new Print();

//        p1.t.start();
//        p2.t.start();
        loop();


    }
}