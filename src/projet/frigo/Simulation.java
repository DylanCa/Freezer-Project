package projet.frigo;

public class Simulation implements ICAD {

	Model model;
	double oldTemperature = 20;
	double oldHumidite = 50;

	public Simulation(Model model) {
		this.model = model;
		this.initialize();
	}

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
