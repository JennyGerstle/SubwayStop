import org.junit.Test;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StationConnectionTest
{

    @Test
    public void getConnections() throws IOException
    {
        //given
        String lines[] = {"418", "464", "97", "167", "179"};
        String station = "50th St";

        //when
        List<String> stationConnections = new StationConnection().getConnections(station);

         //then
        assertEquals(lines[0], stationConnections.get(0));
        assertEquals(lines[1], stationConnections.get(4));
        assertEquals(lines[2], stationConnections.get(6));
        assertEquals(lines[3], stationConnections.get(23));
        assertEquals(lines[4], stationConnections.get(30));
    }
}