package de.germanwarfare.stats;

import java.util.ArrayList;

import de.germanwarfare.stats.entity.GameOverReason;

public class GameOverReasonService {

	private ArrayList<GameOverReason> reasons;

	public GameOverReasonService() {
		super();
		reasons = new ArrayList<>();
	}

	private GameOverReason createReasonByName(String string) {
		GameOverReason neu = new GameOverReason(string);
		reasons.add(neu);
		System.out.println("new reason: " + string);
		return neu;
	}

	public GameOverReason getReasonByName(String string) {
		for (GameOverReason level : reasons) {
			if (level.getReason().equals(string)) {
				return level;
			}
		}
		return createReasonByName(string);
	}

}
