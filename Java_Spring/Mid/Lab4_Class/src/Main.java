public class Main {
    public static void main(String[] args) {
        Thread t1 = Thread.currentThread();
        System.out.println(t1.getName());
        System.out.println(t1.getPriority());
        System.out.println(t1.getState());

        for (int i = 6; i <= 10; i++) {
            System.out.println("From Main = " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        Print p1 = new Print();
        Print p2 = new Print();
    }
}

class Print implements Runnable {

    Thread t;

    public Print() {
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("From Print = " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
