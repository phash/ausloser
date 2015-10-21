package de.germanwarfare.stats;

import java.util.ArrayList;

import de.germanwarfare.stats.entity.Vehicle;

public class VehicleService {

	private ArrayList<Vehicle> vehicles;

	public VehicleService() {
		super();
		vehicles = new ArrayList<>();
	}

	private Vehicle createVehicleByName(String string) {
		Vehicle neu = new Vehicle(string);
		vehicles.add(neu);
		System.out.println("new level: " + string);
		return neu;
	}

	public Vehicle getVehicleByName(String string) {
		for (Vehicle e : vehicles) {
			if (e.getName().equals(string)) {
				return e;
			}
		}
		return createVehicleByName(string);
	}

}
