package projet.frigo;

import java.io.IOException;
import java.util.TooManyListenersException;

import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;

public interface ICAD {

	void initialize() throws NoSuchPortException, PortInUseException, UnsupportedCommOperationException, IOException, TooManyListenersException;


}
