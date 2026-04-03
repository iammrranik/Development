public class Main {
    public static void main(String[] args) {
        Thread t1 = Thread.currentThread();
        System.out.println(t1.getName());
        System.out.println(t1.getPriority());
        System.out.println(t1.getState());

        Print p1 = new Print();
        Print p2 = new Print();

        p1.start();
        p2.start();

        for (int i = 6; i <= 10; i++) {
            System.out.println("From Main = " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Print extends Thread {

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