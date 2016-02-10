package urcgui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class BaseCoordinateGUI extends JFrame{
    private JLabel labelLatitude, labelLongitude;
    private JTextField textLatitude, textLongitude;
    private JButton buttonOK;
    private BaseCoordinateGUI context;
    
    public BaseCoordinateGUI() {
        super("GPS Plotter");
        this.setMinimumSize(new Dimension(350, 155));
        this.setPreferredSize(new Dimension(350, 155));
        this.setLayout(null);
        
        this.context = this;
        
        this.labelLatitude = new JLabel("Base Latitude");
        this.labelLatitude.setBounds(10, 10, 130, 20);
        this.add(this.labelLatitude);
        
        this.labelLongitude = new JLabel("Base Longitude");
        this.labelLongitude.setBounds(10, 40, 130, 20);
        this.add(this.labelLongitude);
        
        this.textLatitude = new JTextField();
        this.textLatitude.setBounds(150, 10, 160, 20);
        this.add(this.textLatitude);
        
        this.textLongitude = new JTextField();
        this.textLongitude.setBounds(150, 40, 160, 20);
        this.add(this.textLongitude);
        
        this.buttonOK = new JButton("OK");
        this.buttonOK.setBounds(125, 70, 100, 20);
        this.add(this.buttonOK);
        
        this.buttonOK.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String latitude = textLatitude.getText(), longitude = textLongitude.getText();
                if(latitude.isEmpty() || longitude.isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "Wrong Input");
                    return;
                }
                try {
                    new GPSGUI(Double.parseDouble(latitude), Double.parseDouble(longitude)).setVisible(true);
                    ((JButton)e.getSource()).getParent().getParent().setVisible(false);
                }
                catch(Exception ex) {
                    JOptionPane.showMessageDialog(rootPane, "Wrong Input");
                }
            }
        });
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
    
    public static void main(String[] args) throws Exception {
        //GPSGUI window = new GPSGUI(23.794562, 90.402206);
        //window.setVisible(true);
        new BaseCoordinateGUI().setVisible(true);
    }
}
