package com.thoughtworks.tdd;

public class Ticket {
    private boolean isTicketUsed = false;
    private boolean isTicketWrong = false;

    public boolean isTicketUsed() {
        return isTicketUsed;
    }

    public void setTicketUsed(boolean ticketUsed) {
        isTicketUsed = ticketUsed;
    }

    public boolean isTicketWrong() {
        return isTicketWrong;
    }

    public void setTicketWrong(boolean ticketWrong) {
        isTicketWrong = ticketWrong;
    }
}
