package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import card.Card;

public class Hand {
	private final List<Card> cards = new ArrayList<>();

	public void addCard(Card card) {
		cards.add(card);
	}

	public int getScore() {
		int score = 0;
		int aceCount = 0;

		for (Card card : cards) {
			score += card.getMinValue();
			if (card.isAce()) { 
				aceCount++;
			}
		}
		// Ace 하나를 11로 계산했을 때 21 이하라면 11로 처리
		if (aceCount > 0 && score + 10 <= 21) {
			return score + 10;
		}
		return score; // Ace가 없거나 Ace를 1로 처리했을 때
	}

	public boolean isBurst() {
		return getScore() > 21;
	}

	public boolean isBlackjack() {
		return cards.size() == 2 && getScore() == 21;
	}

	public List<Card> getCards() {
		return Collections.unmodifiableList(cards);
	}

}
