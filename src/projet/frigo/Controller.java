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
	private Model model;
	private GraphTemp chart;
	private View view;
	private DecimalFormat df = new DecimalFormat("#.##");

	public Controller(Model model) {

		this.model = model;
		this.view = new View(this);

		chart = new GraphTemp("Graphique");

		view.setVisible(true);

		this.initializeButtons();
		model.addObserver(this);

	}

	private void initializeButtons() {

		view.buttonConsignePlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int value = Integer.parseInt(view.labelConsigne.getText()) + 1;
				view.labelConsigne.setText(String.valueOf(value));
			}
		});

		view.buttonConsigneMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int value = Integer.parseInt(view.labelConsigne.getText()) - 1;
				view.labelConsigne.setText(String.valueOf(value));
			}
		});

		view.btnAfficherGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				chart.setVisible(true);
			}
		});
	}

	@Override
	public void tempActuelleNotify(double value) {

		this.tempActuelle = value;

		chart.update("temp", (float) value);
		this.calculRosee("temp", value);

		view.fieldTemperature.setText(String.valueOf(value) + "°C");

	}

	@Override
	public void humidActuelleNotify(double value) {

		this.humiActuelle = value;

		chart.update("humi", (float) value);
		this.calculRosee("humi", value);

		view.fieldHumidity.setText(String.valueOf(value) + "%");

	}

	@Override
	public void tempVoulueNotify(double value) {
		// TODO Dev fonction qui baisse la température sur le frigo ( action sur plaque Peltier )

	}

	private void calculRosee(String serie, double value) {

		tempRosee = (a * tempActuelle) / (b + tempActuelle) + Math.log(humiActuelle * 0.01);
		finalRosee = (b * tempRosee)  /  (a - tempRosee);

		view.fieldTempRosee.setText(String.valueOf(df.format(finalRosee)) + "°C");

	}

}
