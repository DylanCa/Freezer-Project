package projet.frigo;

import java.text.DecimalFormat;

/**
 * A simulator of an Arduino.
 * <p>
 * To use if the Arduino is not connected to the computer
 */
public class Simulation implements ICAD {
    /**
     * A reference to our current model
     */
	private Model model;
    
    /**
     * The formatting parameters for the transfert of our numbers
     */
	private DecimalFormat df = new DecimalFormat("#.##");
    
    /**
     * The current internal temperature of the freezer
     */
	private double temperatureInterieure;
    
    /**
     * The current external temperature of the freezer
     */
	private double temperatureExeterieure;
    
    /**
     * The temperature the user wants
     */
	private double consigne;
    
    /**
     * The current humidity inside the freezer 
     */
	private double humidite;
    
    /**
     * Whether the freezer is open or closed
     */
	private boolean ouvert;

    /**
     * Initialize the simulation on the given model
     * @param model The model to send the data to
     */
	public Simulation(Model model) {

		this.model = model;
		this.temperatureInterieure =20;
		this.temperatureExeterieure = 28;
		this.humidite = 50;

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

			this.consigne = model.getTempVoulueActuelle();
			this.ouvert = model.isOuvert();
			
			if (ouvert == false) {

				// Si la consigne est plus faible que la température du frigo
				if (consigne < temperatureInterieure) {
					temperatureInterieure = temperatureInterieure - 0.2; // On baisse la température de 0.2
				}

				if (consigne - 2 < temperatureInterieure) // Simu de l'inverstion de la plaque a effet peltier
				{
					temperatureInterieure = temperatureInterieure + 0.1; // Sinon elle augmente (principe du tout ou
																			// rien)
				}
			}
			// Si il est ouvert

			else if (ouvert == true) {

				// Si la température extiereure est supérieure

				if (temperatureExeterieure > temperatureInterieure) {
					temperatureInterieure = temperatureInterieure + 0.3; // La température augmente (plus vite que quand
																			// on coupe le Peltier)
				}
			}
			
			model.setTempActuelle(temperatureInterieure);
			model.setHumidActuelle(humidite);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

}
