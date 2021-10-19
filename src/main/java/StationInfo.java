import java.util.List;

public class StationInfo
{
    List<Features> features;
    static class Features
    {
        Properties properties;
        Geometry geometry;
    }
    static class Properties
    {
        String name;
        String line;
        String objectid;
    }
    static class Geometry
    {
        List<Double> coordinates;
    }
}
