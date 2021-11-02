import java.util.ArrayList;
import java.util.List;

public class CloseCoordinateStations
{
    public List<StationInfo.Station> getClosestStation(List<Double> coordinates, StationInfo stations)
    {
        List<StationInfo.Station> closestStations = new ArrayList<>();
        closestStations.add(stations.features.get(0));
        closestStations.add(stations.features.get(1));
        for(StationInfo.Station station : stations.features)
        {
            if(closer(closestStations.get(0), station, coordinates) && !station.equals(closestStations.get(1)))
            {
                closestStations.add(station);
                closestStations.remove(0);
            }
            if(closer(closestStations.get(1), station, coordinates) && !station.equals(closestStations.get(0)))
            {
                closestStations.add(station);
                closestStations.remove(1);
            }
        }
        return closestStations;
    }
    private boolean closer(StationInfo.Station first, StationInfo.Station second, List<Double> coordinates)
    {
        Double firstDistance = (Math.sqrt(Math.pow(first.geometry.coordinates.get(0) - coordinates.get(0), 2)+ Math.pow(first.geometry.coordinates.get(1) - coordinates.get(1), 2)));
        Double secondDistance = (Math.sqrt(Math.pow(second.geometry.coordinates.get(0) - coordinates.get(0), 2)+ Math.pow(second.geometry.coordinates.get(1) - coordinates.get(1), 2)));
        return firstDistance < secondDistance;
    }
}
