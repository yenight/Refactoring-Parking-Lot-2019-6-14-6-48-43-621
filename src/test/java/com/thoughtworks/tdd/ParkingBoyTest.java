package com.thoughtworks.tdd;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingBoyTest {

    //story1
    @Test
    public void should_return_car_when_park_car_to_parking_lot_then_get_it_back() throws Exception {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        Ticket ticket = parkingBoy.park(car);
        Car fetchCar = parkingBoy.fetch(ticket);

        //then
        assertSame(car, fetchCar);
    }

    @Test
    public void should_return_cars_when_park_cars_to_parking_lot_then_get_them_back() throws Exception {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        Ticket ticket = parkingBoy.park(car);
        Car fetchCar = parkingBoy.fetch(ticket);

        //then
        assertSame(car, fetchCar);
    }

    @Test
    public void should_not_fetch_cars_when_ticket_is_wrong() throws Exception{
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        Ticket wrongTicket = new Ticket();

        //when
        wrongTicket.setWrong(true);
        parkingBoy.park(car);

        //then
        assertThrows(Exception.class, () -> parkingBoy.fetch(wrongTicket));
    }

    @Test
    public void should_not_fetch_cars_when_ticket_is_used() throws Exception {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        Ticket hadUsedTicket = new Ticket();

        //when
        hadUsedTicket.setUsed(true);
        parkingBoy.park(car);

        //then
        assertThrows(Exception.class, () -> parkingBoy.fetch(hadUsedTicket));
    }

    @Test
    public void should_not_get_ticket_when_no_position_park_car() throws Exception{
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        parkingLot.setParkedQuantity(10);

        //then
        assertThrows(Exception.class, () -> parkingBoy.park(car));
    }

    @Test
    public void should_not_get_ticket_when_park_null_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        parkingLot.setParkedQuantity(10);

        //then
        assertThrows(Exception.class, () -> parkingBoy.park(null));
    }

    @Test
    public void should_not_get_ticket_when_park_a_parking_lot_car() throws Exception {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        parkingLot.setParkedQuantity(9);
        parkingBoy.park(car);

        //then
        assertThrows(Exception.class, () -> parkingBoy.park(car));
    }

    //story2
    @Test
    public void should_not_fetch_cars_and_get_a_message_when_ticket_is_wrong() throws Exception {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        Ticket wrongTicket = new Ticket();

        //when
        wrongTicket.setWrong(true);
        parkingBoy.park(car);

        //then
        Throwable exception = assertThrows(Exception.class, () -> parkingBoy.fetch(wrongTicket));
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_not_fetch_cars_and_get_a_message_when_ticket_is_used() throws Exception {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        Ticket wrongTicket = new Ticket();

        //when
        wrongTicket.setUsed(true);
        parkingBoy.park(car);

        //then
        Throwable exception = assertThrows(Exception.class, () -> parkingBoy.fetch(wrongTicket));
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_not_fetch_cars_and_get_a_message_when_not_provide_ticket() throws Exception {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        parkingBoy.park(car);

        //then
        Throwable secondException = assertThrows(Exception.class, () -> parkingBoy.fetch(null));
        assertEquals("Please provide your parking ticket.", secondException.getMessage());
    }

    @Test
    public void should_not_park_cars_and_get_a_message_when_parking_lot_is_fulled() throws Exception {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        parkingLot.setParkedQuantity(10);

        //then
        Throwable exception = assertThrows(Exception.class, () -> parkingBoy.park(car));
        assertEquals("Not enough position.", exception.getMessage());
    }

    //story3
    @Test
    public void should_park_cars_to_other_park_when_this_parking_lot_is_fulled() throws Exception {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot();
        ParkingLot parkingSecondLot = new ParkingLot();

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingSecondLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        parkingLot.setParkedQuantity(10);
        Ticket ticket = parkingBoy.park(car);
        Car fetchCar = parkingBoy.fetch(ticket);

        //then
        assertSame(car, fetchCar);
    }

    @Test
    public void should_not_park_car_when_all_parking_lot_are_fulled() throws Exception {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot();
        ParkingLot parkingSecondLot = new ParkingLot();

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingSecondLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        parkingLot.setParkedQuantity(10);
        parkingSecondLot.setParkedQuantity(10);

        //then
        Throwable exception = assertThrows(Exception.class, () -> parkingBoy.park(car));
        assertEquals("Not enough position.", exception.getMessage());
    }
}
