package org.epam.service;

import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.epam.exceptions.CarNotPresentException;
import org.epam.exceptions.CarNumberInvalidException;
import org.epam.fileio.FileOperation;
import org.epam.parking.ParkingSpace;
import org.epam.parking.Slot;

public class ParkingOperaions extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		ParkingSpace parkingSpace = (ParkingSpace) req.getSession().getAttribute("parkingSpace");
		
	}
	
	
	/**
	 * slot to be removed.
	 */
	public Slot slotToRemove;
	/**
     * if car is valid and not present in parking space,
     * then it is placed into slot.
     * @param carNumber to be parked
     * @param parkingSpace where car is parked
     * @return 
     */
    public boolean parkCar(String carNumber, ParkingSpace parkingSpace) {
    	boolean isParked = false;
        try {
        if (isCarNumberValid(carNumber) &&
                isCarNotPresent(carNumber, parkingSpace)) {
        if (!isEmptySlotsAvailable(parkingSpace)) {
            int emptySlot = parkingSpace.nextSlot.poll();
            Slot slot = new Slot(emptySlot, carNumber, new Date().getTime());
            parkingSpace.queue.add(slot);
            isParked = true;
        } else {
            Slot slot = new Slot(parkingSpace.getNextSlotNumber(),
                    carNumber, new Date().getTime());
            parkingSpace.queue.add(slot);
            parkingSpace.setSlotNumber(parkingSpace.getNextSlotNumber() + 1);
            isParked = true;
        }
        parkingSpace.updateSlotsRemaining(parkingSpace.getSlotsRemaining() - 1);
        System.out.println("Parked successfully");
        }
        } catch (CarNumberInvalidException message) {
            message.printStackTrace();
        }
        return isParked;
    }
    /**
	 * @param carNumber to be unparked.
	 * @param parkingSpace of car
	 * @return 
     * @throws CarNotPresentException 
	 */
	public boolean unParkCar(String carNumber, ParkingSpace parkingSpace) throws CarNotPresentException {
		boolean isUnparked = false;
		FileOperation addToLog = new FileOperation();
		try {
		if (isCarNumberValid(carNumber)
				&& isCarPresent(carNumber, parkingSpace)) {
			parkingSpace.queue.remove(slotToRemove);
			parkingSpace.nextSlot.add(slotToRemove.getSlotNumber());
			parkingSpace.updateSlotsRemaining(parkingSpace.getSlotsRemaining() - 1);
			long diff = new Date().getTime() - Slot.intime[slotToRemove.getSlotNumber()].getInTime();
			System.out.println("Unparked successfully! Car parked for duration of:" + duration(diff));
			addToLog.writeToLogFile(slotToRemove);
			isUnparked = true;
		}
		} catch (CarNumberInvalidException message) {
			message.printStackTrace();
		}
		return isUnparked;
	}
	/**
     * checks for empty slots.
     * @param parkingSpace where car is parked.
     * @return true if empty slots are available
     */
    private boolean isEmptySlotsAvailable(ParkingSpace parkingSpace) {
        return parkingSpace.nextSlot.isEmpty();
    }
    /**
     * validates the car numnber.
     * @param carNumber to be parked
     * @return true if car number is valid else false
     * @throws CarNumberInvalidException occurs when carnumber is invalid.
     */
    private boolean isCarNumberValid(String carNumber)
            throws CarNumberInvalidException {
        int len = carNumber.length();
        boolean isValid = false;
        if (len == 10) {
            String regex = "^[a-zA-z]{2}[0-9]{2}[a-zA-z]{2}[0-9]{4}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(carNumber);
            isValid =  matcher.matches();
        } else {
            throw new CarNumberInvalidException("Invalid car number!");
        }
        return isValid;
    }
    /**
     * checks for presence of car.
     * @param carNumber to be parked
     * @return false if car is present in parking spacce.
     */
    private boolean isCarNotPresent(String carNumber, ParkingSpace parkingSpace) {
        boolean isNotPresent = true;
        Iterator<Slot> itr = parkingSpace.queue.iterator();
        while (itr.hasNext()) {
            Slot nextslot = itr.next();
            if (Slot.car[nextslot.getSlotNumber()].equals(carNumber)) {
                isNotPresent = false;
            }
        }
        return isNotPresent;
    }
    /**
	 *
	 * @param carNumber to be checked
	 * @param parkingSpace of car
	 * @return isPresent
	 */
	public boolean isCarPresent(String carNumber, ParkingSpace parkingSpace)
			throws CarNotPresentException {
		boolean isPresent = false;
		Iterator<Slot> itr = parkingSpace.queue.iterator();
		while (itr.hasNext()) {
			Slot nextslot = itr.next();
			if (Slot.car[nextslot.getSlotNumber()].getCarNumber().equals(carNumber)) {
				isPresent = true;
				slotToRemove = nextslot;
			}
		}
		if (isPresent) {
		} else {
			throw new CarNotPresentException("Can't find car in parking space");
		}
		return isPresent;
	}

    /**
	 * @param diff holds the parking time.
	 */
	private String duration(long diff) {
		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
	    long diffHours = diff / (60 * 60 * 1000) % 24;
	    long diffDays = diff / (24 * 60 * 60 * 1000);
	    System.out.print(diffDays + " days, ");
	    System.out.print(diffHours + " hours, ");
	    System.out.print(diffMinutes + " minutes, ");
	    System.out.print(diffSeconds + " seconds.");
	    return "Thankyou For Using Parking Service";
	}
}
