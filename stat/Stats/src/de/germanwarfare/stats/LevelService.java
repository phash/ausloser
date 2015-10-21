package de.germanwarfare.stats;

import java.util.ArrayList;

import de.germanwarfare.stats.entity.Level;

public class LevelService {

	private ArrayList<Level> levels;

	public LevelService() {
		super();
		levels = new ArrayList<>();
	}

	private Level createLevelByName(String string) {
		Level neu = new Level(string);
		levels.add(neu);
		System.out.println("new level: " + string);
		return neu;
	}

	public Level getLevelByName(String string) {
		for (Level level : levels) {
			if (level.getName().equals(string)) {
				return level;
			}
		}
		return createLevelByName(string);
	}

}
