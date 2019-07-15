package com.thoughtworks.tdd;

import java.rmi.server.ExportException;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingBoy {

    private List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public Ticket park(Car car) throws Exception{
        List<ParkingLot> parkingLotByCarExist = parkingLots.stream()
                .filter(x -> x.getParkingCarTicket().containsValue(car))
                .collect(Collectors.toList());
        List<ParkingLot> parkingLotByParkCar = parkingLots.stream()
                .filter(x -> x.getParkedQuantity() < x.getCapacity())
                .collect(Collectors.toList());
        if (car != null && parkingLotByCarExist.size() == 0 && parkingLotByParkCar.size() > 0) {
            return parkingLotByParkCar.get(0).park(car);
        } else {
            throw new Exception("Not enough position.");
        }
    }

    public Car fetch(Ticket ticket) throws Exception{
        List<ParkingLot> parkingLotByCar = parkingLots.stream()
                .filter(x -> x.getParkingCarTicket().containsKey(ticket))
                .collect(Collectors.toList());

        if (ticket == null) {
            throw new Exception("Please provide your parking ticket.");
        } else if (ticket.isWrong() || ticket.isUsed()){
            throw new Exception("Unrecognized parking ticket.");
        } else {
            return parkingLotByCar.get(0).getCar(ticket);
        }
    }

    public String giveFetchMessage(Ticket ticket) {
        if (ticket == null) return "Please provide your parking ticket.";
        if (ticket.isWrong() || ticket.isUsed()) {
            return "Unrecognized parking ticket.";
        } else {
            return "Right";
        }
    }

    public String giveParkMessage(Ticket ticket) {
        if (ticket == null) return "Not enough position.";
        return "Right";
    }
}
