import com.google.gson.Gson;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CloseCoordinateStationsTest
{
    @Test
    public void getClosestStation() throws IOException
    {
        //given
        List<Double> coordinates = new ArrayList<>();
        coordinates.add(-73.9723553085244);
        coordinates.add(40.603258405128265);
        Gson gson = new Gson();
        InputStream in = ClassLoader.getSystemResourceAsStream("Subwaystations.json");
        InputStreamReader reader = new InputStreamReader(in);

        //when
        StationInfo stationInfo = gson.fromJson(reader, StationInfo.class);
        reader.close();
        StationInfo.Station closestStations = new CloseCoordinateStations().getClosestStation(coordinates, stationInfo);

        //then
        assertEquals(280, closestStations.properties.objectid);
    }

}