package com.thoughtworks.tdd;

import com.thoughtworks.tdd.Exception.NotPositionEnoughException;

import java.util.List;
import java.util.stream.Collectors;

public class SuperSmartParkingBoy extends ParkingBoy{

    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) throws NotPositionEnoughException {
        List<ParkingLot> parkingLotByCarExist = this.getParkingLotByCarExist(car);
        double minQuantity = getMinQuantity();
        List<ParkingLot> parkingLotByParkCar = getParkingLotByParkCar(minQuantity);
        return this.doesCarPark(car,parkingLotByCarExist, parkingLotByParkCar);
    }

    private List<ParkingLot> getParkingLotByParkCar(double minQuantity) {
        return this.getParkingLots().stream()
                .filter(x -> x.getParkedQuantity() < x.getCapacity() && x.getParkedQuantity() * 1.0/x.getCapacity() == minQuantity)
                .collect(Collectors.toList());
    }

    private double getMinQuantity() {
        return this.getParkingLots().stream()
                .mapToDouble(x-> x.getParkedQuantity() * 1.0/x.getCapacity())
                .min().orElse(-1);
    }
}
