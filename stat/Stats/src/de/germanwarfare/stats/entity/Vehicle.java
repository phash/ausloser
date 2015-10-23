package de.germanwarfare.stats.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Vehicle {
	@Column
	private String name;
	@Column
	private int tier;
	@OneToMany(targetEntity = VehicleType.class)
	private VehicleType type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTier() {
		return tier;
	}

	public void setTier(int tier) {
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
