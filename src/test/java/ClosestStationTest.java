import com.google.gson.Gson;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ClosestStationTest
{
    @Test
    public void getClosestStation() throws IOException
    {
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
        List<StationInfo.Station> closestPath = new ClosestStation().getClosestStation(stationInfo.features.get(1), stationInfo.features.get(3),stationInfo, lineInfo);

        //then
        assertEquals(2, closestPath.get(0).properties.objectid);
        assertEquals(29, closestPath.get(1).properties.objectid);
        assertEquals(427, closestPath.get(2).properties.objectid);
        assertEquals(420, closestPath.get(3).properties.objectid);
        assertEquals(419, closestPath.get(4).properties.objectid);
        assertEquals(123, closestPath.get(5).properties.objectid);
        assertEquals(128, closestPath.get(6).properties.objectid);
        assertEquals(116, closestPath.get(7).properties.objectid);
    }

}