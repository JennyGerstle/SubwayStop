import java.util.List;

public class StationInfo
{
    List<Station> features;

    public class Station
    {
        Properties properties;
        Geometry geometry;
        private final List<Station> connections;

        public Station(List<Station> connections)
        {
            this.connections = connections;
        }
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
}
