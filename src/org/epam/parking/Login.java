package org.epam.parking;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.epam.admin.Admin;
import org.epam.fileio.FileOperation;
import org.epam.vehicle.Car;
/**
 * provides facitities to user.
 * @author rajendra
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException { 
		boolean isAdminValid = false;
		Admin admin = new Admin();
		PrintWriter out = res.getWriter();
		try {
		String username = req.getParameter("uname");
		String password = req.getParameter("psw");
		isAdminValid = admin.validateAdmin(username ,password);
		} catch (Exception message) {
		    out.println(message);
		}
		if (isAdminValid) {
			req.getSession(true);
			ParkingSpace parkingSpace = new ParkingSpace(50);
			intitializeSlots(parkingSpace);
			req.getSession().setAttribute("parkingSpace", parkingSpace);
			req.getRequestDispatcher("Menu.html").forward(req, res);
		}
	}
	 /**
     * @param parkingSpace of parking area.
     * @throws IOException occurs when file not found.
     */
    private static void intitializeSlots(ParkingSpace parkingSpace) throws
    IOException {
        Slot.car = new Car[parkingSpace.getTotalSlots() + 1];
        System.out.println(parkingSpace.getTotalSlots());
        Slot.intime = new InTime[parkingSpace.getTotalSlots() + 1];
        FileOperation fo = new FileOperation();
        System.out.println(fo.ReadFromFile(parkingSpace));
    }
}
