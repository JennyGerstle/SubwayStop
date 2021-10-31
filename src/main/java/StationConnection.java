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

    public List<Integer> getConnections(int stationId) throws IOException
    {
        //splitting the string station Json gives into the different lines
        Reader readerStation = Files.newBufferedReader(Paths.get("src/main/resources/Subwaystations.json"));
        StationInfo feed = gson.fromJson(readerStation, StationInfo.class);
        String[] lines = null;
        for(StationInfo.Features station : feed.features)
        {
            if(station.properties.objectid.equals(String.valueOf(stationId)))
            {
                //splits the lines up to individual line strings
                lines = station.properties.line.split("-");
                break;
            }
        }
        List<Integer> connectingStations = new ArrayList<>();
        HashMap<String, List<Integer>> subwayLines = getSubwayLines();
        for(String line : lines)
        {
            List<Integer> subLines = subwayLines.get(line);
            int index = subLines.indexOf(stationId);
            if(index > 0)
            {
                connectingStations.add(subLines.get(index - 1));
            }
            if(index < subLines.size() - 1)
            {
                connectingStations.add(subLines.get(index + 1));
            }
        }
        return connectingStations;
    }

    private HashMap<String, List<Integer>> getSubwayLines() throws IOException
    {
        Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/SubwayLines.json"));
        LineInfo feed = gson.fromJson(reader, LineInfo.class);
        List<List<Integer>> subwayLines = new ArrayList<List<Integer>>();
        HashMap<String, List<Integer>> subwayLinesConnection = new HashMap<String, List<Integer>>();
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

