package com.thoughtworks.tdd;

import java.util.List;
import java.util.stream.Collectors;

public class SuperSmartParkingBoy extends ParkingBoy{

    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) {
        List<ParkingLot> parkingLotByCarExist = this.getParkingLots().stream()
                .filter(x -> x.getParkingCarTicket().containsValue(car))
                .collect(Collectors.toList());
        double minQuantity = this.getParkingLots().stream()
                .mapToDouble(x-> x.getParkedQuantity() * 1.0/x.getCapacity())
                .min().orElse(-1);
        List<ParkingLot> parkingLotByParkCar = this.getParkingLots().stream()
                .filter(x -> x.getParkedQuantity() < x.getCapacity() && x.getParkedQuantity() * 1.0/x.getCapacity() == minQuantity)
                .collect(Collectors.toList());
        if (car != null && parkingLotByCarExist.size() == 0 && parkingLotByParkCar.size() > 0) {
            return parkingLotByParkCar.get(0).park(car);
        } else {
            return null;
        }
    }
}
