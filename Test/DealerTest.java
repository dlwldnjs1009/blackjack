import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

	@Test
	@DisplayName("딜러는 점수가 16 이하이면 카드를 반드시 한 장 더 뽑아야 한다")
	void dealerMustDrawCardIfScoreIs16OrLower() {
		Dealer dealer = new Dealer();
		dealer.getHand().addCard(new Card(CardNumber.SIX, Suit.HEART));
		dealer.getHand().addCard(new Card(CardNumber.SEVEN, Suit.DIAMOND));
		assertTrue(dealer.shouldDrawCard());
	}

	@Test
	@DisplayName("딜러는 점수가 17 이상이면 카드를 더 뽑지 않아야 한다")
	void dealerMustNotDrawCardIfScoreIs17OrHigher() {
		Dealer dealer = new Dealer();
		dealer.getHand().addCard(new Card(CardNumber.EIGHT, Suit.HEART));
		dealer.getHand().addCard(new Card(CardNumber.NINE, Suit.DIAMOND));
		assertFalse(dealer.shouldDrawCard());
	}

}
