package org.epam.parking;
/**
 * class to hold the intime of the vehicle.
 * @author rajendra
 */
public class InTime {
    /**
     * intime of vehicle.
     */
    private long inTime;
    /**
     * @param inTime set the time
     */
    public InTime(long inTime) {
        this.inTime = inTime;
    }
    /**
     * @return the inTime
     */
    public long getInTime() {
        return inTime;
    }
    /**
     * @param inTime the inTime to set
     */
    public void setInTime(long inTime) {
        this.inTime = inTime;
    }
}