package projet.frigo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

import java.util.Enumeration;
import java.util.TooManyListenersException;

public class Arduino implements SerialPortEventListener, ICAD {
	SerialPort serialPort;
	private static final String PORT_NAMES[] = { "COM1", "COM2", "COM3", "COM4", "COM5", };

	/**
	 * A BufferedReader which will be fed by a InputStreamReader converting the
	 * bytes into characters making the displayed results codepage independent
	 */
	private BufferedReader input;
	/** The output stream to the port */
	private OutputStream output;
	/** Milliseconds to block while waiting for port open */
	private static final int TIME_OUT = 2000;
	/** Default bits per second for COM port. */
	private static final int DATA_RATE = 9600;

	private Model model;

	public Arduino(Model model) throws NoSuchPortException, PortInUseException, UnsupportedCommOperationException,
			IOException, TooManyListenersException {
		this.model = model;
	}

	public void initialize() throws NoSuchPortException, PortInUseException, UnsupportedCommOperationException,
			IOException, TooManyListenersException {

		CommPortIdentifier portId = null;
		Enumeration<?> portEnum = CommPortIdentifier.getPortIdentifiers();

		// First, Find an instance of serial port as set in PORT_NAMES.
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}

		// open serial port, and use class name for the appName.
		serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);

		// set port parameters
		serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

		// open the streams
		input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
		output = serialPort.getOutputStream();

		// add event listeners
		serialPort.addEventListener(this);
		serialPort.notifyOnDataAvailable(true);

	}

	/**
	 * This should be called when you stop using the port. This will prevent port
	 * locking on platforms like Linux.
	 */
	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	/**
	 * Handle an event on the serial port. Read the data and print it.
	 */
	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {

				// Boucle qui récupère les données depuis l'arduino
				String inputLine = input.readLine();
				String string = inputLine;
				String[] parts = string.split(";");
				double temp1 = Double.parseDouble(parts[0]); // 27°C
				double humi1 = Double.parseDouble(parts[1]); // 53 %
				System.out.print("Temperature :" + temp1);
				System.out.println(" / Humidité :" + humi1);

				model.setTempActuelle(temp1);
				model.setHumidActuelle(humi1);

				if (model.getTempActuelle() > model.getTempVoulueActuelle()) {
					output.write(255);
				} else if (model.getTempActuelle() < model.getTempVoulueActuelle()) {
					output.write(0);
				}

				// On envoie les données au Model à chaque passage de la boucle

			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
		// Ignore all the other eventTypes, but you should consider the other ones.
	}

}
