package card;

public enum CardNumber {
	ACE(1, 11), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(
		10), KING(10);

	private final int minValue;
	private final int maxValue;

	CardNumber(int value) {
		this.minValue = value;
		this.maxValue = value;
	}

	CardNumber(int minValue, int maxValue) {
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	public int getMinValue() {
		return minValue;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public boolean isAce() {
		return this == ACE;
	}
}
