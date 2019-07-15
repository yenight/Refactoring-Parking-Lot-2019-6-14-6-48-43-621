package com.thoughtworks.tdd;

import com.thoughtworks.tdd.Exception.NotPositionEnoughException;
import com.thoughtworks.tdd.Exception.UnrecognizedTicketException;
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
        ParkingLot parkingThirdLot = new ParkingLot();

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingSecondLot);
        parkingLots.add(parkingThirdLot);

        SuperSmartParkingBoy parkingBoy = new SuperSmartParkingBoy(parkingLots);

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
    public void should_not_park_car_when_parking_lots_are_have_fulled() throws NotPositionEnoughException {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot(20);

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);

        SuperSmartParkingBoy parkingBoy = new SuperSmartParkingBoy(parkingLots);

        //when
        parkingLot.setParkedQuantity(20);
        Throwable exception = assertThrows(NotPositionEnoughException.class, () -> parkingBoy.park(car));

        //then
        assertEquals("Not enough position.", exception.getMessage());
    }

    @Test
    public void should_not_fetch_cars_and_get_a_message_when_ticket_is_wrong_and_have_many_parking_lots() throws Exception {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot(20);
        ParkingLot parkingSecondLot = new ParkingLot(15);
        ParkingLot parkingThirdLot = new ParkingLot();

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingSecondLot);
        parkingLots.add(parkingThirdLot);

        SuperSmartParkingBoy parkingBoy = new SuperSmartParkingBoy(parkingLots);

        Ticket wrongTicket = new Ticket();

        //when
        parkingLot.setParkedQuantity(7);
        parkingSecondLot.setParkedQuantity(5);
        parkingThirdLot.setParkedQuantity(10);
        wrongTicket.setTicketWrong(true);
        parkingBoy.park(car);

        //then
        Throwable exception = assertThrows(UnrecognizedTicketException.class, () -> {
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
        ParkingLot parkingThirdLot = new ParkingLot();

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingSecondLot);
        parkingLots.add(parkingThirdLot);

        SuperSmartParkingBoy parkingBoy = new SuperSmartParkingBoy(parkingLots);

        Ticket wrongTicket = new Ticket();

        //when
        parkingLot.setParkedQuantity(7);
        parkingSecondLot.setParkedQuantity(5);
        parkingThirdLot.setParkedQuantity(10);
        wrongTicket.setTicketUsed(true);
        parkingBoy.park(car);

        //then
        Throwable exception = assertThrows(UnrecognizedTicketException.class, () -> {
            parkingBoy.fetch(wrongTicket);
        });
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }
}
