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

/**
 * The class used to connect to the Arduino
 * <p>
 * This class is used to connect to the Arduino. 
 * It implements SerialPortEventListener to register as a dispatcher,
 *  and then on each serialEvent it dispatch the data and sends it to the Model.
 *
 */
public class Arduino implements SerialPortEventListener, ICAD {
    /** 
     * The port we're going to use. 
     */
	SerialPort serialPort;
	
    /** 
     * Port names to test for to connect to the Arduino under different OS
     */
	private static final String PORT_NAMES[] = { "COM1", "COM2", "COM3", "COM4", "COM5", };

    /**
     * A BufferedReader which will be fed by a InputStreamReader
     * converting the bytes into characters and
     * making the displayed results codepage independent
     */
	private BufferedReader input;
	
	/** 
	 * The output stream to the port
	 */
	private OutputStream output;	
	
	/**
	 *  Milliseconds to block while waiting for port open 
	 */
	private static final int TIME_OUT = 2000;
	
	/** Default bits per second for COM port.
     * Should be the same as under the Arduino 
     */
	private static final int DATA_RATE = 9600;
	
    /**
     * A reference to the Model to send data to
     */
	private Model model;

	/**
	 * Initialize the Arduino with the given Model
	 * @param model The Model to connect to
	 * @throws NoSuchPortException
	 * @throws PortInUseException
	 * @throws UnsupportedCommOperationException
	 * @throws IOException
	 * @throws TooManyListenersException
	 */
	public Arduino(Model model) throws NoSuchPortException, PortInUseException, UnsupportedCommOperationException,
			IOException, TooManyListenersException {
		this.model = model;
	}

    /**
     * Search for the port, connect to it, and add Event Listeners for the dispatch loop
     */
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
     * Handle an event on the serial port. Read the data and interpret it.
     * <p>
     * The Arduino sends the data under the following format:
     * <p>
     * {@code < Temperature > ; < Humiditï¿½ > ; < Point de Rosï¿½e > \n}
     * <p>
     * This function read that from the event, dispatch it, and sends it to the Model.
     * 
     * @param oEvent Event data. See gnu.io.SerialPortEvent
     */
	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {

				// Boucle qui rï¿½cupï¿½re les donnï¿½es depuis l'arduino
				String inputLine = input.readLine();
				String string = inputLine;
				String[] parts = string.split(";");
				double temp1 = Double.parseDouble(parts[0]); // 27ï¿½C
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

				// On envoie les donnï¿½es au Model ï¿½ chaque passage de la boucle

			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
		// Ignore all the other eventTypes, but you should consider the other ones.
	}

}
