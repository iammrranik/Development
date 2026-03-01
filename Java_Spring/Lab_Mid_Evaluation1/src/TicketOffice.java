public class TicketOffice {
    int availableTickets = 100;

    public int getAvailableTickets(){
        return availableTickets;
    }


    public void setAvailableTickets(int availableTickets){
        this.availableTickets=availableTickets;
    }

    public synchronized void sellTicket(){
        if(this.getAvailableTickets()>0){
            setAvailableTickets(availableTickets-1);
            System.out.println("Portal "+Thread.currentThread().getName() + " sold a ticker. Remaining: "+ this.getAvailableTickets());

        }else{
            System.out.println("Sold out!");
        }
    }


}
