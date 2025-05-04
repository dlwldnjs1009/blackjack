import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

	@Test
	@DisplayName("52장의 카드 생성")
	void create52Cards() {
		Deck deck = new Deck();
		Set<Card> cards = new HashSet<>();

		for (int i = 0; i < 52; i++) {
			cards.add(deck.draw());
		}

		assertEquals(52, cards.size());
	}

	@Test
	@DisplayName("draw()를 52번 호출한 이후엔 예외가 발생한다")
	void drawMoreThan52ThrowsException() {
		Deck deck = new Deck();
		for (int i = 0; i < 52; i++) {
			deck.draw();
		}

		assertThrows(IllegalStateException.class, deck::draw);
	}

}
