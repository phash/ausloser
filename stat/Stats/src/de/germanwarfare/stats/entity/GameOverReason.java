package de.germanwarfare.stats.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class GameOverReason extends BasisEntity {
	@Column
	private String reason;

	public GameOverReason(String reason) {
		super();
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
