package projet.frigo;

import java.awt.BorderLayout;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.DynamicTimeSeriesCollection;
import org.jfree.data.time.Second;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;

public class GraphTemp extends ApplicationFrame {

	/**
	 * Required to be unique
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The title for the graph
	 */
	private static final String TITLE = "Température & Humidité ( Temps réel )";
	
	/**
	 * Our dataset
	 */
	private DynamicTimeSeriesCollection dataset;
	
	/**
	 * The data to add
	 */
	private float[] newData = new float[3];

	/**
	 * Create a graph with a title
	 * 
	 * @param title The title of our graph
	 */
	public GraphTemp(final String title) {
		super(title);
		dataset = new DynamicTimeSeriesCollection(3, 600, new Second());
		dataset.setTimeBase(new Second());
		dataset.addSeries(new float[1], 0, "Température");
		dataset.addSeries(new float[1], 1, "Humidité");
		dataset.addSeries(new float[1], 2, "Consigne");
		JFreeChart chart = createChart(dataset);

		this.add(new ChartPanel(chart), BorderLayout.CENTER);
		this.setDefaultCloseOperation(ApplicationFrame.HIDE_ON_CLOSE);
		this.pack();

	}

	/**
	 * Create a chart from our graph
	 * @param dataset The data to create it from
	 * @return JFreeChart graphic
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

	/**
	 * Update data
	 * 
	 * @param serie The serie
	 * @param value The Value
	 */

	public void update(String serie, float value) {
		
		dataset.advanceTime();

		switch (serie) {
		case "temp":

			newData[0] = value;

			dataset.appendData(newData);
			break;

		case "humi":

			newData[1] = value;

			dataset.appendData(newData);
			break;

		default:
			break;
		}

	}

	/**
	 * Update consigne
	 * @param value new value
	 */
	public void updateConsigne(float value) {
		newData[2] = value;	
	}

}
