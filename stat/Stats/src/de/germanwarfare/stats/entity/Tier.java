package de.germanwarfare.stats.entity;

public enum Tier {
	ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10);
	private int level;

	private Tier(int level) {
		this.level = level;
	}

	public static Tier getTierForInt(String i) {
		for (Tier e : values()) {
			if (e.level == Integer.valueOf(i)) {
				return e;
			}
		}
		return null;
	}

}
