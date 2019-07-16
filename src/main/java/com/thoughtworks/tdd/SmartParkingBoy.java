package com.thoughtworks.tdd;

import com.thoughtworks.tdd.Exception.NotPositionEnoughException;

import java.util.List;
import java.util.stream.Collectors;

public class SmartParkingBoy extends ParkingBoy{

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) throws NotPositionEnoughException {
        List<ParkingLot> parkingLotByCarExist = this.getParkingLotByCarExist(car);
        int minQuantity = getMinQuantity();
        List<ParkingLot> parkingLotByParkCar = getParkLotByParkCar(minQuantity);
        return this.doesCarPark(car,parkingLotByCarExist, parkingLotByParkCar);
    }

    private List<ParkingLot> getParkLotByParkCar(int minQuantity) {
        return this.getParkingLots().stream()
                .filter(x -> x.getParkedQuantity() < x.getCapacity() && x.getParkedQuantity() == minQuantity)
                .collect(Collectors.toList());
    }

    private int getMinQuantity() {
        return this.getParkingLots().stream()
                .filter(x -> x.getParkedQuantity() < x.getCapacity())
                .mapToInt(ParkingLot::getParkedQuantity)
                .min().orElse(-1);
    }
}
