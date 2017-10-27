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
     * The previous Temperature
     */
	private double oldTemp = 0;
	
    /**
     * Whether the freezer is open or closed
     */
	private boolean ouvert = false;

	/**
	 * The current Humidity
	 */
	private double humidActuelle = 0;

	/**
	 * The ordered Temperature
	 */
	private double tempVoulue = 17;
	
	/**
	 * Temperature for the Point de Ros�e
	 */
	private double tempRosee;

    /**
     * Gets the temperature for the Point de Ros�e
     * @return The current temperature for the Point de Ros�e
     */
	public double getTempRosee() {
		return tempRosee;
	}

    /**
     * Sets the temperature for the Point de Ros�e
     * @param tempRosee The new temperature for the Point de Ros�e
     */
	public void setTempRosee(double tempRosee) {
		this.tempRosee = tempRosee;
	}

    /**
     * Gets the temperature the user wants
     * @returns The current wanted temperature
     */
	public double getTempVoulue() {
		return tempVoulue;
	}

    /**
     * Sets the temperature the user wants
     * @param tempVoulu The new wanted temperature
     */
	public void setTempVoulue(double tempVoulue) {
		this.tempVoulue = tempVoulue;
	}

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
		this.oldTemp = this.tempActuelle;
		this.tempActuelle = tempActuelle;
		notifyTempActuelleChange();
	}

	public double getOldTemp() {
		return oldTemp;
	}

	public void setOldTemp(double oldTemp) {
		this.oldTemp = oldTemp;
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

    /**
     * Gets whether the freezer is open
     * @return whether the freezer is open
     */
	public boolean isOuvert() {
		return ouvert;
	}

    /**
     * Sets whether the freezer is open
     * @param ouvert whether the freezer is open
     */
	public void setOuvert(boolean ouvert) {
		this.ouvert = ouvert;
	}



}
