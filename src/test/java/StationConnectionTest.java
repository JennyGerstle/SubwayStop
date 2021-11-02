import com.google.gson.Gson;
import org.junit.Test;
import java.io.IOException;
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
        Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/SubwayLines.json"));
        Reader readerStation = Files.newBufferedReader(Paths.get("src/main/resources/Subwaystations.json"));
        Integer lines[] = {359, 94};

        //when
        LineInfo lineInfo = gson.fromJson(reader, LineInfo.class);
        StationInfo stationInfo = gson.fromJson(readerStation, StationInfo.class);
        StationInfo station = new StationInfo();

        readerStation.close();
        reader.close();
        List<Integer> stationConnections = new StationConnection().getConnections(station, lineInfo);

         //then
        assertEquals(lines[0], stationConnections.get(0));
        assertEquals(lines[1], stationConnections.get(1));
    }
}