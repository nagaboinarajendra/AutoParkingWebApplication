package org.epam.parking;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.epam.fileio.FileOperation;
import org.epam.vehicle.Car;
@WebServlet("/menu")
public class Menu extends HttpServlet{
	@Override
	 public void doGet(HttpServletRequest req, HttpServletResponse res) throws
	    IOException {
		PrintWriter out = res.getWriter();
		ParkingSpace parkingSpace = (ParkingSpace) req.getSession().getAttribute("parkingSpace");
		intitializeSlots(parkingSpace);
		
	}
	 /**
     * @param parkingSpace of parking area.
     * @throws IOException occurs when file not found.
     */
    private static void intitializeSlots(ParkingSpace parkingSpace) throws
    IOException {
        Slot.car = new Car[parkingSpace.getTotalSlots() + 1];
        Slot.intime = new InTime[parkingSpace.getTotalSlots() + 1];
        FileOperation fo = new FileOperation();
        fo.ReadFromFile(parkingSpace);
    }

}