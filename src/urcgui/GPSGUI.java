package urcgui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Collection;
import javax.swing.JFrame;


public class GPSGUI extends JFrame{
    private final MapPanel map;
    private final UIPanel ui;
    
    public GPSGUI(double latitude, double longitude) throws Exception {
        super("GPS Plotter");
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        map = new MapPanel();
        this.add(map, BorderLayout.WEST);
        drawBoundingRectangle(new Point(latitude, longitude), 1);
        
        ui = new UIPanel(map);
        this.add(ui, BorderLayout.EAST);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        
    }
    
    public void drawBoundingRectangle(Point base, double distance) {
        double radius = 6371.01;

        
        // angular distance in radians on a great circle
        double radDist = distance / radius;
        double MIN_LAT = Math.toRadians(-90);
        double MAX_LAT = Math.toRadians(90);
        double MIN_LON = Math.toRadians(-180);
        double MAX_LON = Math.toRadians(180);
        
        double radLat = Math.toRadians(base.getLatitude());
        double radLon = Math.toRadians(base.getLongitude());

        double minLat = radLat - radDist;
        double maxLat = radLat + radDist;

        double minLon, maxLon;
        if (minLat > MIN_LAT && maxLat < MAX_LAT) {
            double deltaLon = Math.asin(Math.sin(radDist) / Math.cos(radLat));
            minLon = radLon - deltaLon;
            if (minLon < MIN_LON) minLon += 2d * Math.PI;
            maxLon = radLon + deltaLon;
            if (maxLon > MAX_LON) maxLon -= 2d * Math.PI;
        }
        else {
            // a pole is within the distance
            minLat = Math.max(minLat, MIN_LAT);
            maxLat = Math.min(maxLat, MAX_LAT);
            minLon = MIN_LON;
            maxLon = MAX_LON;
        }
        
        minLat = Math.toDegrees(minLat);
        maxLat = Math.toDegrees(maxLat);
        minLon = Math.toDegrees(minLon);
        maxLon = Math.toDegrees(maxLon);
        
        Color gridColor = new Color(97, 227, 36, 255);
        
        //// Draw Boundary ////
        Point lowerLeftPoint = new Point(minLat, minLon);
        Point upperLeftPoint = new Point(maxLat, minLon);
        Point upperRightPoint = new Point(maxLat, maxLon);
        Point lowerRightPoint = new Point(minLat, maxLon);
        
        this.map.addSegment(new Segment(lowerLeftPoint, upperLeftPoint, gridColor));
        this.map.addSegment(new Segment(upperLeftPoint, upperRightPoint, gridColor));
        this.map.addSegment(new Segment(upperRightPoint, lowerRightPoint, gridColor));
        this.map.addSegment(new Segment(lowerRightPoint, lowerLeftPoint, gridColor));
        
        //// Draw Grid ////
        double latitudeStep = (maxLat - minLat) / 20, longitudeStep = (maxLon - minLon) / 20;
        for(double lat = minLat; lat <= maxLat; lat += latitudeStep)
            this.map.addSegment(new Segment(new Point(lat, minLon), new Point(lat, maxLon), gridColor));
        
        for(double lon = minLon; lon <= maxLon; lon += longitudeStep)
            this.map.addSegment(new Segment(new Point(minLat, lon), new Point(maxLat, lon), gridColor));
        
        //// Draw Base ////
        this.map.addPOI(new POI(base, "Base", 255, 0, 0, 255));
        
    }
    
    
    
    /**
    * Deletes all the registered segments and POIs.
    */
    public void clear() {
        this.map.clear();
    }

    /**
    * Adds a segment to the list of segments to display.
    * @param segment the segment to add
    */
    public void addSegment(Segment segment) {
        this.map.addSegment(segment);
    }

    /**
    * Adds a whole collection of segments to the list of segments to display
    * @param segments the collection of segments to add
    */
    public void addSegments(Collection<Segment> segments) {
        this.map.addSegments(segments);
    }

    /**
    * Adds a point of interest (POI) to the list of POIs to display. 
    * @param poi the POI to add
    */
    public void addPOI(POI poi) {
        this.map.addPOI(poi);
    }
    
    
}
