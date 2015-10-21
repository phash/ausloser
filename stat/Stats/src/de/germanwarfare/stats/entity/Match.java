package de.germanwarfare.stats.entity;

import java.util.Date;
import java.util.List;

public class Match {
	private List<PlayerMatch> players;
	private MatchType matchType;
	private Level level;
	private String matchId;

	private int wincondition;
	private GameOverReason gameOverReason;
	private Tier matchTier;
	private Date timestamp;
	private int duration;

	public List<PlayerMatch> getPlayers() {
		return players;
	}

	public void setPlayers(List<PlayerMatch> players) {
		this.players = players;
	}

	public MatchType getMatchType() {
		return matchType;
	}

	public void setMatchType(MatchType matchType) {
		this.matchType = matchType;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public int getWincondition() {
		return wincondition;
	}

	public void setWincondition(int wincondition) {
		this.wincondition = wincondition;
	}

	public GameOverReason getGameOverReason() {
		return gameOverReason;
	}

	public void setGameOverReason(GameOverReason gameOverReason) {
		this.gameOverReason = gameOverReason;
	}

	public Tier getMatchTier() {
		return matchTier;
	}

	public void setMatchTier(Tier matchTier) {
		this.matchTier = matchTier;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMatchId() {
		return matchId;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	public Match(String matchId) {
		super();
		System.out.println(matchId);
		this.matchId = matchId;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

}
