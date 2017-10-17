package projet.frigo;

import java.util.ArrayList;
import java.util.List;

public class Model {

	private double tempActuelle = 0;
	private double humidActuelle = 0;
	private double tempVoulue = 0;
	private List<IModelObserver> listModel = new ArrayList<IModelObserver>();
	private Controller controller;

	public Model() {
		this.controller = new Controller(this);
		
	}

	public double getTempActuelle() {
		return tempActuelle;
	}

	public void setTempActuelle(double tempActuelle) {
		this.tempActuelle = tempActuelle;
		notifyTempActuelleChange();
	}

	public double getHumidActuelle() {
		return humidActuelle;
	}

	public void setHumidActuelle(double humidActuelle) {
		this.humidActuelle = humidActuelle;
		notifyHumidActuelleChange();
	}

	public double getTempVoulueActuelle() {
		return tempVoulue;
	}

	public void setTempVoulueActuelle(double tempVoulueActuelle) {
		this.tempVoulue = tempVoulueActuelle;
		notifyTempVoulueChange();
	}

	public void addObserver(IModelObserver observer) {
		listModel.add(observer);
	}

	public void removeObserver(IModelObserver observer) {
		listModel.remove(observer);
	}

	private void notifyTempActuelleChange() {
		for (IModelObserver observer : listModel) {
			observer.tempActuelleNotify(this.tempActuelle);
		}
	}

	private void notifyHumidActuelleChange() {
		for (IModelObserver observer : listModel) {
			observer.humidActuelleNotify(this.humidActuelle);
		}
	}

	private void notifyTempVoulueChange() {
		for (IModelObserver observer : listModel) {
			observer.tempVoulueNotify(this.tempVoulue);
		}
	}

}
