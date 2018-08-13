package org.epam.parking;
import org.epam.vehicle.Car;
/**
 * Slot holds the vehicle time and number.
 * @author rajendra
 */
public class Slot implements Comparable<Slot>{
    /**
     * slot number where vehicle is stored.
     */
    private int slotNumber;
    /**2
     * array of cars.
     */
    public static Car[] car;
    /**
     * array of intimes.
     */
    public static InTime[] intime;
    /**
     * default constructor.
     */
    public Slot() { }
    /**
     * @param slot where vehicle is parked.
     * @param carNumber
     * @param inTime of vehicle
     */
    public Slot(int slot, String carNumber, long inTime) {
        this.slotNumber = slot;
        Car present = new Car(carNumber);
        car[slot] = present;
        InTime time = new InTime(inTime);
        intime[slot] = time;
    }
    /**
     * @return the slotNumber
     */
    public int getSlotNumber() {
        return slotNumber;
    }
    /**
     * @param slotNumber the slotNumber to set
     */
    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }
    /**
     * @return the string of vehicle details.
     */
    public String toString() {
        return this.getSlotNumber() + " " + Slot.car[this.getSlotNumber()].getCarNumber() + " "
                + Slot.intime[this.getSlotNumber()].getInTime();
    }
	@Override
	public int compareTo(Slot next) {
		
		return 0;
	}
}
