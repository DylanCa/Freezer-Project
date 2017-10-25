package projet.frigo;

/**
 * Interface to a observer for a Model
 *
 */
public interface IModelObserver {
	/**
	 * Notify of a change in the current temperature
	 * @param value The new value
	 */
	void tempActuelleNotify(double value);
	
	/**
	 * Notify of a change in the current humidity
	 * @param value The new value
	 */
	void humidActuelleNotify(double value);
}
