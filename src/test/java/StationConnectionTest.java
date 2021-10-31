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
        Integer lines[] = {359, 94};
        int station = 3;

        //when
        List<Integer> stationConnections = new StationConnection().getConnections(station);

         //then
        assertEquals(lines[0], stationConnections.get(0));
        assertEquals(lines[1], stationConnections.get(1));
    }
}