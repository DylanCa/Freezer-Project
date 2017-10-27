package projet.frigo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.DynamicTimeSeriesCollection;
import org.jfree.data.time.Second;
import org.jfree.data.xy.XYDataset;


import javax.swing.SwingConstants;


/**
 * The screen of our application
 */

public class View {
    private static class PanelWithBackground extends JPanel {

        private Image backgroundImage;
        private Point backgroundLocation = new Point();

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (getBackgroundImage() != null) {
                g.drawImage(getBackgroundImage(), backgroundLocation.x, backgroundLocation.y, this);
            }
        }

        public Image getBackgroundImage() {
            return backgroundImage;
        }

        public void setBackgroundImage(Image backgroundImage) {
            this.backgroundImage = backgroundImage;
            repaint();
        }

        public Point getBackgroundLocation() {
            return backgroundLocation;
        }

        public void setBackgroundLocation(Point backgroundLocation) {
            this.backgroundLocation = backgroundLocation;
            repaint();
        }
    }

    protected static void View() throws MalformedURLException {
        JFrame frame = new JFrame("Mini-Frigo connect\u00E9");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PanelWithBackground panelWithBackground = new PanelWithBackground();
        panelWithBackground.setLayout(null);
        panelWithBackground.setBackgroundImage(new ImageIcon(new URL(
                "http://ahdzbook.com/data/out/114/hdwp693947929.jpeg")).getImage());
        JLabel backgroundWallpaper = new JLabel(new ImageIcon(new URL(
                "http://www.google.fr")));
        // Next 2 lines should rather be performed by a LayoutManager
        panelWithBackground.setPreferredSize(new Dimension(1024, 768));
        backgroundWallpaper.setBounds(50, 200, backgroundWallpaper.getPreferredSize().width, backgroundWallpaper.getPreferredSize().height);

        panelWithBackground.add(backgroundWallpaper);
        frame.getContentPane().add(panelWithBackground);
        
        JLabel lblNewLabel = new JLabel("Température actuelle");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setBackground(Color.BLACK);
        lblNewLabel.setBounds(50, 83, 196, 40);
        panelWithBackground.add(lblNewLabel);
        
        JLabel fieldTemperature = new JLabel("0°C");
        fieldTemperature.setHorizontalAlignment(SwingConstants.CENTER);
        fieldTemperature.setForeground(Color.RED);
        fieldTemperature.setFont(new Font("Tahoma", Font.PLAIN, 18));
        fieldTemperature.setBounds(229, 83, 196, 40);
        panelWithBackground.add(fieldTemperature);
        
        JLabel lblWarning = new JLabel("Pimp my Fridge !");
        lblWarning.setHorizontalAlignment(SwingConstants.CENTER);
        lblWarning.setFont(new Font("Dialog", Font.ITALIC, 29));
        lblWarning.setBackground(Color.WHITE);
        lblWarning.setBounds(184, 14, 696, 63);
        panelWithBackground.add(lblWarning);
        
        JLabel lblHumidit = new JLabel("Humidité");
        lblHumidit.setHorizontalAlignment(SwingConstants.CENTER);
        lblHumidit.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblHumidit.setBounds(36, 142, 236, 40);
        panelWithBackground.add(lblHumidit);
        
        JLabel lblPointDeRose = new JLabel("Point de Rosée");
        lblPointDeRose.setHorizontalAlignment(SwingConstants.CENTER);
        lblPointDeRose.setFont(new Font("Tahoma", Font.PLAIN, 21));
        lblPointDeRose.setBounds(23, 200, 250, 26);
        panelWithBackground.add(lblPointDeRose);
        
        JLabel fieldTempRosee = new JLabel("0?C");
        fieldTempRosee.setHorizontalAlignment(SwingConstants.CENTER);
        fieldTempRosee.setForeground(Color.PINK);
        fieldTempRosee.setFont(new Font("Tahoma", Font.PLAIN, 18));
        fieldTempRosee.setBounds(200, 203, 250, 22);
        panelWithBackground.add(fieldTempRosee);
        
        JLabel fieldHumidity = new JLabel("0%");
        fieldHumidity.setHorizontalAlignment(SwingConstants.CENTER);
        fieldHumidity.setForeground(Color.BLUE);
        fieldHumidity.setFont(new Font("Tahoma", Font.PLAIN, 18));
        fieldHumidity.setBounds(200, 134, 236, 40);
        panelWithBackground.add(fieldHumidity);
        
        JLabel lblConsigne = new JLabel("Température désirée :");
        lblConsigne.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblConsigne.setBounds(449, 83, 173, 40);
        panelWithBackground.add(lblConsigne);
        
        JLabel labelConsigne = new JLabel("17");
        labelConsigne.setForeground(Color.ORANGE);
        labelConsigne.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelConsigne.setBounds(632, 83, 20, 40);
        panelWithBackground.add(labelConsigne);
        
        JButton buttonConsigneMinus = new JButton("Baisser la température");
        buttonConsigneMinus.setFont(new Font("Tahoma", Font.PLAIN, 13));
        buttonConsigneMinus.setBounds(449, 127, 193, 25);
        panelWithBackground.add(buttonConsigneMinus);
        
        JButton buttonConsignePlus = new JButton("Augmenter la température");
        buttonConsignePlus.setFont(new Font("Tahoma", Font.PLAIN, 13));
        buttonConsignePlus.setBounds(672, 127, 221, 25);
        panelWithBackground.add(buttonConsignePlus);
        
        JLabel labelConsigneDegres = new JLabel("°C");
        labelConsigneDegres.setForeground(Color.ORANGE);
        labelConsigneDegres.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelConsigneDegres.setBounds(662, 83, 29, 40);
        panelWithBackground.add(labelConsigneDegres);
        
        JPanel chartpanel = new JPanel();
        chartpanel.setBounds(36, 286, 955, 471);
        panelWithBackground.add(chartpanel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    View();
                } catch (MalformedURLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }
	
}