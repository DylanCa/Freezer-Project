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


/**
 * The screen of our application
 */
public class View extends JFrame {
	
	/**
	 * Must be unique
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The panel containing the content
	 */
	private JPanel contentPane;
	
	/**
	 * The label with the temperature
	 */
	public final JLabel fieldTemperature;
	
	/**
	 * The label with the humidity
	 */
	public final JLabel fieldHumidity;
	
	/**
	 * The label with the consigne
	 */
	public final JLabel labelConsigne;
	
	/**
	 * The button that increase the consigne
	 */
	public final JButton buttonConsignePlus;
	
	/**
	 * The button that decrease the consigne
	 */
	public final JButton buttonConsigneMinus;
	
	/**
	 * The label with the Point de Ros�e
	 */
	public JLabel lblPointDeRose;
	
	/**
	 * The label with the temperature de Ros�e
	 */
	public JLabel fieldTempRosee;
	
	/**
	 * The top Label
	 */
	public JLabel lblWarning;
	
	/**
	 * The title of the graph
	 */
	private static final String TITLE = "";

	/**
	 * The dataset for the graph
	 */
	private DynamicTimeSeriesCollection dataset;
	

	/**
	 * Create the frame.
	 */
	public View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		dataset = new DynamicTimeSeriesCollection(3, 600, new Second());
		dataset.setTimeBase(new Second());
		dataset.addSeries(new float[1], 0, "Temp\u00E9rature");
		dataset.addSeries(new float[1], 1, "Humidit\u00E9");
		dataset.addSeries(new float[1], 2, "Consigne");
		
		

		setTitle("Mini-Frigo connect\u00E9");
		setBounds(100, 100, 728, 722);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Temp\u00E9rature Interne");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
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
		fieldTemperature.setForeground(new Color(0, 102, 51));
		fieldTemperature.setFont(new Font("Tahoma", Font.PLAIN, 18));

		fieldHumidity = new JLabel("0%");
		fieldHumidity.setHorizontalAlignment(SwingConstants.CENTER);
		fieldHumidity.setForeground(Color.BLUE);
		fieldHumidity.setFont(new Font("Tahoma", Font.PLAIN, 18));

		labelConsigne = new JLabel("0\u00B0C");
		labelConsigne.setForeground(new Color(204, 102, 0));
		labelConsigne.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelConsigne.setText("17");

		buttonConsignePlus = new JButton("Augmenter la temp\u00E9rature");
		buttonConsignePlus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		

		buttonConsigneMinus = new JButton("Baisser la temp\u00E9rature");
		buttonConsigneMinus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel labelConsigneDegres = new JLabel("\u00B0C");
		labelConsigneDegres.setForeground(new Color(204, 102, 0));
		labelConsigneDegres.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		lblPointDeRose = new JLabel("Point de Ros\u00E9e");
		lblPointDeRose.setHorizontalAlignment(SwingConstants.CENTER);
		lblPointDeRose.setFont(new Font("Tahoma", Font.PLAIN, 21));
		
		fieldTempRosee = new JLabel("0\u00B0C");
		fieldTempRosee.setHorizontalAlignment(SwingConstants.CENTER);
		fieldTempRosee.setForeground(Color.PINK);
		fieldTempRosee.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		ChartPanel chartpanel = new ChartPanel(createChart(dataset));
		
		lblWarning = new JLabel("Pimp my Fridge !");
		lblWarning.setBackground(new Color(255, 255, 255));
		lblWarning.setFont(new Font("Malgun Gothic Semilight", Font.ITALIC, 29));
		lblWarning.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblWarning, GroupLayout.PREFERRED_SIZE, 696, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
								.addComponent(fieldTemperature, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(fieldHumidity, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
								.addComponent(lblHumidit, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(fieldTempRosee, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblPointDeRose, GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED))
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
							.addComponent(chartpanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(833))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblWarning, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addGap(15)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblConsigne, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelConsigne, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelConsigneDegres, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonConsigneMinus)
						.addComponent(buttonConsignePlus))
					.addGap(18)
					.addComponent(chartpanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblHumidit, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblPointDeRose))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(fieldTempRosee, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(fieldHumidity, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(fieldTemperature, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	/**
	 * Get the dataset
	 * @return The current dataset
	 */
	public DynamicTimeSeriesCollection getDataset() {
		return dataset;
	}

	/**
	 * Sets the dataset
	 * @param dataset The new dataset
	 */
	public void setDataset(DynamicTimeSeriesCollection dataset) {
		this.dataset = dataset;
	}

	/**
	 * Create a new chart from a dataset
	 * @param dataset The dataset to create it from
	 * @return The new chart
	 */
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
