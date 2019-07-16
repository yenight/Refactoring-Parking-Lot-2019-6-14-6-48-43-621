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
        List<ParkingLot> parkingLotByCarExist = getParkingLotByCarExist(car);
        List<ParkingLot> parkingLotByParkCar = getParkingLotByParkCar();
        return doesCarPark(car, parkingLotByCarExist, parkingLotByParkCar);
    }

    public Car fetch(Ticket ticket) throws Exception{
        List<ParkingLot> parkingLotByCar = getParkingLotByFetchCar(ticket);
        return doesCarFetch(ticket, parkingLotByCar);
    }

    private List<ParkingLot> getParkingLotByParkCar() {
        return parkingLots.stream()
                .filter(x -> x.getParkedQuantity() < x.getCapacity())
                .collect(Collectors.toList());
    }

    public List<ParkingLot> getParkingLotByCarExist(Car car) {
        return parkingLots.stream()
                .filter(x -> x.getParkingCarTicketContainsValue(car))
                .collect(Collectors.toList());
    }

    public boolean doseCarParkInParkingLot(Car car, List<ParkingLot> parkingLotByCarExist, List<ParkingLot> parkingLotByParkCar) {
        return car != null && parkingLotByCarExist.size() == 0 && parkingLotByParkCar.size() > 0;
    }


    public Ticket doesCarPark(Car car, List<ParkingLot> parkingLotByCarExist, List<ParkingLot> parkingLotByParkCar) throws NotPositionEnoughException {
        if (doseCarParkInParkingLot(car, parkingLotByCarExist, parkingLotByParkCar)) {
            return parkingLotByParkCar.get(0).park(car);
        } else {
            throw new NotPositionEnoughException("Not enough position.");
        }
    }

    private Car doesCarFetch(Ticket ticket, List<ParkingLot> parkingLotByCar) throws NoTicketProvideException, UnrecognizedTicketException {
        if (ticket == null) {
            throw new NoTicketProvideException("Please provide your parking ticket.");
        } else if (isTicketHasWrong(ticket)){
            throw new UnrecognizedTicketException("Unrecognized parking ticket.");
        } else {
            return parkingLotByCar.get(0).getCar(ticket);
        }
    }

    private List<ParkingLot> getParkingLotByFetchCar(Ticket ticket) {
        return parkingLots.stream()
                .filter(x -> x.getParkingCarTicketContainsKey(ticket))
                .collect(Collectors.toList());
    }

    private boolean isTicketHasWrong(Ticket ticket) {
        return ticket.isTicketWrong() || ticket.isTicketUsed();
    }
}
