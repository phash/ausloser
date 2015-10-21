package de.germanwarfare.stats.entity;

public class Vehicle {

	private String name;
	private Tier tier;
	private VehicleType type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Tier getTier() {
		return tier;
	}

	public void setTier(Tier tier) {
		this.tier = tier;
	}

	public VehicleType getType() {
		return type;
	}

	public void setType(VehicleType type) {
		this.type = type;
	}

	public Vehicle(String name) {
		super();
		this.name = name;
	}

}
