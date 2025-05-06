package card;

public class Card {
	private final CardNumber number;
	private final Suit suit;

	public Card(CardNumber number, Suit suit) {
		this.number = number;
		this.suit = suit;
	}

	public CardNumber getNumber() {
		return number;
	}

	public Suit getSuit() {
		return suit;
	}

	public int getMinValue() {
		return number.getMinValue();
	}

	public boolean isAce() {
		return number.isAce();
	}

	@Override
	public String toString() {
		return number.name()+ "(" + suit.name() + ")";
	}
}
