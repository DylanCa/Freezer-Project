package projet.frigo;

public interface IModelObserver {
	
	void tempActuelleNotify(double value);
	void humidActuelleNotify(double value);
	void tempVoulueNotify(double value);
}
