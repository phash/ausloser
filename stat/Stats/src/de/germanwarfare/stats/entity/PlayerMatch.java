package de.germanwarfare.stats.entity;

public class PlayerMatch {

	private Player player;

	private int damagedealt;
	private int killsenemies;
	private int spottedenemies;
	private int teamid;
	private int spottingdamage;
	private int assists;
	private int xpearned;
	private Vehicle vehicle;
	private Player killedby;
	private Killreason killedreason;
	private boolean survived;
	private boolean isinlocalplayersplatoon;
	private Battalion battalion;

	public PlayerMatch(Player player) {
		super();
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}

	public int getDamagedealt() {
		return damagedealt;
	}

	public void setDamagedealt(int damagedealt) {
		this.damagedealt = damagedealt;
	}

	public int getKillsenemies() {
		return killsenemies;
	}

	public void setKillsenemies(int killsenemies) {
		this.killsenemies = killsenemies;
	}

	public int getSpottedenemies() {
		return spottedenemies;
	}

	public void setSpottedenemies(int spottedenemies) {
		this.spottedenemies = spottedenemies;
	}

	public int getTeamid() {
		return teamid;
	}

	public void setTeamid(int teamid) {
		this.teamid = teamid;
	}

	public int getSpottingdamage() {
		return spottingdamage;
	}

	public void setSpottingdamage(int spottingdamage) {
		this.spottingdamage = spottingdamage;
	}

	public int getAssists() {
		return assists;
	}

	public void setAssists(int assists) {
		this.assists = assists;
	}

	public int getXpearned() {
		return xpearned;
	}

	public void setXpearned(int xpearned) {
		this.xpearned = xpearned;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Player getKilledby() {
		return killedby;
	}

	public void setKilledby(Player killedby) {
		this.killedby = killedby;
	}

	public Killreason getKilledreason() {
		return killedreason;
	}

	public void setKilledreason(Killreason killedreason) {
		this.killedreason = killedreason;
	}

	public boolean isSurvived() {
		return survived;
	}

	public void setSurvived(boolean survived) {
		this.survived = survived;
	}

	public boolean isIsinlocalplayersplatoon() {
		return isinlocalplayersplatoon;
	}

	public void setIsinlocalplayersplatoon(boolean isinlocalplayersplatoon) {
		this.isinlocalplayersplatoon = isinlocalplayersplatoon;
	}

	public Battalion getBattalion() {
		return battalion;
	}

	public void setBattalion(Battalion battalion) {
		this.battalion = battalion;
	}

}
