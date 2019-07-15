package com.thoughtworks.tdd;

import java.util.HashMap;

public class ParkingLot {

    private HashMap<Ticket, Car> parkingCarTicket;
    private int capacity = 10;
    private int parkedQuantity = 0;

    public ParkingLot() {
        this.parkingCarTicket = new HashMap<>();
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.parkingCarTicket = new HashMap<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public HashMap<Ticket, Car> getParkingCarTicket() {
        return parkingCarTicket;
    }

    public int getParkedQuantity() {
        return parkedQuantity;
    }

    public void setParkedQuantity(int parkedQuantity) {
        this.parkedQuantity = parkedQuantity;
    }

    public Ticket park(Car car) {
        if (parkedQuantity < capacity) {
            Ticket ticket = new Ticket();
            parkingCarTicket.put(ticket, car);
            return ticket;
        } else {
            return null;
        }
    }

    public Car getCar(Ticket ticket) {
        Car car = parkingCarTicket.get(ticket);
        if (car != null) {
            parkingCarTicket.remove(ticket);
        }
        return car;
    }
}
