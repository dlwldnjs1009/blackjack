import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {
	@Test
	void calculateHandScoreWithoutAce() {
		Hand hand = new Hand();
		hand.addCard(new Card(CardNumber.EIGHT, Suit.HEART));
		hand.addCard(new Card(CardNumber.SEVEN, Suit.DIAMOND));
		assertEquals(15, hand.getScore());
	}


	@Test
	@DisplayName("Ace가 11로 계산될 때")
	void aceIsCalculatedAs11() {
		Hand hand = new Hand();
		hand.addCard(new Card(CardNumber.ACE, Suit.HEART));
		hand.addCard(new Card(CardNumber.SEVEN, Suit.DIAMOND));
		assertEquals(18, hand.getScore());
	}

	@Test
	@DisplayName("Ace가 1로 계산될 때")
	void aceIsCalculatedAs1() {
		Hand hand = new Hand();
		hand.addCard(new Card(CardNumber.SEVEN, Suit.DIAMOND));
		hand.addCard(new Card(CardNumber.EIGHT, Suit.SPADE));
		// 이때는 Ace를 11로 계산하는 것보다 1로 계산하는 것이 더 유리하다.
		hand.addCard(new Card(CardNumber.ACE, Suit.HEART));
		assertEquals(16, hand.getScore());
	}

	@Test
	@DisplayName("여러 Ace가 있을 때 첫번째만 11로 나머지는 전부 1로 계산한다")
	void multipleAcesOneCountsAs11() {
		Hand hand = new Hand();
		hand.addCard(new Card(CardNumber.ACE, Suit.HEART)); // 11
		hand.addCard(new Card(CardNumber.ACE, Suit.DIAMOND)); // 1
		assertEquals(12, hand.getScore()); // 12
	}

	@Test
	@DisplayName("여러 Ace가 있을 때 첫번째 Ace는 11, 나머지는 전부 1로 계산해야 하는 경우")
	void multipleAcesAllCountAs1IfBurst21() {
		Hand hand = new Hand();
		hand.addCard(new Card(CardNumber.ACE, Suit.HEART));     // 1 or 11
		hand.addCard(new Card(CardNumber.ACE, Suit.SPADE));     // 1
		hand.addCard(new Card(CardNumber.NINE, Suit.DIAMOND));  // 9
		assertEquals(21, hand.getScore());                      // 11 + 1 + 9
	}

	@Test
	@DisplayName("Ace 두 장 + 10이면 두 Ace 모두 1로 계산")
	void multipleAcesCount() {
		Hand hand = new Hand();
		hand.addCard(new Card(CardNumber.ACE, Suit.HEART)); // 1
		hand.addCard(new Card(CardNumber.ACE, Suit.CLUB));  // 1
		hand.addCard(new Card(CardNumber.TEN, Suit.DIAMOND));
		assertEquals(12, hand.getScore()); // 1 + 1 + 10 = 12
	}

	@Test
	@DisplayName("Ace 세 장 + 6이면 하나는 11, 나머지는 1")
	void threeAcesAndSix() {
		Hand hand = new Hand();
		hand.addCard(new Card(CardNumber.ACE, Suit.HEART)); // 11
		hand.addCard(new Card(CardNumber.ACE, Suit.CLUB)); // 1
		hand.addCard(new Card(CardNumber.ACE, Suit.DIAMOND)); // 1
		hand.addCard(new Card(CardNumber.SIX, Suit.SPADE));
		assertEquals(19, hand.getScore()); // 11 + 1 + 1 + 6
	}

	@Test
	@DisplayName("Ace 네 장만 있으면 14점 (11 + 1 + 1 + 1)")
	void fourAcesOnlyShouldBe14() {
		Hand hand = new Hand();
		hand.addCard(new Card(CardNumber.ACE, Suit.HEART)); // 11
		hand.addCard(new Card(CardNumber.ACE, Suit.CLUB)); // 1
		hand.addCard(new Card(CardNumber.ACE, Suit.SPADE)); // 1
		hand.addCard(new Card(CardNumber.ACE, Suit.DIAMOND)); // 1
		assertEquals(14, hand.getScore()); // 11 + 1 + 1 + 1
	}


	@Test
	void sumOfTwoCardsIs21ReturnTrue() {
		Hand hand = new Hand();
		hand.addCard(new Card(CardNumber.ACE, Suit.HEART));
		hand.addCard(new Card(CardNumber.TEN, Suit.DIAMOND));
		assertTrue(hand.isBlackjack());
	}

	@Test
	void sumOfTwoCardsIsBurstThan21ReturnTrue() {
		Hand hand = new Hand();
		hand.addCard(new Card(CardNumber.QUEEN, Suit.HEART));
		hand.addCard(new Card(CardNumber.KING, Suit.DIAMOND));
		hand.addCard(new Card(CardNumber.EIGHT, Suit.CLUB));
		assertFalse(hand.isBurst());
	}

}
