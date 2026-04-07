public class Main {

    public static void main(){
        Thread t1= new Thread();
//        System.out.println(t1.currentThread());
//        System.out.println(t1.threadId());
//        System.out.println(t1.getName());
//        System.out.println(t1.getPriority());
//        System.out.println(t1.getThreadGroup());
//        System.out.println(t1.getThreadGroup().getName());
//        System.out.println(t1.getThreadGroup().getMaxPriority());
//        System.out.println(t1.getState());

        Print p1 = new Print();
        Print p2 = new Print();
        p1.start();
        p2.start();

        try{
            p1.join();
            p2.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (int i=1; i<=5; i++){
            System.out.println("From Main = " + t1.currentThread());
            System.out.println("From Main = " + i);
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }

    public static class Print extends Thread{

        public Print(){
        }

        @Override
        public void run(){
            for (int i=6; i<=10; i++){
                System.out.println("From Print = " + currentThread());
                System.out.println("From Print = " + i);
                try{
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
