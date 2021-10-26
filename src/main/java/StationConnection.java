import com.google.gson.Gson;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StationConnection
{
    private Gson gson = new Gson();

    public List<String> getConnections(String stationName) throws IOException
    {
        //splitting the string station Json gives into the different lines
        Reader readerStation = Files.newBufferedReader(Paths.get("src/main/resources/Subwaystations.json"));
        StationInfo feed = gson.fromJson(readerStation, StationInfo.class);
        String[] lines = null;
        for(int station = 0; station < feed.features.size(); station++)
        {
            if(feed.features.get(station).properties.name.equals(stationName))
            {
                //splits the lines up to individual line strings
                lines = feed.features.get(station).properties.line.split("-");
                break;
            }
        }
        List<String> connectingStations = new ArrayList<String>();
        HashMap<String, List<String>> subwayLines = getSubwayLines();
        for(String line : lines)
        {
            for (String lineName : subwayLines.keySet())
            {
                if(lineName.equals(line))
                {
                    connectingStations.addAll(subwayLines.get(lineName));
                }
            }
        }
        return connectingStations;
    }

    private HashMap<String, List<String>> getSubwayLines() throws IOException
    {
        Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/SubwayLines.json"));
        LineInfo feed = gson.fromJson(reader, LineInfo.class);
        List<List<String>> subwayLines = new ArrayList<List<String>>();
        HashMap<String, List<String>> subwayLinesConnection = new HashMap<String, List<String>>();
        subwayLinesConnection.put("A", feed.A);
        subwayLinesConnection.put("B", feed.B);
        subwayLinesConnection.put("C", feed.C);
        subwayLinesConnection.put("D", feed.D);
        subwayLinesConnection.put("E", feed.E);
        subwayLinesConnection.put("F", feed.F);
        subwayLinesConnection.put("G", feed.G);
        subwayLinesConnection.put("J", feed.J);
        subwayLinesConnection.put("L", feed.L);
        subwayLinesConnection.put("M", feed.M);
        subwayLinesConnection.put("N", feed.N);
        subwayLinesConnection.put("Q", feed.Q);
        subwayLinesConnection.put("R", feed.R);
        subwayLinesConnection.put("S", feed.S);
        subwayLinesConnection.put("W", feed.W);
        subwayLinesConnection.put("Z", feed.Z);
        subwayLinesConnection.put("1", feed.oneTrain);
        subwayLinesConnection.put("2", feed.twoTrain);
        subwayLinesConnection.put("3", feed.threeTrain);
        subwayLinesConnection.put("4", feed.fourTrain);
        subwayLinesConnection.put("5", feed.fiveTrain);
        subwayLinesConnection.put("6", feed.sixTrain);
        subwayLinesConnection.put("7", feed.sevenTrain);
        subwayLinesConnection.put("7 Express", feed.sevenExpress);
        subwayLinesConnection.put("6 Express", feed.sixExpress);
        return subwayLinesConnection;
    }
}

