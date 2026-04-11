public class Main {
    public static int sum(int a, int b){ // Method 1
        return a+b;
    }
    public int sum(int a, int b, int c){ // Overloaded Method
        return a+b+c;
    }
    public int sum(int a, int b, int c, int d){ // Overloaded Method
        return a+b+c+d;
    }
//    static int sum(int... arr){ // No compulsory variables
//        int result=0;
//        for(int i: arr){
//            result+=i;
//        }
//        return result;
//    }

    public int sum(int x, int... arr){ // 1 variable input is compulsory
        int result=x;
        for(int i: arr){
            result+=i;
        }
        return result;
    }

    public void main(String[] args) {
        System.out.println(sum(1)); // static int sum(int x, int... arr)
        System.out.println(sum(1,2));
        System.out.println(sum(1,2,3));
        System.out.println(sum(1,2,3,4));
        System.out.println(sum(1,2,3,4,5));
        System.out.println(sum(1,2,3,4,5,6));
//        System.out.println(sum()); // static int sum(int... arr)
    }
}