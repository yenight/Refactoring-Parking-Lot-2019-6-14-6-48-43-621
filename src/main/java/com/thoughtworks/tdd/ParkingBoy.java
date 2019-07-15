package com.thoughtworks.tdd;

import com.thoughtworks.tdd.Exception.NoTicketProvideException;
import com.thoughtworks.tdd.Exception.NotPositionEnoughException;
import com.thoughtworks.tdd.Exception.UnrecognizedTicketException;

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
                .filter(x -> x.getParkingCarTicketContainsValue(car))
                .collect(Collectors.toList());
        List<ParkingLot> parkingLotByParkCar = parkingLots.stream()
                .filter(x -> x.getParkedQuantity() < x.getCapacity())
                .collect(Collectors.toList());
        if (car != null && parkingLotByCarExist.size() == 0 && parkingLotByParkCar.size() > 0) {
            return parkingLotByParkCar.get(0).park(car);
        } else {
            throw new NotPositionEnoughException("Not enough position.");
        }
    }

    public Car fetch(Ticket ticket) throws Exception{
        List<ParkingLot> parkingLotByCar = parkingLots.stream()
                .filter(x -> x.getParkingCarTicketContainsKey(ticket))
                .collect(Collectors.toList());
        if (ticket == null) {
            throw new NoTicketProvideException("Please provide your parking ticket.");
        } else if (ticket.isTicketWrong() || ticket.isTicketUsed()){
            throw new UnrecognizedTicketException("Unrecognized parking ticket.");
        } else {
            return parkingLotByCar.get(0).getCar(ticket);
        }
    }
}
