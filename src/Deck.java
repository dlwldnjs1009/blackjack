import java.util.Collections;
import java.util.Stack;

public class Deck {
	/**
	 * todo: 가독성 좋게 문장 가다듬기
	 * 보통 카드 더미를 생각하면 카드를 섞고 앞면(카드번호, 무늬)이 보이는 면이 아래에 깔리게 뒤집고
	 * 맨 밑에 깔려 있는 카드부터 뽑히게 된다. Stack 형태라 생각했다.
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
