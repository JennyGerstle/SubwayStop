import com.google.gson.Gson;
import org.junit.Test;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.Assert.*;

public class StationInfoTest
{
    @Test
    public void getInfo() throws IOException
    {
        //given
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/Subwaystations.json"));

        //when
        StationInfo feed = gson.fromJson(reader, StationInfo.class);
        reader.close();

        //then
        assertEquals("Astor Pl", feed.features.get(0).properties.name);
        assertEquals("4-6-6 Express", feed.features.get(0).properties.line);
        assertEquals("1", feed.features.get(0).properties.objectid);
        assertEquals(-73.99106999861966, feed.features.get(0).geometry.coordinates.get(0), 0);
        assertEquals(40.73005400028978, feed.features.get(0).geometry.coordinates.get(1), 0);

    }
}
