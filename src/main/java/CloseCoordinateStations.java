import java.util.ArrayList;
import java.util.List;

public class CloseCoordinateStations
{
    public StationInfo.Station getClosestStation(List<Double> coordinates, StationInfo stations)
    {
        StationInfo.Station closestStations = stations.features.get(0);
        for(StationInfo.Station station : stations.features)
        {
            if(closer(closestStations, station, coordinates))
            {
                closestStations = station;
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
