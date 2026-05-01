class MyTask implements Runnable {
    private int count = 0;

    @Override
    public void run() {
        count++;
        System.out.println(Thread.currentThread().getName() + " কাজ করছে। কাউন্ট: " + count);
    }
}

public class Main {
    public static void main(String[] args) {
        MyTask sharedTask = new MyTask(); // মাত্র ১টি অবজেক্ট তৈরি হলো

        // একই অবজেক্ট ৩টি ভিন্ন থ্রেডকে দেওয়া হলো
        Thread t1 = new Thread(sharedTask, "Thread-1");
        Thread t2 = new Thread(sharedTask, "Thread-2");
        Thread t3 = new Thread(sharedTask, "Thread-3");

        // লজিক্যাল সেপারেশন: Runnable বলে— "এটা আমার কাজ", আর Thread বলে— "আমি একজন কর্মী"।
        // কর্মীকে বারবার পরিবর্তন করা যায়, কিন্তু কাজ একই থাকতে পারে।
        t1.start();
        t2.start();
        t3.start();
    }
}