public class Dealer extends Participant {
	protected Dealer() {
		super("딜러");
	}

	@Override
	public boolean shouldDrawCard() {
		return getScore() <= 16;
	}
}
