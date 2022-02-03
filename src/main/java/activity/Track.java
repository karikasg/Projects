package activity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Track {
    List<TrackPoint> trackPoints = new ArrayList<>();

    public void addTrackPoint(TrackPoint trackPoint) {
        trackPoints.add(trackPoint);
    }

    public double getFullElevation() {
        double result = 0;
        double elevation;
        for (int i = 1; i < trackPoints.size(); i++) {
            elevation = trackPoints.get(i).getElevation() - trackPoints.get(i-1).getElevation();
            if (elevation > 0) {
                result += elevation;
            }
        }
        return result;
    }

    public double getFullDecrease() {
        double result = 0;
        double elevation;
        for (int i = 1; i < trackPoints.size(); i++) {
            elevation = trackPoints.get(i).getElevation() - trackPoints.get(i-1).getElevation();
            if (elevation < 0) {
                result -= elevation;
            }
        }
        return result;
    }

    public double getDistance() {
        double result = 0;
        for (int i = 1; i < trackPoints.size(); i++) {
            result += trackPoints.get(i).getDistanceFrom(trackPoints.get(i-1));
        }
        return result;
    }


    public Coordinate findMinimumCoordinate() {
        return new Coordinate(trackPoints.stream().mapToDouble(o->o.getCoordinate().getLatitude()).min().getAsDouble(),
                trackPoints.stream().mapToDouble(o->o.getCoordinate().getLongitude()).min().getAsDouble());
    }

    public Coordinate findMaximumCoordinate() {
        return new Coordinate(trackPoints.stream().mapToDouble(o->o.getCoordinate().getLatitude()).max().getAsDouble(),
                trackPoints.stream().mapToDouble(o->o.getCoordinate().getLongitude()).max().getAsDouble());
    }

    public double getRectangleArea() {
        Coordinate leftDown = findMinimumCoordinate();
        Coordinate rightUp = findMaximumCoordinate();
        double lat = rightUp.getLatitude() - leftDown.getLatitude();
        double lon = rightUp.getLongitude() - leftDown.getLongitude();
        return lat * lon;
    }

    public void loadFromGpx(InputStream is) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String[] elements;
            String line;
            Coordinate coordinate = new Coordinate(0, 0);
            while ((line = br.readLine()) != null) {
                if (line.trim().startsWith("<trkpt")) {
                    elements = line.split("\"");
                    coordinate = new Coordinate(Double.parseDouble(elements[1]), Double.parseDouble(elements[3]));
                }
                if (line.trim().startsWith("<ele")) {
                    elements = line.split("[<>]");
                    double ele = Double.parseDouble(elements[2]);
                    trackPoints.add(new TrackPoint(coordinate, ele));
                }
            }
        } catch (
                IOException ioe) {
            throw new IllegalStateException("File beolvasási hiba", ioe);
        }
    }


    public List<TrackPoint> getTrackPoints() {
        return trackPoints;
    }

}
