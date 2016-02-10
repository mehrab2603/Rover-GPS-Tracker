package urcgui;

import java.awt.Color;


/**
 * Geographic point, defined by its coordinates and by a label.
 * 
 * @author Christophe Jacquet
 * @see POI
 *
 */
public class POI extends Point {
        private final String label;
        private final Color color;
        
        public POI(double latitude, double longitude, String label, int r, int g, int b, int a) {
            super(latitude, longitude);
            this.label = label;
            this.color = new Color(r, g, b, a);
        }
        
        public POI(double latitude, double longitude, double direction, String label, int r, int g, int b, int a) {
            super(latitude, longitude, direction);
            this.label = label;
            this.color = new Color(r, g, b, a);
        }
        
        public POI(Point point, String label, int r, int g, int b, int a) {
            this(point.getLatitude(), point.getLongitude(), label, r, g, b, a);
        }
        
        public String getLabel() {
            return label;
        }
        
        public Color getColor() {
            return color;
        }
        
        @Override
        public String toString() {
            return this.label;
        }
}
