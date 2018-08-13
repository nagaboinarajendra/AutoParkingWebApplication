package org.epam.fileio;
/**
 * @author rajendra
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.epam.parking.ParkingSpace;
import org.epam.parking.Slot;
/**
 * read and write operations on file.
 */
public class FileOperation {
    /**
     * write the car info into file.
     * @param queue
     */
    public void writeToFile(ArrayList<Slot> queue) {
        try (BufferedWriter bw = new BufferedWriter (
                new FileWriter("/Users/rajendra/eclipse-workspace/AutoParkingWebApplication/src/org/epam/fileio/Transaction.txt"))) {
        for (Object line : queue) {
            bw.write(line + "\n");
        }
        bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Succesfully saves to transactions");
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
        BufferedReader br = new BufferedReader(new FileReader("/Users/rajendra/eclipse-workspace/AutoParkingWebApplication/src/org/epam/fileio/Transaction.txt"));
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
    /**
     * writes the records into log.
     * @param slot object to be placed in logs
     */
    public void writeToLogFile(Slot slot) {
    	try (BufferedWriter bw = new BufferedWriter(
                new FileWriter("/Users/rajendra/eclipse-workspace/AutoParkingWebApplication/src/org/epam/fileio/LogFile.txt", true))) {
        Object line = slot;
        bw.write(line + "\n");
        bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
