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
        return distanceFormula(first, coordinates) < distanceFormula(second, coordinates);
    }
    private Double distanceFormula(StationInfo.Station station, List<Double> coordinates)
    {
        return (Math.sqrt(Math.pow(station.geometry.coordinates.get(0) - coordinates.get(0), 2)+ Math.pow(station.geometry.coordinates.get(1) - coordinates.get(1), 2)));
    }
}
