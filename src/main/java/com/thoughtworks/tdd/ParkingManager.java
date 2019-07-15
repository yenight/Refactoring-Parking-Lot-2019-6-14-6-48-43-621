package com.thoughtworks.tdd;

import java.util.List;

public class ParkingManager extends ParkingBoy{
    private List<ParkingBoy> parkingBoyList;

    public ParkingManager(List<ParkingLot> parkingLots, List<ParkingBoy> parkingBoyList) {
        super(parkingLots);
        this.parkingBoyList = parkingBoyList;
    }

    public List<ParkingBoy> getParkingBoyList() {
        return parkingBoyList;
    }

    public Ticket callParkingBoyParkCar(ParkingBoy parkingBoy, Car car) throws Exception {
        return parkingBoy.park(car);
    }

    public Car callParkingBoyFetchCar(ParkingBoy parkingBoy, Ticket ticket) throws Exception{
        return parkingBoy.fetch(ticket);
    }


}
