package org.epam.fileio;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.epam.parking.ParkingSpace;
import org.epam.parking.Slot;

/**
 * Servlet implementation class WriteOperations
 */
@WebServlet("/WriteOperations")
public class WriteOperations extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ParkingSpace parkingSpace = (ParkingSpace)req.getSession().getAttribute("parkingSpace");
		writeToFile(parkingSpace.queue);
	}
	
	/**
     * write the car info into file.
     * @param queue
     */
    public void writeToFile(ArrayList<Slot> queue) {
        try (BufferedWriter bw = new BufferedWriter (
                new FileWriter("Transaction.txt"))) {
        for (Object line : queue) {
            bw.write(line + "\n");
        }
        bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Succesfully saves to transactions");
    }

}
