package de.germanwarfare.stats.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Player extends BasisEntity {
	@Column
	private String name;
	@Column
	private int givenid;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGivenid() {
		return givenid;
	}

	public void setGivenid(int givenid) {
		this.givenid = givenid;
	}

}
