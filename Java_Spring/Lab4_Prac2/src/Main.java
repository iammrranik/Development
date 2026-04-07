public class Main{

    public static void main() {
        Print p1 = new Print();
        Print p2 = new Print();

        for (int i=1; i<=5; i++){
            System.out.println("From Main = " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        p1.t.start();
        p2.t.start();

        for (int i=1; i<=5; i++){
            System.out.println("From Main = " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class Print implements Runnable{

        Thread t;

        public Print(){
            t = new Thread(this);
        }

        public void run(){
            for (int i=6; i<=10; i++){
                System.out.println("From Print = " + i + " " + t.getName());
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
