package projet.frigo;

import java.io.IOException;
import java.util.TooManyListenersException;

import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;

/**
 * Interface to the CAD
 *
 */
public interface ICAD {
	/**
	 * Initialize the CAD
	 * @throws NoSuchPortException
	 * @throws PortInUseException
	 * @throws UnsupportedCommOperationException
	 * @throws IOException
	 * @throws TooManyListenersException
	 */
	void initialize() throws NoSuchPortException, PortInUseException, UnsupportedCommOperationException, IOException, TooManyListenersException;


}
