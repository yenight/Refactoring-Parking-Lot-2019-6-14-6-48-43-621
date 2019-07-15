package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
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
        Car firstCar = new Car();
        Car secondCar = new Car();

        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        Ticket firstTicket = parkingBoy.park(firstCar);
        Car fetchFirstCar = parkingBoy.fetch(firstTicket);

        Ticket secondTicket = parkingBoy.park(secondCar);
        Car fetchSecondCar = parkingBoy.fetch(secondTicket);
        //then
        assertSame(firstCar, fetchFirstCar);
        assertSame(secondCar, fetchSecondCar);
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
        wrongTicket.setWrong(true);
        //when
        parkingBoy.park(car);

        //then
        assertThrows(Exception.class, () -> {
            parkingBoy.fetch(wrongTicket);
        });
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
        hadUsedTicket.setUsed(true);
        //when
        parkingBoy.park(car);

        //then
        assertThrows(Exception.class, () -> {
            parkingBoy.fetch(hadUsedTicket);
        });
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
        assertThrows(Exception.class, () -> {
            parkingBoy.park(car);
        });
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
        assertThrows(Exception.class, () -> {
            parkingBoy.park(null);
        });
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
        assertThrows(Exception.class, () -> {
            parkingBoy.park(car);
        });
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
        wrongTicket.setUsed(true);

        Ticket wrongSecondTicket = new Ticket();
        wrongSecondTicket.setWrong(true);

        //when
        parkingBoy.park(car);
        //then

        Throwable exception = assertThrows(Exception.class, () -> {
            parkingBoy.fetch(wrongTicket);
        });
        assertEquals("Unrecognized parking ticket.", exception.getMessage());

        Throwable secondException = assertThrows(Exception.class, () -> {
            parkingBoy.fetch(wrongSecondTicket);
        });
        assertEquals("Unrecognized parking ticket.", secondException.getMessage());
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
        Throwable secondException = assertThrows(Exception.class, () -> {
            parkingBoy.fetch(null);
        });
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
        Throwable exception = assertThrows(Exception.class, () -> {
            parkingBoy.park(car);
        });
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
        Throwable exception = assertThrows(Exception.class, () -> {
            parkingBoy.park(car);
        });
        assertEquals("Not enough position.", exception.getMessage());
    }

    //story4
    @Test
    public void should_park_car_in_most_quantity_parking_lot_when_parking_lots_are_have_vacancy() throws Exception {
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
        parkingLot.setParkedQuantity(7);
        parkingSecondLot.setParkedQuantity(5);
        parkingThridLot.setParkedQuantity(10);

        Ticket ticket = parkingBoy.park(car);
        Car fetchCar = parkingBoy.fetch(ticket);

        //then
        assertSame(car, fetchCar);
    }

    @Test
    public void should_not_park_car_when_all_parking_lots_are_have_vacancy_and_no_provide_ticket() throws Exception {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot();
        ParkingLot parkingSecondLot = new ParkingLot();
        ParkingLot parkingThridLot = new ParkingLot();
        parkingLot.setParkedQuantity(7);
        parkingSecondLot.setParkedQuantity(5);
        parkingThridLot.setParkedQuantity(10);

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingSecondLot);
        parkingLots.add(parkingThridLot);

        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);

        //when
        parkingBoy.park(car);

        //then
        Throwable exception = assertThrows(Exception.class, () -> {
            parkingBoy.fetch(null);
        });
        assertEquals("Please provide your parking ticket.", exception.getMessage());
    }

    //story5
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
        parkingLot.setParkedQuantity(7);
        parkingSecondLot.setParkedQuantity(5);
        parkingThridLot.setParkedQuantity(10);

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingSecondLot);
        parkingLots.add(parkingThridLot);

        SuperSmartParkingBoy parkingBoy = new SuperSmartParkingBoy(parkingLots);

        Ticket wrongTicket = new Ticket();
        wrongTicket.setUsed(true);
        Ticket wrongSecondTicket = new Ticket();
        wrongSecondTicket.setWrong(true);

        //when
        parkingBoy.park(car);

        //then
        Throwable exception = assertThrows(Exception.class, () -> {
            parkingBoy.fetch(wrongTicket);
        });
        assertEquals("Unrecognized parking ticket.", exception.getMessage());

        Throwable secondException = assertThrows(Exception.class, () -> {
            parkingBoy.fetch(wrongSecondTicket);
        });
        assertEquals("Unrecognized parking ticket.", secondException.getMessage());
    }

    //story6
    @Test
    public void should_fetch_car_when_manager_call_parking_boy_parks_car() throws Exception {
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
        Ticket ticket = manager.park(car);
        Car fetchCar = manager.fetch(ticket);
        //then
        assertSame(car, fetchCar);
    }

    @Test
    public void should_not_fetch_car_and_get_message_when_manager_call_parking_boy_parks_car() throws Exception {
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

        //then
        Throwable exception = assertThrows(Exception.class, () -> {
            manager.callParkingBoyParkCar(parkingBoy, null);
        });
        assertEquals("Not enough position.", exception.getMessage());

        Throwable secondException = assertThrows(Exception.class, () -> {
            manager.callParkingBoyFetchCar(parkingBoy, null);
        });
        assertEquals("Please provide your parking ticket.", secondException.getMessage());
    }
}
