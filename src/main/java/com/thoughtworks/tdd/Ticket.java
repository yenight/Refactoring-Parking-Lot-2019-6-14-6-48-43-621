package com.thoughtworks.tdd;

public class Ticket {
    private boolean isUsed = false;
    private boolean isWrong = false;

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public boolean isWrong() {
        return isWrong;
    }

    public void setWrong(boolean wrong) {
        isWrong = wrong;
    }
}
