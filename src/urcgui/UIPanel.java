package urcgui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.net.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UIPanel extends JPanel{
    private MapPanel map;
    private JLabel labelLatitudeDegree, labelLatitudeMinute, labelLatitudeSecond, labelLongitudeDegree, labelLongitudeMinute, labelLongitudeSecond, labelPointName, labelSelectedPoint, labelPointPosition, labelDistanceFrom, labelPointLatitude, labelPointLongitude, labelDistance, labelIP, labelPort;
    private JTextField textLatitudeDegree, textLatitudeMinute, textLatitudeSecond, textLongitudeDegree, textLongitudeMinute, textLongitudeSecond, textPointName, textIP, textPort;
    private JComboBox comboSelectedPoint, comboDistanceFromPoint;
    private JButton buttonAdd, buttonClear, buttonRemove, buttonConnect;
    private UDPClient client;
    private Thread t;
    private volatile POI rover;
    
    private JButton buttonTest;
    
    public UIPanel(MapPanel map) throws UnknownHostException {
        this.map = map;
        this.setMinimumSize(new Dimension(350, 600));
        this.setPreferredSize(new Dimension(350, 600));
        this.setLayout(null);
        
        // Labels ////////////////
        
        this.labelLatitudeDegree = new JLabel("Latitude Degree");
        this.labelLatitudeDegree.setBounds(10, 10, 130, 20);
        this.add(this.labelLatitudeDegree);
        
        this.labelLatitudeMinute = new JLabel("Latitude Minute");
        this.labelLatitudeMinute.setBounds(10, 40, 130, 20);
        this.add(this.labelLatitudeMinute);
        
        this.labelLatitudeSecond = new JLabel("Latitude Second");
        this.labelLatitudeSecond.setBounds(10, 70, 130, 20);
        this.add(this.labelLatitudeSecond);
        
        this.labelLatitudeDegree = new JLabel("Longitude Degree");
        this.labelLatitudeDegree.setBounds(10, 100, 130, 20);
        this.add(this.labelLatitudeDegree);
        
        this.labelLatitudeMinute = new JLabel("Longitude Minute");
        this.labelLatitudeMinute.setBounds(10, 130, 130, 20);
        this.add(this.labelLatitudeMinute);
        
        this.labelLatitudeSecond = new JLabel("Longitude Second");
        this.labelLatitudeSecond.setBounds(10, 160, 130, 20);
        this.add(this.labelLatitudeSecond);
        
        this.labelPointName = new JLabel("Point Name");
        this.labelPointName.setBounds(10, 190, 130, 20);
        this.add(this.labelPointName);
        
        this.labelSelectedPoint = new JLabel("Selected Point");
        this.labelSelectedPoint.setBounds(10, 250, 130, 20);
        this.add(this.labelSelectedPoint);
        
        this.labelPointPosition = new JLabel("Point Position");
        this.labelPointPosition.setBounds(10, 280, 130, 20);
        this.add(this.labelPointPosition);
        
        this.labelPointLatitude = new JLabel("");
        this.labelPointLatitude.setBounds(150, 280, 160, 20);
        this.add(this.labelPointLatitude);
        
        this.labelPointLongitude = new JLabel("");
        this.labelPointLongitude.setBounds(150, 310, 160, 20);
        this.add(this.labelPointLongitude);
        
        this.labelDistanceFrom = new JLabel("Distance From");
        this.labelDistanceFrom.setBounds(10, 340, 130, 20);
        this.add(this.labelDistanceFrom);
        
        this.labelDistance = new JLabel("");
        this.labelDistance.setBounds(150, 370, 160, 20);
        this.add(this.labelDistance);
        
        this.labelIP = new JLabel("IP");
        this.labelIP.setBounds(10, 650, 20, 20);
        this.add(this.labelIP);
        
        this.labelPort = new JLabel("Port");
        this.labelPort.setBounds(150, 650, 30, 20);
        this.add(this.labelPort);
        
        
        // Text Fields ////////////////
        
        this.textLatitudeDegree = new JTextField();
        this.textLatitudeDegree.setBounds(150, 10, 160, 20);
        this.add(this.textLatitudeDegree);
        
        this.textLatitudeMinute = new JTextField();
        this.textLatitudeMinute.setBounds(150, 40, 160, 20);
        this.add(this.textLatitudeMinute);
        
        this.textLatitudeSecond = new JTextField();
        this.textLatitudeSecond.setBounds(150, 70, 160, 20);
        this.add(this.textLatitudeSecond);
        
        this.textLongitudeDegree = new JTextField();
        this.textLongitudeDegree.setBounds(150, 100, 160, 20);
        this.add(this.textLongitudeDegree);
        
        this.textLongitudeMinute = new JTextField();
        this.textLongitudeMinute.setBounds(150, 130, 160, 20);
        this.add(this.textLongitudeMinute);
        
        this.textLongitudeSecond = new JTextField();
        this.textLongitudeSecond.setBounds(150, 160, 160, 20);
        this.add(this.textLongitudeSecond);
        
        this.textPointName = new JTextField();
        this.textPointName.setBounds(150, 190, 160, 20);
        this.add(this.textPointName);
        
        this.textIP = new JTextField();
        this.textIP.setBounds(30, 650, 120, 20);
        this.add(this.textIP);
        
        this.textPort = new JTextField();
        this.textPort.setBounds(185, 650, 40, 20);
        this.add(this.textPort);
        
        // Buttons ////////////////
        
        this.buttonAdd = new JButton("Add Point");
        this.buttonAdd.setBounds(130, 220, 120, 20);
        this.add(this.buttonAdd);
        
        //this.buttonClear = new JButton("Clear");
        //this.buttonClear.setBounds(10, 570, 120, 20);
        //this.add(this.buttonClear);
        
        this.buttonRemove = new JButton("Remove Point");
        this.buttonRemove.setBounds(130, 400, 140, 20);
        this.add(this.buttonRemove);
        
        this.buttonConnect = new JButton("Connect");
        this.buttonConnect.setBounds(240, 650, 100, 20);
        this.add(this.buttonConnect);
        
//        this.buttonTest = new JButton("Test");
//        this.buttonTest.setBounds(240, 450, 100, 20);
//        this.add(this.buttonTest);
        
        // Combo Boxes ////////////////
        
        this.comboSelectedPoint = new JComboBox();
        this.comboSelectedPoint.setBounds(150, 250, 160, 20);
        this.add(this.comboSelectedPoint);
        
        this.comboDistanceFromPoint = new JComboBox();
        this.comboDistanceFromPoint.setBounds(150, 340, 160, 20);
        this.add(this.comboDistanceFromPoint);
        
        if(this.map != null) {
            setListeners();
            updateComboBox();
        }
    }
    
    private synchronized void updateComboBox() {
        this.comboSelectedPoint.removeAllItems();
        this.comboDistanceFromPoint.removeAllItems();
        List<POI> list = this.map.getPOIs();
        for(int i = 0; i < list.size(); i++) {
            this.comboSelectedPoint.addItem(list.get(i));
            this.comboDistanceFromPoint.addItem(list.get(i));
        }
        this.comboSelectedPoint.setSelectedIndex(0);
        this.comboDistanceFromPoint.setSelectedIndex(0);
    }
    
    private synchronized void setListeners() {
        
        this.buttonAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                double latitudeDegree, latitudeMinute, latitudeSecond, longitudeDegree, longitudeMinute, longitudeSecond;
                try {
                    latitudeDegree = Double.parseDouble(textLatitudeDegree.getText().trim());
                    latitudeMinute = Double.parseDouble(textLatitudeMinute.getText().trim());
                    latitudeSecond = Double.parseDouble(textLatitudeSecond.getText().trim());
                    longitudeDegree = Double.parseDouble(textLongitudeDegree.getText().trim());
                    longitudeMinute = Double.parseDouble(textLongitudeMinute.getText().trim());
                    longitudeSecond = Double.parseDouble(textLongitudeSecond.getText().trim());
                    
                    String pointName = textPointName.getText().trim();
                    if(pointName.isEmpty()) throw new Exception();
                
                    double latitude = latitudeDegree + latitudeMinute / 60.0 + latitudeSecond / 3600.0;
                    double longitude = longitudeDegree + longitudeMinute / 60.0 + longitudeSecond / 3600.0;

                    map.addPOI(new POI(new Point(latitude, longitude), pointName, 0, 255, 0, 255));
                    map.repaint();
                    
                    updateComboBox();
                    
                    textLatitudeDegree.setText("");
                    textLatitudeMinute.setText("");
                    textLatitudeSecond.setText("");
                    textLongitudeDegree.setText("");
                    textLongitudeMinute.setText("");
                    textLongitudeSecond.setText("");
                    textPointName.setText("");
                }
                catch(Exception ex) {
                    ex.printStackTrace();
                }
                
            }
        });
        
        this.buttonRemove.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                POI poi = (POI)comboSelectedPoint.getSelectedItem();
                if(poi == null || poi.getLabel().equals("Base") || poi == rover) {
                    JOptionPane.showMessageDialog(null, "Cannot remove base or rover points.");
                    return;
                }
                map.removePOI(poi);
                map.repaint();
                updateComboBox();
            }
        });
        
//        this.buttonTest.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Thread t = new Thread(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        try {
//                            BufferedReader br = new BufferedReader(new FileReader("/dev/ttyACM1"));
//                            double latitude = 0;
//                            double longitude = 0;
//                            String line = "";
//                            while(true) {
//                                line = br.readLine();
//                                if(line == null) continue;
//                                //System.out.println(line);
//                                try {
//                                    String[] array = line.split(" ");
//                                    if(array.length != 2) throw new Exception();
//                                    latitude = Double.parseDouble(array[0]);
//                                    longitude = Double.parseDouble(array[1]);
//                                    System.out.println(latitude + ", " + longitude);
//                                }
//                                catch(Exception ex) {
//                                    continue;
//                                }
//
//                                if(rover == null) {
//                                    rover = new POI(latitude, longitude, "Rover", 0, 0, 255, 255);
//                                    map.addPOI(rover);
//                                    updateComboBox();
//                                }
//                                else {
//                                    rover.updatePoint(latitude, longitude);
//                                    if(comboSelectedPoint.getSelectedItem() == rover) {
//                                        comboSelectedPoint.setSelectedIndex(0);
//                                        comboSelectedPoint.setSelectedItem(rover);
//                                    }
//                                    if(comboDistanceFromPoint.getSelectedItem() == rover) {
//                                        comboDistanceFromPoint.setSelectedIndex(0);
//                                        comboDistanceFromPoint.setSelectedItem(rover);
//                                    }
//                                }
//                                map.repaint();
//                                //Thread.sleep(600);
//
//                            }
//                        }
//                        catch(Exception ex) {
//                            System.out.println(ex.toString());
//                        }
//                    }
//                });
//                t.start();
//            }
//        });
        
        this.buttonConnect.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(buttonConnect.getText().equals("Connect")) {
                    long counter = 0;
                    try {
                        client = new UDPClient(InetAddress.getByName(textIP.getText()), Integer.parseInt(textPort.getText()));
                    }
                    catch(Exception ex) {
                        JOptionPane.showMessageDialog(null, "Could not connect");
                        return;
                    }
                    buttonConnect.setText("Disconnect");
                    t = new Thread(new Runnable() {
                        @Override
                        public synchronized void run() {
                            double latitude = 0, longitude = 0, degree = 0;
                            while(true) {
                                if (Thread.currentThread().isInterrupted()) break;
                                
                                try {
                                    client.makeRequest();
                                    String message = client.getServerMessage();
                                    System.out.println(message);
                                    String lines[] = message.split("\\r?\\n");
                                    String[] array = null;
                                    boolean found = false;
                                    for(String line : lines) {
                                        array = line.split(" ");
                                        if(array[0].equals("GPS")) {found = true; break;}
                                    }
                                    if(!found || array.length != 4) throw new Exception();
                                    latitude = Double.parseDouble(array[1]);
                                    longitude = Double.parseDouble(array[2]);
                                    degree = Double.parseDouble(array[3]);
                                    System.out.println(latitude + " " + longitude);
                                }
                                catch(Exception ex) {
                                    System.out.println("Invalid data received");
                                    continue;
                                }
                                if(rover == null) {
                                    rover = new POI(latitude, longitude, degree, "Rover", 0, 0, 255, 255);
                                    map.addPOI(rover);
                                    updateComboBox();
                                }
                                else {
                                    rover.updatePoint(latitude, longitude, degree);
                                    if(comboSelectedPoint.getSelectedItem() == rover) {
                                        POI poi = rover;
                                        POI other = (POI)comboDistanceFromPoint.getSelectedItem();
                                        if(other == null) {
                                            labelDistance.setText("");
                                        }
                                        double lat = poi.getLatitude(), lon = poi.getLongitude();
                                        labelPointLatitude.setText(decimalToDMS(lat) + " N");
                                        labelPointLongitude.setText(decimalToDMS(lon) + " E");

                                        if(other != null) {
                                            labelDistance.setText(round(poi.distanceTo(other) * 1000, 2)  + " m");
                                        }
                                    }
                                    if(comboDistanceFromPoint.getSelectedItem() == rover) {
                                        POI poi = rover;
                                        POI other = (POI)comboSelectedPoint.getSelectedItem();
                                        if(poi == null || other == null) labelDistance.setText("");
                                        else labelDistance.setText(round(poi.distanceTo(other) * 1000, 2)  + " m");
                                    }
                                }
                                map.repaint();
                            }
                            System.out.println("Thread stopped");
                        }
                    });
                    t.start();
                }
                else if(buttonConnect.getText().equals("Disconnect")) {
                    buttonConnect.setText("Connect");
                    t.interrupt();
                }
            }
        });
        
        
        /*
        this.buttonClear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //map.clear();
                //map.updateUI();
            }
        });
        */
        this.comboSelectedPoint.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    POI poi = (POI)comboSelectedPoint.getSelectedItem();
                    POI other = (POI)comboDistanceFromPoint.getSelectedItem();
                    if(poi == null) {
                        labelPointLatitude.setText("");
                        labelPointLongitude.setText("");
                        labelDistance.setText("");
                        return;
                    }
                    if(other == null) {
                        labelDistance.setText("");
                    }
                    double latitude = poi.getLatitude(), longitude = poi.getLongitude();
                    labelPointLatitude.setText(decimalToDMS(latitude) + " N");
                    labelPointLongitude.setText(decimalToDMS(longitude) + " E");
                    
                    if(other != null) {
                        labelDistance.setText(round(poi.distanceTo(other) * 1000, 2)  + " m");
                    }
                }
            }
        });
        
        this.comboDistanceFromPoint.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    POI poi = (POI)comboSelectedPoint.getSelectedItem();
                    POI other = (POI)comboDistanceFromPoint.getSelectedItem();
                    if(poi == null || other == null) {
                        labelDistance.setText("");
                        return;
                    }
                    labelDistance.setText(round(poi.distanceTo(other) * 1000, 2)  + " m");
                }
            }
        });
    }
    
    private synchronized String decimalToDMS(double decimal) {
        double degree, minute, second;
        degree = Math.floor(decimal);
        double fraction = decimal - degree;
        minute = Math.floor(fraction * 60);
        second = fraction * 3600 - minute * 60;
        degree = round(degree, 2);
        minute = round(minute, 2);
        second = round(second, 2);
        return degree + "° " + minute + "' " + second + "\"";
    }
    
    private synchronized double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
