package domain;

public class Dealer extends Participant {
	public Dealer() {
		super("딜러");
	}

	@Override
	public boolean shouldDrawCard() {
		return getScore() <= 16;
	}
}
