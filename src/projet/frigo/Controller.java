package projet.frigo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
/**
 * This class sends the data from the Model to the View
 * <p>
 * It observes the Model using its IModelObserver implementation for any changes. Then, it updates the View
 */
public class Controller implements IModelObserver {
	
	/**
	 * The current Temperature
	 */
	private double tempActuelle;

	/**
	 * The current Humidity
	 */
	private double humiActuelle;
	
	/**
	 * Parameter for the Point de Rosée
	 */
	private double a = 17.27;
	
	/**
	 * Parameter for the Point de Rosée
	 */
	private double b = 237.7;
	
	/**
	 * Température for the Point de Rosée
	 */
	private double tempRosee;
	
	/**
	 * Final result for the Point de Rosée
	 */
	private double finalRosee;

	/**
	 * A reference to our current Model
	 */
	private Model model;
	
	/**
	 * The graph to model our changes on
	 */
	private GraphTemp chart;
	
	/**
	 * The View to use for the Interface
	 */
	private View view;
	
	/**
	 * The format used for the transfert of numbers
	 */
	private DecimalFormat df = new DecimalFormat("#.##");

	/**
	 * Instanciate the controller
	 * @param model The model to control
	 */
	public Controller(Model model) {

		// save the model
		this.model = model;
		
		// register to the model
		model.controller = this;

		// instanciate a new view for our output
		this.view = new View();

		// instanciate a new grafic, to follow the temperature
		chart = new GraphTemp("Graphique");

		// makes the view render
		view.setVisible(true);

		this.initializeButtons();
		
		// register to the model for changes
		model.addObserver(this);

	}

	/**
	 * Register as an eventlistener to the buttons of our view
	 */
	private void initializeButtons() {

		view.buttonConsignePlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int value = (int) (model.getTempVoulueActuelle() + 1);
				model.setTempVoulueActuelle(value);
				
				view.labelConsigne.setText(String.valueOf(value));

				chart.updateConsigne((float) value);
			}
		});

		view.buttonConsigneMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int value = (int) (model.getTempVoulueActuelle() - 1);
				model.setTempVoulueActuelle(value);

				view.labelConsigne.setText(String.valueOf(value));

				chart.updateConsigne((float) value);
			}

		});

		view.btnAfficherGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				chart.setVisible(true);
			}
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void tempActuelleNotify(double value) {

		this.tempActuelle = value;

		chart.update("temp", (float) value);
		this.calculRosee("temp", value);

		view.fieldTemperature.setText(String.valueOf(value) + "°C");

	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void humidActuelleNotify(double value) {

		this.humiActuelle = value;

		chart.update("humi", (float) value);
		this.calculRosee("humi", value);

		view.fieldHumidity.setText(String.valueOf(value) + "%");

	}

	/**
	 * Calculate the Point de Roséee
	 * @param serie The serie
	 * @param value The value
	 */
	private void calculRosee(String serie, double value) {

		tempRosee = (a * tempActuelle) / (b + tempActuelle) + Math.log(humiActuelle * 0.01);
		finalRosee = (b * tempRosee) / (a - tempRosee);

		view.fieldTempRosee.setText(String.valueOf(df.format(finalRosee)) + "°C");

	}


}
