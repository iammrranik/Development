public class Main {
    public static void main(String[] args) {

        int[] data = new int[100000];

        for (int i = 0; i < 100000; i++) {
            data[i] = i + 1;
        }

        int key = 76590;

        // ---------------- LINEAR SEARCH ----------------
        boolean flag = false;

        long startTime = System.nanoTime();

        for (int i = 0; i < 100000; i++) {
            if (data[i] == key) {
                flag = true;
                break; // ⚡ early stop (important improvement)
            }
        }

        long endTime = System.nanoTime();

        System.out.println(flag ? "Record found" : "Record NOT found");
        System.out.println("Linear : Total Duration in ms: " + ((endTime - startTime) / 1000000));

        // ---------------- MULTITHREAD SEARCH ----------------

        SharedFlag sharedFlag = new SharedFlag();

        long startTimeMultiThread = System.nanoTime();

        Search search1 = new Search(0, data.length / 2, data, key, sharedFlag);
        Search search2 = new Search(data.length / 2, data.length, data, key, sharedFlag);

        search1.start();
        search2.start();

        try {
            search1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            search2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long endTimeMultiThread = System.nanoTime();

        System.out.println("Multithreading: Total Duration in ms: " +
                ((endTimeMultiThread - startTimeMultiThread) / 1000000));
    }
}

// ---------------- SHARED FLAG CLASS ----------------
class SharedFlag {
    volatile boolean found = false;
}

// ---------------- SEARCH THREAD ----------------
class Search extends Thread {

    int firstIndex;
    int lastIndex;
    int[] arr;
    int searchKey;
    SharedFlag flag;

    public Search(int firstIndex, int lastIndex, int[] arr, int searchKey, SharedFlag flag) {
        this.firstIndex = firstIndex;
        this.lastIndex = lastIndex;
        this.arr = arr;
        this.searchKey = searchKey;
        this.flag = flag;
    }

    @Override
    public void run() {

        for (int i = firstIndex; i < lastIndex && !flag.found; i++) {

            if (arr[i] == searchKey) {
                flag.found = true;

                System.out.println(Thread.currentThread().getName()
                        + " : Value Found at index " + i);

                return;
            }
        }
    }
}