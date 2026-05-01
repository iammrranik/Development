class Test extends Thread {
    String threadName;

    public Test(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            System.out.println(threadName + " = " + (i + 1));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class Main {


    public static void main(String[] args) {
        Test t1 = new Test("T1");
        Test t2 = new Test("T2");
        Test t3 = new Test("T3");

        System.out.println("Previous T1 = " + t1.isAlive());
        System.out.println("Previous T2 = " + t2.isAlive());
        System.out.println("Previous T3 = " + t3.isAlive());

        t1.start();

        System.out.println("Previous T1 = " + t1.isAlive());
        System.out.println("Previous T2 = " + t2.isAlive());
        System.out.println("Previous T3 = " + t3.isAlive());

        t2.start();
        System.out.println("Previous T1 = " + t1.isAlive());
        System.out.println("Previous T2 = " + t2.isAlive());
        System.out.println("Previous T3 = " + t3.isAlive());

        t3.start();
        System.out.println("Previous T1 = " + t1.isAlive());
        System.out.println("Previous T2 = " + t2.isAlive());
        System.out.println("Previous T3 = " + t3.isAlive());

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("New T1 = " + t1.isAlive());
        System.out.println("New T2 = " + t2.isAlive());
        System.out.println("New T3 = " + t3.isAlive());

        System.out.println("Main thread finish");
    }
}