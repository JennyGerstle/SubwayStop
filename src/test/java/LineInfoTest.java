import com.google.gson.Gson;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class LineInfoTest
{
    @Test
    public void lineInfo() throws IOException
    {
        //given
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/SubwayLines.json"));
        String lines[] = {"55", "459", "275", "25", "464"};
        //when
        LineInfo feed = gson.fromJson(reader, LineInfo.class);
        reader.close();

        //then
        assertEquals(lines[0], feed.A.get(0));
        assertEquals(lines[1], feed.C.get(0));
        assertEquals(lines[2], feed.D.get(0));
        assertEquals(lines[3], feed.sevenExpress.get(11));
        assertEquals(lines[4], feed.oneTrain.get(4));
    }
}