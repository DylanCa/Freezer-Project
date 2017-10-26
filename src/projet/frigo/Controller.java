package projet.frigo;

import java.awt.Color;
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
	 * Parameter for the Point de Rosï¿½e
	 */
	private double a = 17.27;
	
	/**
	 * Parameter for the Point de Rosï¿½e
	 */
	private double b = 237.7;
	
	/**
	 * Final result for the Point de Rosï¿½e
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
	 * The hysterisis for the warning for the humidity
	 * <p>
	 * This is the tangent value from which on to give a warning.
	 * <p>
	 * If positive, give warning if the values rises too quickly.
	 * If negative, give warning if the value falls too quickly
	 */
	private double warnHumiHyst = 2.2;
	
	/**
	 * The sensitivity for the warning for the humidity
	 * <p>
	 * This is the number of points the warning generator backtraces for the tangent.
	 * Use this to smooth out read noise.
	 */
	private int warnHumiSens = 10;
	
	/**
	 * If there should be a warning for the temperature
	 * <p>
	 * {@code true=warning ; false=no warning}
	 */
	private boolean warnTempActive = false;
	
	/**
	 * The hysterisis for the warning for the temperature
	 * <p>
	 * This is the tangent value from which on to give a warning.
	 * <p>
	 * If positive, give warning if the values rises too quickly.
	 * If negative, give warning if the value falls too quickly
	 */
	private double warnTempHyst = 2.2;
	
	/**
	 * The sensitivity for the warning for the temperature
	 * <p>
	 * This is the number of points the warning generator backtraces for the tangent.
	 * Use this to smooth out read noise.
	 */
	private int warnTempSens = 10;
	
	/**
	 * If there should be a warning for the humidity
	 * <p>
	 * {@code true=warning ; false=no warning}
	 */
	private boolean warnHumiActive=false;


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
	 * Calculate the Point de Rosï¿½ee
	 * @param serie The serie
	 * @param value The value
	 */
	private void calculRosee(String serie, double value) {

		model.setTempRosee( (a * tempActuelle) / (b + tempActuelle) + Math.log(humiActuelle * 0.01) ) ;
		finalRosee = (b * model.getTempRosee()) / (a - model.getTempRosee());

		view.fieldTempRosee.setText(String.valueOf(df.format(finalRosee)) + "C");

	}
	
	/**
	 * Calculates whether a warning applies for a given series
	 * @param newvalue The last value
	 * @param oldvalue The Nth-last value
	 * @param hysterisis The hysterisis for a given series
	 * @returns boolean {code true=warning applies ; false=no warning needed}
	 */
	private boolean calculateWarning(double newvalue,double oldvalue,double hysterisis) {
		// calcul of the tangent between the two value
		double tangent=newvalue-oldvalue;
		
		// if the hysterisis is positive, throws warning if the tangent is superior to it
		if((hysterisis>0) && (tangent>hysterisis)) {
			return true;
		}
		
		// if the hysterisis is negative, throws warning if the tangent is inferior to it
		if((hysterisis<0) && (tangent<hysterisis)) {
			return true;
		}
		
		// if we arrive here, no warning to throw

		view.lblWarning.setForeground(new Color(0,0,0));
		view.lblWarning.setText("Pimp my Fridge !");
		return false;
	}
	
	/**
	 * Calculates the warnings
	 */
	private void updateWarnings() {
		if(view.getDataset().getNewestIndex()>=warnHumiSens) {
			warnHumiActive=calculateWarning(newData[1],view.getDataset().getYValue(1,-warnHumiSens),warnHumiHyst);
		}
		if(view.getDataset().getNewestIndex()>=warnTempSens) {
			warnTempActive=calculateWarning(newData[0],view.getDataset().getYValue(0,-warnTempSens),warnTempHyst);
		}
		if(warnHumiActive) {

			view.lblWarning.setForeground(new Color(255,0,0));
			view.lblWarning.setText("Attention: Humidité en hausse !");
		}
		if(warnTempActive) {

			view.lblWarning.setForeground(new Color(255,0,0));
			view.lblWarning.setText("Attention: Température en hausse !");
		}
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
		
		updateWarnings();
	}

	/**
	 * Updates the consigne
	 * @param value The new value
	 */
	public void updateConsigne(float value) {
		newData[2] = value;
	}

}
