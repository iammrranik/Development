
class CallMe {
    synchronized void call(String msg) {
        System.out.println(msg);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class Caller implements Runnable {
    String msg;
    CallMe target;
    Thread t;

    public Caller(String msg, CallMe target) {
        this.msg = msg;
        this.target = target;
        t = new Thread(this);
    }

    @Override
    public void run() {
        target.call(msg);
    }
}

public class Main {
    public static void main(String[] args) {
        CallMe cm = new CallMe();
        Caller c1 = new Caller("Hello", cm);
        Caller c2 = new Caller("Synchronized", cm);
        Caller c3 = new Caller("World", cm);

        c1.t.start();
        c2.t.start();
        c3.t.start();
    }
}
