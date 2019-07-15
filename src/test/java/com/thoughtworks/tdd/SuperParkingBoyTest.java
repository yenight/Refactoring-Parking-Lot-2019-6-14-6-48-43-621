package com.thoughtworks.tdd;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SuperParkingBoyTest {
    @Test
    public void should_park_car_in_most_quantity_parking_lot_when_parking_lots_are_have_different_capacity() throws Exception {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot(20);
        ParkingLot parkingSecondLot = new ParkingLot(15);
        ParkingLot parkingThridLot = new ParkingLot();

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingSecondLot);
        parkingLots.add(parkingThridLot);

        SuperSmartParkingBoy parkingBoy = new SuperSmartParkingBoy(parkingLots);

        //when
        parkingLot.setParkedQuantity(7);
        parkingSecondLot.setParkedQuantity(5);
        parkingThridLot.setParkedQuantity(10);

        Ticket ticket = parkingBoy.park(car);
        Car fetchCar = parkingBoy.fetch(ticket);

        //then
        assertSame(car, fetchCar);
    }

    @Test
    public void should_not_fetch_cars_and_get_a_message_when_ticket_is_wrong_and_have_many_parking_lots() throws Exception {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot(20);
        ParkingLot parkingSecondLot = new ParkingLot(15);
        ParkingLot parkingThridLot = new ParkingLot();

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingSecondLot);
        parkingLots.add(parkingThridLot);

        SuperSmartParkingBoy parkingBoy = new SuperSmartParkingBoy(parkingLots);

        Ticket wrongTicket = new Ticket();

        //when
        parkingLot.setParkedQuantity(7);
        parkingSecondLot.setParkedQuantity(5);
        parkingThridLot.setParkedQuantity(10);
        wrongTicket.setWrong(true);
        parkingBoy.park(car);

        //then
        Throwable exception = assertThrows(Exception.class, () -> {
            parkingBoy.fetch(wrongTicket);
        });
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_not_fetch_cars_and_get_a_message_when_ticket_is_used_and_have_many_parking_lots() throws Exception {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot(20);
        ParkingLot parkingSecondLot = new ParkingLot(15);
        ParkingLot parkingThridLot = new ParkingLot();

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingSecondLot);
        parkingLots.add(parkingThridLot);

        SuperSmartParkingBoy parkingBoy = new SuperSmartParkingBoy(parkingLots);

        Ticket wrongTicket = new Ticket();

        //when
        parkingLot.setParkedQuantity(7);
        parkingSecondLot.setParkedQuantity(5);
        parkingThridLot.setParkedQuantity(10);
        wrongTicket.setUsed(true);
        parkingBoy.park(car);

        //then
        Throwable exception = assertThrows(Exception.class, () -> {
            parkingBoy.fetch(wrongTicket);
        });
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }
}
