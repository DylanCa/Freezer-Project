package projet.frigo;

/**
 * The main application
 *
 */
public class Main {
	
	/**
	 * Start the application
	 * @param args The command-line argument; not used
	 */
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
