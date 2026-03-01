public class Main{
    public static void main(String[] args) {
        TicketOffice office = new TicketOffice();

        Thread portal1 = new Thread(new Portal(office), "1");
        Thread portal2 = new Thread(new Portal(office), "2");
        Thread portal3 = new Thread(new Portal(office), "3");

        System.out.println("Total ticket: "+ office.getAvailableTickets());

        portal1.start();
        portal2.start();
        portal3.start();

        try{
            portal1.join();
            portal2.join();
            portal3.join();
        } catch (InterruptedException e) {
            System.out.println("Error Occurred");
        }


    }
}