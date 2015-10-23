package de.germanwarfare.stats.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Manuel Rödig
 */
@MappedSuperclass
public abstract class BasisEntity implements Serializable { // NOPMD by msir

	@Id
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
