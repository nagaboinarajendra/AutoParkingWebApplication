package org.epam.service;
import java.io.IOException;
/**
 * @author rajendra
 */
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.epam.exceptions.CarNumberInvalidException;
import org.epam.parking.ParkingSpace;
import org.epam.parking.Slot;
/**
 * parks the vehicle into parking space.
 */
@WebServlet("/park")
public class ParkVehicle extends HttpServlet{
    
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String carNumber = req.getParameter("carnumber");
		ParkingSpace parkingSpace = (ParkingSpace) req.getSession().getAttribute("parkingSpace");
		parkCar(carNumber,parkingSpace);
		req.getRequestDispatcher("Menu.html").forward(req, res);
	}
	
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
        System.out.println(parkingSpace.getNextSlotNumber());
        }
        } catch (CarNumberInvalidException message) {
            message.printStackTrace();
        }
        return isParked;
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
}
