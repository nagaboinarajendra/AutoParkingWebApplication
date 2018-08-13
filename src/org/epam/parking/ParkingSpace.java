package org.epam.parking;
import java.util.ArrayList;
/**
 * @author rajendra
 */
import java.util.Iterator;
import java.util.PriorityQueue;
/**
 * parking space for the vehicles.
 */
public class ParkingSpace {
    /**
     * slotnumber of vehicle.
     */
    private int slotNumber = 1;
    /**
     * total number of slots in parking spaces.
     */
    private int totalSlots;
    /**
     * total number of slots remaining.
     */
    private int slotsRemaining;
    /**
     * queue that holds the slot objects.
     */
    public ArrayList<Slot> queue = new ArrayList<Slot>();
    /**
     * queue to store empty slots.
     */
    public PriorityQueue<Integer> nextSlot = new PriorityQueue<Integer>();
    /**
     * @param totalSlots in parking space.
     */
    public ParkingSpace(final int totalSlots) {
        this.totalSlots = totalSlots;
        this.slotsRemaining = totalSlots;
    }
    /**
     * @return the slotNumber
     */
    public int getNextSlotNumber() {
        return this.slotNumber;
    }
    /**
     * @param slotNumber the slotNumber to set
     */
    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }
    /**
     * @return the totalSlots
     */
    public int getTotalSlots() {
        return this.totalSlots;
    }
    /**
     * Display vehicle details.
     * @param parkingSpace object
     */
    public void displayParkingSpace(ParkingSpace parkingSpace) {
        Iterator<Slot> itr = parkingSpace.queue.iterator();
        while (itr.hasNext()) {
            Slot slot = itr.next();
            System.out.println(slot.toString());
        }
    }
    /**
     * @return the slotsRemaining
     */
    public int getSlotsRemaining() {
        return slotsRemaining;
    }
    /**
     * @param slotsRemaining the slotsRemaining to set
     */
    public void updateSlotsRemaining(int slotsRemaining) {
        this.slotsRemaining = slotsRemaining;
    }
}
