//class Print extends Thread{
//    @Override
//    public void run(){
//        for (int i=1; i<=5; i++){
//            System.out.println(this.getName()+ " = From Thread "  + i);
//        }
//    }
//}

class Print implements Runnable{

    Thread t;

    public Print(){
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run(){
        for (int i=1; i<=5; i++){
            System.out.println("From Thread "  + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class Main{
    public static void main(String[] args){

        for (int i=6; i<=10; i++){
            System.out.println("From Main "  + i);
        }

        Print t1 = new Print();
        Print t2 = new Print();
        Print t3 = new Print();

//        t1.start();
//        t2.start();
//        t3.start();

        for (int i=6; i<=10; i++){
            System.out.println("From Main "  + i);
        }

    }
}