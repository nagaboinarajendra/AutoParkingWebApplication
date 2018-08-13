package org.epam.vehicle;

/**
 * Car class to hold car objects.
 * @author rajendra
 */
public class Car implements Vehicle {
    /**
     * car number to be parked in parkingSpace.
     */
    private String carNumber;
    /**
     * @param car to be parked
     */
    public Car(String car) {
        this.carNumber = car;
    }
    /**
     * @return the carNumber
     */
    public String getCarNumber() {
        return carNumber;
    }
}
