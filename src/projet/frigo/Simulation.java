package projet.frigo;

/**
 * A simulator of an Arduino.
 * <p>
 * To use if the Arduino is not connected to the computer
 */
public class Simulation implements ICAD {

	/**
	 * The Model to update
	 */
	Model model;
	
	/**
	 * A log of the previous temperature
	 */
	double oldTemperature = 20;
	
	/**
	 * A log of the previous humidity
	 */
	double oldHumidite = 50;

	/**
	 * Start a simulation
	 * @param model The model to send the data to
	 */
	public Simulation(Model model) {
		this.model = model;
		this.initialize();
	}

	/**
	 * Initialize the simulation.
	 * <p>
	 * /!\ Warning /!\ : Blocking function
	 */
	public void initialize() {

		while (true) {

			double change = Math.round(Math.random() * 100);
			if (change % 2 == 0) {
				change = change * (-1);
			}
			if (oldTemperature + Math.round(change / 25) > 0 & oldTemperature + Math.round(change / 25) < 50) {
				this.oldTemperature = this.oldTemperature + Math.round(change / 25);
				model.setTempActuelle(oldTemperature);
			}
			if (oldHumidite + Math.round(change / 25) > 0 & oldHumidite + Math.round(change / 25) < 100) {
				this.oldHumidite = this.oldHumidite + Math.round(change / 25);
				model.setHumidActuelle(oldHumidite);
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}


}
