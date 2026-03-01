public class Portal implements Runnable{
    TicketOffice ticketOffice;

    public Portal(TicketOffice ticketOffice){
        this.ticketOffice=ticketOffice;
    }

    @Override
    public void run(){
        while (ticketOffice.getAvailableTickets()>0){
            ticketOffice.sellTicket();
            try{
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Error");
            }
        }

    }
}
