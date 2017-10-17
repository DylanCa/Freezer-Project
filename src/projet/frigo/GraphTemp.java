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
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String TITLE = "Température & Humidité ( Temps réel )";
	private DynamicTimeSeriesCollection dataset;
	private float[] newData = new float[2];

	/**
	 * Constructor
	 * 
	 * @param title
	 */
	public GraphTemp(final String title) {
		super(title);
		dataset = new DynamicTimeSeriesCollection(2, 60, new Second());
		dataset.setTimeBase(new Second());
		dataset.addSeries(new float[1], 0, "Température");
		dataset.addSeries(new float[1], 1, "Humidité");
		JFreeChart chart = createChart(dataset);

		this.add(new ChartPanel(chart), BorderLayout.CENTER);
		this.setDefaultCloseOperation(ApplicationFrame.HIDE_ON_CLOSE);
		this.pack();

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

	/**
	 * Update data
	 * 
	 * @param serie
	 * @param value
	 */

	public void update(String serie, float value) {

		switch (serie) {
		case "temp":

			newData[0] = value;

			dataset.advanceTime();
			dataset.appendData(newData);
			break;

		case "humi":

			newData[1] = value;

			dataset.advanceTime();
			dataset.appendData(newData);
			break;
			
		default:
			break;
		}

	}

}
