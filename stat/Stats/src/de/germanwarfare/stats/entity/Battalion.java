package de.germanwarfare.stats.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Battalion extends BasisEntity {
	@Column
	private String name;
	@Column
	private String shortcut;
	private List<Player> players;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortcut() {
		return shortcut;
	}

	public void setShortcut(String shortcut) {
		this.shortcut = shortcut;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

}
