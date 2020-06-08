package Assignment.ParkingLot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {
	ParkingLot parkingLot = new ParkingLot();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }
    @Test
    public void createParkingLot() throws Exception {
        parkingLot.createParkingLot("6");
        assertEquals(6, parkingLot.MAX_SIZE);
        assertEquals(6, parkingLot.availableSlotList.size());
        assertTrue("createdparkinglotwith6slots".equalsIgnoreCase(outContent.toString().trim().replace(" ", "")));
    }

    @Test
    public void park() throws Exception {
        parkingLot.park("KA-01-HH-1234");
        parkingLot.park("KA-01-HH-9999");
        assertEquals("Sorry,parkinglotinotcreated \n" +
                "\n" +
                "Sorry,parkinglotinotcreated", outContent.toString().trim().replace(" ", ""));
        parkingLot.createParkingLot("6");
        parkingLot.park("KA-01-HH-1234");
        parkingLot.park("KA-01-HH-9999");
        assertEquals(4, parkingLot.availableSlotList.size());
    }

    @Test
    public void leave() throws Exception {
        parkingLot.leave("KA-01-HH-1234", "2");
        assertEquals("Sorry, parking lot is not created", outContent.toString().trim().replace(" ", ""));
        parkingLot.createParkingLot("6");
        parkingLot.park("KA-01-HH-1234");
        parkingLot.park("KA-01-HH-9999");
        parkingLot.leave("KA-01-HH-1234","4");
        assertEquals("Sorry, parking lot is not created \n" +
                "\n" +
                "Created parking lotwith6slots\n" +
                "\n" +
                "Allocatedslotnumber:1\n" +
                "\n" +
                "Allocatedslotnumber:2\n" +
                "\n" +
                "Slotnumber4isalreadyempty", outContent.toString().trim().replace(" ", ""));
    }
}



