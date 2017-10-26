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
	 * Parameter for the Point de Ros�e
	 */
	private double a = 17.27;
	
	/**
	 * Parameter for the Point de Ros�e
	 */
	private double b = 237.7;
	
	/**
	 * Final result for the Point de Ros�e
	 */
	private double finalRosee;
	
	/**
	 * Variable to check the warnings
	 */
	
	/**
	 *  Captures the data to update the charts
	 */
	private float[] newData = new float[3];

	/**
	 * A reference to our current Model
	 */
	private Model model;
	
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

				updateConsigne((float) value);
			}
		});

		view.buttonConsigneMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int value = (int) (model.getTempVoulueActuelle() - 1);
				model.setTempVoulueActuelle(value);

				view.labelConsigne.setText(String.valueOf(value));

				updateConsigne((float) value);
			}

		});
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void tempActuelleNotify(double value) {
		
		if(value > model.getOldTemp()) {
			System.out.println(value + " > " + model.getOldTemp() );
		} else if ( value < model.getOldTemp()) {
			System.out.println(value + " < " + model.getOldTemp() );
		} 

		this.tempActuelle = value;

		update("temp", (float) value);
		this.calculRosee("temp", value);

		view.fieldTemperature.setText(String.valueOf(value) + "C");

	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void humidActuelleNotify(double value) {

		this.humiActuelle = value;

		update("humi", (float) value);
		this.calculRosee("humi", value);

		view.fieldHumidity.setText(String.valueOf(value) + "%");

	}

	/**
	 * Calculate the Point de Ros�ee
	 * @param serie The serie
	 * @param value The value
	 */
	private void calculRosee(String serie, double value) {

		model.setTempRosee( (a * tempActuelle) / (b + tempActuelle) + Math.log(humiActuelle * 0.01) ) ;
		finalRosee = (b * model.getTempRosee()) / (a - model.getTempRosee());

		view.fieldTempRosee.setText(String.valueOf(df.format(finalRosee)) + "C");

	}

	/**
	 * Updates the controller
	 * @param serie The class of value to change in the graph
	 * @param value The new value in the graph
	 */
	public void update(String serie, float value) {

		view.getDataset().advanceTime();

		switch (serie) {
		case "temp":

			newData[0] = value;

			view.getDataset().appendData(newData);
			break;

		case "humi":

			newData[1] = value;

			view.getDataset().appendData(newData);
			break;

		default:
			break;
		}
	}

	/**
	 * Updates the consigne
	 * @param value The new value
	 */
	public void updateConsigne(float value) {
		newData[2] = value;
	}

}
