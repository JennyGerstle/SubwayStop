import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class SmallestPathToStation
{
    public List<StationInfo.Station> getPathToStation(StationInfo.Station start, StationInfo.Station end, StationInfo stations, LineInfo lineInfo)
    {
        List<StationInfo.Station> stationList = new ArrayList<>();
        HashMap<Integer, Integer> stationAndDistance = new HashMap<>();
        HashMap<Integer, Integer> stationAndPrevious = new HashMap<>();
        HashMap<Integer, Integer> visitedStops = new HashMap<>();

        for(StationInfo.Station station : stations.features)
        {
            stationList.add(station);
            if(station != start)
            {
                stationAndDistance.put(station.properties.objectid, Integer.MAX_VALUE);
            }
            else
            {
                stationAndDistance.put(station.properties.objectid, 0);
            }
            stationAndPrevious.put(station.properties.objectid, 0);
        }
        getStationDistances(stationAndDistance, stationAndPrevious, visitedStops, stationList, lineInfo);
        List<StationInfo.Station> stationPath = new ArrayList<>();
        Integer distance = visitedStops.get(end.properties.objectid);
        StationInfo.Station previousStation = end;
        while(distance >= 1)
        {
            Integer previous = stationAndPrevious.get(previousStation.properties.objectid);
            previousStation = stationList.get(previous-1);
            stationPath.add(previousStation);
            distance--;
        }
        Collections.reverse(stationPath);
        return stationPath;
    }
    public void getStationDistances(HashMap<Integer, Integer> stationAndDistance, HashMap<Integer, Integer> stationAndPrevious, HashMap<Integer, Integer> visitedStops, List<StationInfo.Station> stationList, LineInfo lineInfo)
    {
        StationConnection stationConncetion = new StationConnection();
        List<Integer> connectingStations = new ArrayList<>();
        int curDistance;
        Integer nextStationInt = null;
        Integer stationDistance;
        while(stationAndDistance.values().size() > 1)
        {
            stationDistance = Collections.min(stationAndDistance.values());
            List<Integer> lowestDistanceStops = new ArrayList<>();
            if (stationAndDistance.containsValue(stationDistance))
            {
                for (Map.Entry<Integer, Integer> entry : stationAndDistance.entrySet())
                {
                    if (entry.getValue().equals(stationDistance))
                    {
                        nextStationInt = entry.getKey();
                        break;
                    }
                }
            }
            StationInfo.Station nextStation = null;
            for(StationInfo.Station stationCheck : stationList)
            {
                if(stationCheck.properties.objectid == nextStationInt)
                {
                    nextStation = stationCheck;
                }
            }
            connectingStations = stationConncetion.getConnections(nextStation, lineInfo);
            if(stationAndPrevious.get(nextStationInt) == 0)
            {
                curDistance = 1;
            }
            else
            {
                curDistance = stationAndDistance.get(nextStationInt) + 1;
            }
            for (Integer connection : connectingStations)
            {
                Integer nextConnectingStation = stationAndDistance.get(connection);
                if (!visitedStops.containsKey(connection) && nextConnectingStation > curDistance)
                {
                    stationAndDistance.remove(connection, stationAndDistance.get(connection));
                    stationAndDistance.put(connection, curDistance);
                    stationAndPrevious.remove(connection);
                    stationAndPrevious.put(connection, nextStationInt);
                }
            }
            Integer distanceFromStart = stationAndDistance.get(nextStationInt);
            visitedStops.put(nextStationInt, distanceFromStart);
            stationAndDistance.remove(nextStationInt);
        }
        Integer nextIteratedStation = stationAndDistance.keySet().iterator().next();
        Integer distanceFromStart = stationAndDistance.get(nextIteratedStation);
        visitedStops.put(nextIteratedStation, distanceFromStart);
        stationAndDistance.remove(nextIteratedStation);
    }
}
