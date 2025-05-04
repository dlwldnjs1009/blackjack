public class Player extends Participant{
	private final int betMoney;
	private boolean shouldDraw;

	public Player(String name, int betMoney, boolean shouldDraw) {
		super(name);
		this.betMoney = betMoney;
		this.shouldDraw = shouldDraw;
	}

	@Override
	public boolean shouldDrawCard() {
		return shouldDraw;
	}

	public int getBetMoney() {
		return betMoney;
	}

	public void stopDrawing() {
		this.shouldDraw = false;
	}
}
