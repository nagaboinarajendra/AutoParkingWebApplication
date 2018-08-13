package org.epam.fileio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.epam.parking.ParkingSpace;
import org.epam.parking.Slot;

/**
 * Servlet implementation class ReadOperations
 */
@WebServlet("/ReadOperations")
public class ReadOperations extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ParkingSpace parkingSpace = (ParkingSpace)req.getSession().getAttribute("parkingSpace");
		ReadFromFile(parkingSpace);
	}
	/**
     * read information from file.
     * @param parkingSpace of parking
     * @return 
     * @throws IOException is occured when file not found
     */
    public String ReadFromFile(ParkingSpace parkingSpace) throws IOException {
        String line = "";
        //File file = new File("Transaction.txt");
        //file.createNewFile();
        System.out.println("hmmmm");
        BufferedReader br = new BufferedReader(new FileReader("Transaction.txt"));
        int lastSlot = 0;
        int[] temp = new int[parkingSpace.getTotalSlots()];
        while ((line = br.readLine()) != null) {
            String cvsSplitBy = " ";
            String[] record = line.split(cvsSplitBy);
            parkingSpace.queue.add(new Slot(Integer.parseInt(record[0]),
                    record[1], Long.valueOf(record[2])));
            parkingSpace.setSlotNumber(parkingSpace.getNextSlotNumber() + 1);
            parkingSpace.updateSlotsRemaining(parkingSpace.getSlotsRemaining() - 1);
            temp[Integer.parseInt(record[0])] = Integer.parseInt(record[0]);
            lastSlot  = Integer.parseInt(record[0]);
        }
        if ((line = br.readLine()) == null) {
            updateQueue(lastSlot, temp, parkingSpace);
            parkingSpace.setSlotNumber(lastSlot + 1);
        }
        br.close();
        return "read sucessfully";  
    }
    /**
     * updates the queue with empty slots.
     * @param lastSlot
     * @param temp
     * @param parkingSpace
     */
    public void updateQueue(int lastSlot, int temp[], ParkingSpace parkingSpace) {
        for (int slot = 1; slot <= lastSlot; slot++) {
            if (temp[slot] == 0) {
                parkingSpace.nextSlot.add((slot));
            }
        }
    }
}
