package projet.frigo;

public class Main {
	public static void main(String[] args) {

		ICAD icad;

		Model model = new Model();

		 new Controller(model);

		try {

			icad = new Arduino(model);

			icad.initialize();

		} catch (Exception e) {

			System.out.println("Could not find COM port, starting simulation ...");
			icad = new Simulation(model);

		}

	}
}
