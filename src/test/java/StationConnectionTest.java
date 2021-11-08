import com.google.gson.Gson;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class StationConnectionTest
{

    @Test
    public void getConnections() throws IOException
    {
        //given
        Gson gson = new Gson();
        InputStream in = ClassLoader.getSystemResourceAsStream("SubwayLines.json");
        InputStreamReader reader = new InputStreamReader(in);
        InputStream inStation = ClassLoader.getSystemResourceAsStream("Subwaystations.json");
        InputStreamReader readerStation = new InputStreamReader(inStation);
        //when
        LineInfo lineInfo = gson.fromJson(reader, LineInfo.class);
        StationInfo stationInfo = gson.fromJson(readerStation, StationInfo.class);
        readerStation.close();
        reader.close();
        List<Integer> stationConnections = new StationConnection().getConnections(stationInfo.features.get(1), lineInfo);

         //then
        assertEquals(Integer.valueOf(29), stationConnections.get(0));
        assertEquals(Integer.valueOf(467), stationConnections.get(1));
    }
}