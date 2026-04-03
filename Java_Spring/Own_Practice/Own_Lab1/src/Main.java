public class Main {
    public static void main(){
        Thread t = new Thread();
        System.out.println(t.currentThread());
        System.out.println(t.getName());
        System.out.println(t.getState());

        Print p1 = new Print();
        Print p2 = new Print();
//        p1.run();
//        p2.run();
        p1.start();
        p2.start();

        for (int i=1; i<=5; i++){
            System.out.println(i);
        }
    }

    public static class Print extends Thread{
        public synchronized void run(){
            System.out.println(this.currentThread());
            for (int i=6; i<=10; i++){
                System.out.println(i);
            }
        }
    }
}