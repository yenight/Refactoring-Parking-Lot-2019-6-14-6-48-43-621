package com.thoughtworks.tdd;

import com.thoughtworks.tdd.Exception.NoTicketProvideException;
import com.thoughtworks.tdd.Exception.NotPositionEnoughException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SmartParkingBoyTest {
    @Test
    public void should_park_car_in_most_quantity_parking_lot_when_parking_lots_are_have_vacancy() throws Exception {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot();
        ParkingLot parkingSecondLot = new ParkingLot();
        ParkingLot parkingThirdLot = new ParkingLot();

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingSecondLot);
        parkingLots.add(parkingThirdLot);

        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);

        //when
        parkingLot.setParkedQuantity(7);
        parkingSecondLot.setParkedQuantity(5);
        parkingThirdLot.setParkedQuantity(10);

        Ticket ticket = parkingBoy.park(car);
        Car fetchCar = parkingBoy.fetch(ticket);

        //then
        assertSame(car, fetchCar);
    }

    @Test
    public void should_not_park_car_when_parking_lots_are_fulled() throws NotPositionEnoughException {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot();
        ParkingLot parkingSecondLot = new ParkingLot();
        ParkingLot parkingThridLot = new ParkingLot();

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingSecondLot);
        parkingLots.add(parkingThridLot);

        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);

        //when
        parkingLot.setParkedQuantity(10);
        parkingSecondLot.setParkedQuantity(10);
        parkingThridLot.setParkedQuantity(10);

        Throwable exception = assertThrows(NotPositionEnoughException.class, () -> parkingBoy.park(car));
        //then
        assertEquals("Not enough position.", exception.getMessage());
    }


    @Test
    public void should_not_park_car_when_all_parking_lots_are_have_vacancy_and_no_provide_ticket() throws Exception {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot();
        ParkingLot parkingSecondLot = new ParkingLot();
        ParkingLot parkingThirdLot = new ParkingLot();

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingSecondLot);
        parkingLots.add(parkingThirdLot);

        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);

        //when
        parkingLot.setParkedQuantity(7);
        parkingSecondLot.setParkedQuantity(5);
        parkingThirdLot.setParkedQuantity(10);
        parkingBoy.park(car);

        //then
        Throwable exception = assertThrows(NoTicketProvideException.class, () -> parkingBoy.fetch(null));
        assertEquals("Please provide your parking ticket.", exception.getMessage());
    }
}
