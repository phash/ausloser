package de.germanwarfare.stats.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * die Map
 *
 * @author msir
 *
 */
@Entity
public class Level extends BasisEntity {
	@Column
	private String name;

	public Level(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
