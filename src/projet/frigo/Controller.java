package projet.frigo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Controller implements IModelObserver {

	private double tempActuelle;
	private double humiActuelle;
	private double a = 17.27;
	private double b = 237.7;
	private double tempRosee;
	private double finalRosee;
	private float[] newData = new float[3];

	private Model model;
	private View view;
	private DecimalFormat df = new DecimalFormat("#.##");

	public Controller(Model model) {

		this.model = model;
		model.controller = this;

		this.view = new View();

		view.setVisible(true);

		this.initializeButtons();
		model.addObserver(this);

	}

	private void initializeButtons() {

		view.buttonConsignePlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int value = (int) (model.getTempVoulueActuelle() + 1);
				model.setTempVoulueActuelle(value);

				view.labelConsigne.setText(String.valueOf(value));

				updateConsigne((float) value);
			}
		});

		view.buttonConsigneMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int value = (int) (model.getTempVoulueActuelle() - 1);
				model.setTempVoulueActuelle(value);

				view.labelConsigne.setText(String.valueOf(value));

				updateConsigne((float) value);
			}

		});
	}

	@Override
	public void tempActuelleNotify(double value) {

		this.tempActuelle = value;

		update("temp", (float) value);
		this.calculRosee("temp", value);

		view.fieldTemperature.setText(String.valueOf(value) + "°C");

	}

	@Override
	public void humidActuelleNotify(double value) {

		this.humiActuelle = value;

		update("humi", (float) value);
		this.calculRosee("humi", value);

		view.fieldHumidity.setText(String.valueOf(value) + "%");

	}

	private void calculRosee(String serie, double value) {

		tempRosee = (a * tempActuelle) / (b + tempActuelle) + Math.log(humiActuelle * 0.01);
		finalRosee = (b * tempRosee) / (a - tempRosee);

		view.fieldTempRosee.setText(String.valueOf(df.format(finalRosee)) + "°C");

	}

	public void update(String serie, float value) {

		view.getDataset().advanceTime();

		switch (serie) {
		case "temp":

			newData[0] = value;

			view.getDataset().appendData(newData);
			break;

		case "humi":

			newData[1] = value;

			view.getDataset().appendData(newData);
			break;

		default:
			break;
		}

	}

	public void updateConsigne(float value) {
		newData[2] = value;
	}

}
