public class Main {
    public static void main(String[] args) throws InterruptedException {
        int[] data = new int[100000];
        for (int i = 0; i < 100000; i++) {
            data[i] = i + 1;
        }

        int key = 76590;
        boolean flag = false;
        long startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            if (data[i] == key) {
                flag = true;
            }
        }
        long endTime = System.nanoTime();

        if (flag) {
            System.out.println("Record found");
        }
        else {
            System.out.println("Record NOT found");
        }

        System.out.println("Linear : Total Duration in ms: " + ((endTime - startTime)/1000000));

        long startTimeMultiThread = System.nanoTime();
        Search search1 = new Search(0, data.length / 2, data, key);
        Search search2 = new Search(data.length / 2, data.length, data, key);
        search1.start();
        search2.start();
        search1.join();
        search2.join();
        long endTimeMultiThread = System.nanoTime();

        System.out.println("Multithreading: Total Duration in ms: " + ((endTimeMultiThread - startTimeMultiThread)/1000000));
    }
}

class Search extends Thread {
    int firstIndex;
    int lastIndex;
    int[] arr;
    int searchKey;
    volatile boolean flag = false;

    public Search(int firstIndex, int lastIndex, int[] arr, int searchKey) {
        this.firstIndex = firstIndex;
        this.lastIndex = lastIndex;
        this.arr = arr;
        this.searchKey = searchKey;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag) {
            return;
        }

        for(int i = firstIndex; i < lastIndex; i++) {
            if (arr[i] == searchKey) {
                setFlag(true);
                System.out.println(Thread.currentThread().getName() + " : " + "Value Found");
                return;
            }
        }
    }
}