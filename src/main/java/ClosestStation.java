import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class ClosestStation
{
    /**
     * takes in the start and end station
     * returns the smallest path so a list of stations
     * get all the stations connected
     * (keep track of the paths each stop takes)
     * have a priority list with all of the distance being maxval
     * start with the connecting stations and
     * mark them with +1 of the start if its not max val and that would be lower than the val it had before if it is change to and the station u take to get to it
     * sort list and get next station to call pop and push to finished stack and then do the previous steps again till u run out of stations
     * then traverse the list to get the station and its previous adding this to a backwards shortest path
     * the flip and return it also maybe keep track of which line u take to get to the stations
     * - return smallest path
     *
*/
    public List<StationInfo.Station> getClosestStation(StationInfo.Station start, StationInfo.Station end, StationInfo stations, LineInfo lineInfo)
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
        StationConnection stationConncetion = new StationConnection();
        List<Integer> connectingStations = new ArrayList<>();
        int curDistance;
        Integer nextStationInt;
        while(stationAndDistance.values().size() > 1)
        {
            stationAndDistance = stationAndDistance
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect( toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
            nextStationInt = stationAndDistance.keySet().iterator().next();
            StationInfo.Station nextStation = null;
            for(StationInfo.Station stationCheck : stationList)
            {
                if(stationCheck.properties.objectid == nextStationInt)
                {
                    nextStation = stationCheck;
                }
            }
            Integer index = stationList.indexOf(nextStation);
            StationInfo.Station startStation = stationList.get(index);
            connectingStations= stationConncetion.getConnections(startStation, lineInfo);
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
        List<StationInfo.Station> stationPath = new ArrayList<>();
        Integer distance = visitedStops.get(end.properties.objectid);
        StationInfo.Station previousStation = end;
        while(distance >= 1)
        {
            Integer previous = stationAndPrevious.get(previousStation.properties.objectid);
            previousStation = stationList.get(previous-1);
            stationPath.add(previousStation);
            distance -=1;
        }
        Collections.reverse(stationPath);
        return stationPath;
    }
}
