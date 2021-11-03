import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StationConnection
{
    private Gson gson = new Gson();

    public List<Integer> getConnections(StationInfo.Station station, LineInfo lineInfo)
    {
        //splits the lines up to individual line strings
        String[] lines = station.properties.line.split("-");
        List<Integer> connectingStations = new ArrayList<>();
        HashMap<String, List<Integer>> subwayLines = getSubwayLines(lineInfo);
        for(String line : lines)
        {
            List<Integer> subLines = subwayLines.get(line);
            int index = subLines.indexOf(station.properties.objectid);
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

    private HashMap<String, List<Integer>> getSubwayLines(LineInfo linenInfo)
    {
        HashMap<String, List<Integer>> subwayLinesConnection = new HashMap<>();
        subwayLinesConnection.put("A", linenInfo.A);
        subwayLinesConnection.put("B", linenInfo.B);
        subwayLinesConnection.put("C", linenInfo.C);
        subwayLinesConnection.put("D", linenInfo.D);
        subwayLinesConnection.put("E", linenInfo.E);
        subwayLinesConnection.put("F", linenInfo.F);
        subwayLinesConnection.put("G", linenInfo.G);
        subwayLinesConnection.put("J", linenInfo.J);
        subwayLinesConnection.put("L", linenInfo.L);
        subwayLinesConnection.put("M", linenInfo.M);
        subwayLinesConnection.put("N", linenInfo.N);
        subwayLinesConnection.put("Q", linenInfo.Q);
        subwayLinesConnection.put("R", linenInfo.R);
        subwayLinesConnection.put("W", linenInfo.W);
        subwayLinesConnection.put("Z", linenInfo.Z);
        subwayLinesConnection.put("1", linenInfo.oneTrain);
        subwayLinesConnection.put("2", linenInfo.twoTrain);
        subwayLinesConnection.put("3", linenInfo.threeTrain);
        subwayLinesConnection.put("4", linenInfo.fourTrain);
        subwayLinesConnection.put("5", linenInfo.fiveTrain);
        subwayLinesConnection.put("6", linenInfo.sixTrain);
        subwayLinesConnection.put("7", linenInfo.sevenTrain);
        subwayLinesConnection.put("7 Express", linenInfo.sevenExpress);
        subwayLinesConnection.put("6 Express", linenInfo.sixExpress);
        return subwayLinesConnection;
    }
}
