package com.thoughtworks.tdd;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingManagerTest {
    @Test
    public void should_fetch_car_when_manager_call_parking_boy_parks_car() throws Exception {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot(20);
        ParkingLot parkingSecondLot = new ParkingLot(15);
        ParkingLot parkingThridLot = new ParkingLot();

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingSecondLot);

        List<ParkingLot> smartParkingLots = new ArrayList<>();
        smartParkingLots.add(parkingSecondLot);
        smartParkingLots.add(parkingThridLot);

        List<ParkingLot> superParkingLots = new ArrayList<>();
        superParkingLots.add(parkingLot);
        superParkingLots.add(parkingSecondLot);
        superParkingLots.add(parkingThridLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(smartParkingLots);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(superParkingLots);

        List<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(parkingBoy);
        parkingBoys.add(smartParkingBoy);
        parkingBoys.add(superSmartParkingBoy);

        ParkingManager manager = new ParkingManager(parkingLots, parkingBoys);

        //when
        parkingLot.setParkedQuantity(7);
        parkingSecondLot.setParkedQuantity(5);
        parkingThridLot.setParkedQuantity(10);

        Ticket ticket = manager.callParkingBoyParkCar(parkingBoy, car);
        Car fetchCar = manager.callParkingBoyFetchCar(parkingBoy, ticket);

        //then
        assertSame(car, fetchCar);
    }

    @Test
    public void should_fetch_car_when_manager_parks_car() throws Exception {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot(20);
        ParkingLot parkingSecondLot = new ParkingLot(15);
        ParkingLot parkingThridLot = new ParkingLot();
        parkingLot.setParkedQuantity(7);
        parkingSecondLot.setParkedQuantity(5);
        parkingThridLot.setParkedQuantity(10);

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingSecondLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        List<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(parkingBoy);

        ParkingManager manager = new ParkingManager(parkingLots, parkingBoys);

        //when
        parkingLot.setParkedQuantity(7);
        parkingSecondLot.setParkedQuantity(5);
        parkingThridLot.setParkedQuantity(10);

        Ticket ticket = manager.park(car);
        Car fetchCar = manager.fetch(ticket);

        //then
        assertSame(car, fetchCar);
    }

    @Test
    public void should_not_fetch_car_and_get_message_when_manager_call_parking_boy_parks_car_and_no_position_to_park_car() throws Exception {
        //given
        ParkingLot parkingLot = new ParkingLot(20);
        ParkingLot parkingSecondLot = new ParkingLot(15);
        ParkingLot parkingThridLot = new ParkingLot();

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingSecondLot);

        List<ParkingLot> superParkingLots = new ArrayList<>();
        superParkingLots.add(parkingLot);
        superParkingLots.add(parkingSecondLot);
        superParkingLots.add(parkingThridLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(superParkingLots);

        List<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(parkingBoy);
        parkingBoys.add(superSmartParkingBoy);

        ParkingManager manager = new ParkingManager(parkingLots, parkingBoys);

        //when
        parkingLot.setParkedQuantity(7);
        parkingSecondLot.setParkedQuantity(5);
        parkingThridLot.setParkedQuantity(10);

        //then
        Throwable exception = assertThrows(Exception.class, () -> {
            manager.callParkingBoyParkCar(parkingBoy, null);
        });
        assertEquals("Not enough position.", exception.getMessage());
    }

    @Test
    public void should_not_fetch_car_and_get_message_when_manager_call_parking_boy_parks_car_and_no_ticket_provide() throws Exception {
        //given
        ParkingLot parkingLot = new ParkingLot(20);
        ParkingLot parkingSecondLot = new ParkingLot(15);

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingSecondLot);

        List<ParkingLot> smartParkingLots = new ArrayList<>();
        smartParkingLots.add(parkingSecondLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(smartParkingLots);

        List<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(parkingBoy);
        parkingBoys.add(smartParkingBoy);

        ParkingManager manager = new ParkingManager(parkingLots, parkingBoys);

        //when
        parkingLot.setParkedQuantity(7);
        parkingSecondLot.setParkedQuantity(5);

        //then
        Throwable secondException = assertThrows(Exception.class, () -> {
            manager.callParkingBoyFetchCar(parkingBoy, null);
        });
        assertEquals("Please provide your parking ticket.", secondException.getMessage());
    }
}
