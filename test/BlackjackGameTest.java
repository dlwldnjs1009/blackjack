import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import card.Card;
import card.CardNumber;
import card.Suit;
import deck.Deck;
import domain.Player;
import game.BlackjackGame;

class BlackjackGameTest {

	private static class TestDeck extends Deck {
		private final Iterator<Card> cards;

		public TestDeck(List<Card> preCards) {
			this.cards = preCards.iterator();
		}

		@Override
		public Card draw() {
			if (!cards.hasNext()) {
				throw new IllegalStateException("No cards left in test deck.");
			}
			return cards.next();
		}
	}

	@Test
	@DisplayName("딜러가 21 초과하면 플레이어는 무조건 승리")
	void dealerBurst() {
		Player player = new Player("jiwon", 10000, false);
		BlackjackGame game = new BlackjackGame(List.of(player), new TestDeck(List.of(
			// 순서대로 분배: dealer, player, dealer, player, dealer
			new Card(CardNumber.SIX, Suit.HEART),        // dealer 1
			new Card(CardNumber.TEN, Suit.CLUB),         // jiwon 1
			new Card(CardNumber.NINE, Suit.DIAMOND),     // dealer 2
			new Card(CardNumber.SEVEN, Suit.SPADE),      // jiwon 2
			new Card(CardNumber.EIGHT, Suit.CLUB)        // dealer draw → bust
		)));
		game.play();

		assertEquals(10000, game.getProfit(player));
		assertEquals(-10000, game.getProfit(game.getDealer()));
	}

	@Test
	@DisplayName("플레이어가 버스트되면 무조건 패배")
	void playerBurst() {
		Player player = new Player("jiwon", 10000, true);
		BlackjackGame game = new BlackjackGame(List.of(player), new TestDeck(List.of(
				new Card(CardNumber.TEN, Suit.HEART),     // dealer
				new Card(CardNumber.EIGHT, Suit.CLUB),    // jiwon
				new Card(CardNumber.NINE, Suit.SPADE),    // dealer
				new Card(CardNumber.SEVEN, Suit.DIAMOND), // jiwon
				new Card(CardNumber.NINE, Suit.HEART)    // jiwon draw → bust (8+7+9=24)
			)));

		game.play();

		assertEquals(-10000, game.getProfit(player));
		assertEquals(10000, game.getProfit(game.getDealer()));
	}
}
