package org.epam.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.epam.parking.ParkingSpace;

/**
 * Servlet implementation class ExitParkingSpace
 */
@WebServlet("/ExitParkingSpace")
public class ExitParkingSpace extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ParkingSpace parkingSpace = (ParkingSpace) req.getSession().getAttribute("parkingSpace");
		try (BufferedWriter bw = new BufferedWriter (
                new FileWriter("/Users/rajendra/eclipse-workspace/AutoParkingWebApplication/src/org/epam/fileio/Transaction.txt"))) {
        for (Object line : parkingSpace.queue) {
            bw.write(line + "\n");
        }
        bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

	}

}
