import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StationInfo
{
    List<Features> features;
    List<Station> station;
    static class Features
    {
        Properties properties;
        Geometry geometry;
    }
    static class Properties
    {
        String name;
        String line;
        int objectid;
    }
    static class Geometry
    {
        List<Double> coordinates;
    }
    static class Station
    {

    }

    public HashMap<String, List<Station>> getSubwayMap()
    {
        HashMap<String, List<Station>> subwayMap;
        for(LineInfo lines : LineInfo)
        {
            subwayMap.put(, lines);
        }
        return subwayMap;
    }

    public List<Station> getConnections(HashMap<String, List<Station>> subwayLines, StationInfo stationInfo)
    {
        String[] lines = null;
        for(StationInfo.Features stationFeature : features)
        {
            //splits the lines up to individual line strings
            lines = stationFeature.properties.line.split("-");
        }
        int stationId = stationInfo.features.get(0).properties.objectid;
        List<Station> connectingStations = new ArrayList<>();
        for(String connectingLine : lines)
        {

            List<Station> subLines = subwayLines.get(connectingLine);
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
}
