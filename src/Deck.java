import java.util.Collections;
import java.util.Stack;

public class Deck {
	/**
	 * 카드 더미는 Stack 형태로 구현되며, 카드를 섞은 후 맨 위에서부터 뽑습니다.
	 */
	private final Stack<Card> cards = new Stack<>();

	public Deck() {
		initialize();
		shuffle();
	}

	private void initialize() {
		for (Suit suit : Suit.values()) {
			for (CardNumber cardNumber : CardNumber.values()) {
				cards.push(new Card(cardNumber, suit));
			}
		}
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public Card draw() {
		if (cards.isEmpty()) {
			throw new IllegalStateException("카드가 없습니다.");
		}
		return cards.pop();
	}
}
