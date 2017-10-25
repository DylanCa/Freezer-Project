package projet.frigo;

import java.awt.Color;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.DynamicTimeSeriesCollection;
import org.jfree.data.time.Second;
import org.jfree.data.xy.XYDataset;
import javax.swing.SwingConstants;

public class View extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public final JLabel fieldTemperature;
	public final JLabel fieldHumidity;
	public final JLabel labelConsigne;
	public final JButton buttonConsignePlus;
	public final JButton buttonConsigneMinus;
	public JLabel lblPointDeRose;
	public JLabel fieldTempRosee;
	
	private static final String TITLE = "Graphiques en temps réel";
	private DynamicTimeSeriesCollection dataset;
	
	
	
	

	/**
	 * Create the frame.
	 * @param graphTemp 
	 */
	public View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		dataset = new DynamicTimeSeriesCollection(3, 600, new Second());
		dataset.setTimeBase(new Second());
		dataset.addSeries(new float[1], 0, "Température");
		dataset.addSeries(new float[1], 1, "Humidité");
		dataset.addSeries(new float[1], 2, "Consigne");
		
		

		setTitle("Mini-Frigo connecté");
		setBounds(100, 100, 724, 704);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Temp\u00E9rature actuelle");
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblHumidit = new JLabel("Humidit\u00E9");
		lblHumidit.setHorizontalAlignment(SwingConstants.CENTER);
		lblHumidit.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblConsigne = new JLabel("Temp\u00E9rature d\u00E9sir\u00E9e :");
		lblConsigne.setFont(new Font("Tahoma", Font.PLAIN, 18));

		fieldTemperature = new JLabel("0\u00B0C");
		fieldTemperature.setHorizontalAlignment(SwingConstants.CENTER);
		fieldTemperature.setForeground(Color.RED);
		fieldTemperature.setFont(new Font("Tahoma", Font.PLAIN, 18));

		fieldHumidity = new JLabel("0%");
		fieldHumidity.setHorizontalAlignment(SwingConstants.CENTER);
		fieldHumidity.setForeground(Color.BLUE);
		fieldHumidity.setFont(new Font("Tahoma", Font.PLAIN, 18));

		labelConsigne = new JLabel("0\u00B0C");
		labelConsigne.setForeground(Color.ORANGE);
		labelConsigne.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelConsigne.setText("17");

		buttonConsignePlus = new JButton("Augmenter la temp\u00E9rature");
		buttonConsignePlus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		

		buttonConsigneMinus = new JButton("Baisser la temp\u00E9rature");
		buttonConsigneMinus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel labelConsigneDegres = new JLabel("\u00B0C");
		labelConsigneDegres.setForeground(Color.ORANGE);
		labelConsigneDegres.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		lblPointDeRose = new JLabel("Point de Ros\u00E9e");
		lblPointDeRose.setHorizontalAlignment(SwingConstants.CENTER);
		lblPointDeRose.setFont(new Font("Tahoma", Font.PLAIN, 21));
		
		fieldTempRosee = new JLabel("0°C");
		fieldTempRosee.setHorizontalAlignment(SwingConstants.CENTER);
		fieldTempRosee.setForeground(Color.PINK);
		fieldTempRosee.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ChartPanel chartpanel = new ChartPanel(createChart(dataset));
		
		JLabel lblPimpMyFridge = new JLabel("Pimp my Fridge");
		lblPimpMyFridge.setBackground(new Color(255, 255, 255));
		lblPimpMyFridge.setFont(new Font("Segoe Print", Font.BOLD, 18));
		lblPimpMyFridge.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNewLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(fieldTemperature, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(fieldHumidity, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
						.addComponent(lblHumidit, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(fieldTempRosee, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblPointDeRose, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
					.addGap(185))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblPimpMyFridge, GroupLayout.PREFERRED_SIZE, 696, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(193, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblConsigne)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(labelConsigne)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(labelConsigneDegres, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(buttonConsigneMinus, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(buttonConsignePlus, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(chartpanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(199, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblPimpMyFridge, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHumidit, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPointDeRose))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(fieldHumidity, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(fieldTempRosee)
						.addComponent(fieldTemperature, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblConsigne, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelConsigne, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelConsigneDegres, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonConsigneMinus)
						.addComponent(buttonConsignePlus))
					.addGap(18)
					.addComponent(chartpanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public DynamicTimeSeriesCollection getDataset() {
		return dataset;
	}

	public void setDataset(DynamicTimeSeriesCollection dataset) {
		this.dataset = dataset;
	}

	private JFreeChart createChart(final XYDataset dataset) {
		final JFreeChart result = ChartFactory.createTimeSeriesChart(TITLE, "Temps", "Valeurs", dataset, true, true,
				false);
		final XYPlot plot = result.getXYPlot();
		ValueAxis domain = plot.getDomainAxis();
		domain.setAutoRange(true);
		ValueAxis range = plot.getRangeAxis();
		range.setRange(-10, 100);
		return result;
	}
}
