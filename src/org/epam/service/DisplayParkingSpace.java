package org.epam.service;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.epam.parking.ParkingSpace;
import org.epam.parking.Slot;

/**
 * Servlet implementation class DisplayParkingSpace
 */
@WebServlet("/DisplayParkingSpace")
public class DisplayParkingSpace extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ParkingSpace parkingSpace = (ParkingSpace) req.getSession().getAttribute("parkingSpace");
        Iterator<Slot> itr = parkingSpace.queue.iterator();
        while (itr.hasNext()) {
            Slot slot = itr.next();
            System.out.println(slot.toString());
        }
	}

}
