package projet.frigo;

import java.util.ArrayList;
import java.util.List;

/**
 * This contains the representation of all Data
 */
public class Model {

	
	/**
	 * The current Temperature
	 */
	private double tempActuelle = 0;

	/**
	 * The current Humidity
	 */
	private double humidActuelle = 0;

	/**
	 * The ordered Temperature
	 */
	private double tempVoulue = 17;

	/**
	 * A list of all the observators of this model
	 */
	private List<IModelObserver> listModel = new ArrayList<IModelObserver>();
	
	/**
	 * The controller responsable to send the Data to the View
	 */
	Controller controller;


	/**
	 * Initialize the model
	 */
	public Model() {
	}

	/**
	 * Gets the current temperature
	 * @return the current temperature
	 */
	public double getTempActuelle() {
		return tempActuelle;
	}

	/**
	 * Sets the current temperature
	 * @param tempActuelle the new temperature
	 */
	public void setTempActuelle(double tempActuelle) {
		this.tempActuelle = tempActuelle;
		notifyTempActuelleChange();
	}

	/**
	 * Gets the current humidity
	 * @return the current humidity
	 */
	public double getHumidActuelle() {
		return humidActuelle;
	}

	/**
	 * Sets the new humidity
	 * @param humidActuelle the new temperature
	 */
	public void setHumidActuelle(double humidActuelle) {
		this.humidActuelle = humidActuelle;
		notifyHumidActuelleChange();
	}

	/**
	 * Gets the ordered temperature
	 * @return the ordered temperature
	 */
	public double getTempVoulueActuelle() {
		return tempVoulue;
	}

	/**
	 * Set the ordered temperature
	 * @param tempVoulueActuelle The new ordered temperature
	 */
	public void setTempVoulueActuelle(double tempVoulueActuelle) {
		this.tempVoulue = tempVoulueActuelle;
	}

	/**
	 * Add an observer to this model
	 * @param observer The observer that is going to observe the model
	 */
	public void addObserver(IModelObserver observer) {
		listModel.add(observer);
	}

	/**
	 * Remove an observer from this model
	 * @param observer The observer to remove
	 */
	public void removeObserver(IModelObserver observer) {
		if(listModel.contains(observer)) {
			listModel.remove(observer);
		}
	}

	/**
	 * Notify of a change in the current temperature
	 */
	private void notifyTempActuelleChange() {
		for (IModelObserver observer : listModel) {
			observer.tempActuelleNotify(this.tempActuelle);
		}
	}

	/**
	 * Notify of a change in the current humidity
	 */
	private void notifyHumidActuelleChange() {
		for (IModelObserver observer : listModel) {
			observer.humidActuelleNotify(this.humidActuelle);
		}
	}



}
