import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Account a1 = new Account("xyz", 500.0, "123");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("account.ser"));
        objectOutputStream.writeObject(a1);

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("account.ser"));
        Account a2 = (Account) objectInputStream.readObject();
        System.out.println(a2);
    }
}

class Account implements Serializable {
    private String title;
    private double balance;
    private transient String pin;

    public Account(String title, double balance, String pin) {
        this.title = title;
        this.balance = balance;
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "Account{" +
                "title='" + title + '\'' +
                ", balance=" + balance +
                ", pin='" + pin + '\'' +
                '}';
    }
}