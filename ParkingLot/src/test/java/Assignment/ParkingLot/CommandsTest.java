package Assignment.ParkingLot;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CommandsTest {
	Commands commands = new Commands();
    @Test
    public void checkCommandInList() throws Exception {
        assertFalse(commands.commandsMap.isEmpty());
        assertTrue(commands.commandsMap.containsKey("create_parking_lot"));
        assertFalse(commands.commandsMap.containsKey("mytestcommand"));
    }
}
