public class Main {

    public static void loop(){
        for (int i=1; i<=5; i++){
            System.out.println("From Main Loop " + i);
            try{
                Thread.sleep(1000);
            }catch(Exception e){
                System.out.println("Time error");
            }
        }
    }

    public static void main(String[] args) {
        loop();

        Thread thread1 = new Print();
        Thread thread2 = new Print();

        System.out.println(Thread.currentThread().getName());
        System.out.println(thread1.getName() + " " + thread1.getState());
        System.out.println(thread2.getName() + " " + thread2.getState());

        thread1.setPriority(Thread.MIN_PRIORITY);

        thread1.start();
        thread2.start();


        System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getState());
        loop();
    }
}

class Print extends Thread {
    @Override
    public void run(){

        System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getState());
        for (int i=6; i<=10; i++){
            System.out.println(this.getName() + " " + this.isAlive() + " From Thread Loop " + i);
            try{
                Thread.sleep(1000);
            }catch(Exception e){
                System.out.println("Time error");
            }
        }
    }
}