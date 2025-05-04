package domain;

import card.Card;

public abstract class Participant {
	protected String name;
	protected Hand hand = new Hand();

	public Participant(String name) {
		this.name = name;
	}

	public void draw(Card card) {
		hand.addCard(card);
	}

	public int getScore() {
		return hand.getScore();
	}

	public boolean isBurst() {
		return hand.isBurst();
	}

	public boolean isBlackjack() {
		return hand.isBlackjack();
	}

	public Hand getHand() {
		return hand;
	}

	public String getName() {
		return name;
	}

	public abstract boolean shouldDrawCard();
}
