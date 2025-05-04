package game;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import deck.Deck;
import domain.Dealer;
import domain.Player;
import ui.InputView;

public class BlackjackGame {
	private final Dealer dealer = new Dealer();
	private final List<Player> players;
	private final Deck deck;
	private final Map<String, Integer> profitResult = new HashMap<>();
	private final InputView inputView = new InputView();

	public BlackjackGame(List<Player> players) {
		this(players, new Deck());
	}

	public BlackjackGame(List<Player> players, Deck deck) {
		this.players = players;
		this.deck = deck;
	}

	public void play() {
		dealInitialCards();
		playPlayersTurn(inputView);
		playDealerTurn();
		calculateResults();
	}

	public void dealInitialCards() {
		for (int i = 0; i < 2; i++) {
			dealer.draw(deck.draw());
			for (Player player : players) {
				player.draw(deck.draw());
			}
		}
	}

	public void playPlayersTurn(InputView inputView) {
		for (Player player : players) {
			while (!player.isBurst() && player.shouldDrawCard()) {
				player.draw(deck.draw());
			}

			System.out.printf("%s카드: %s%n", player.getName(), player.getHand().getCards());
			if (!this.inputView.askDrawCard(player.getName())) {
				player.stopDrawing(); // 사용자 입력이 n일 경우 카드 그만 받기
			}
		}
	}

	public void playDealerTurn() {
		while (!dealer.isBurst() && dealer.shouldDrawCard()) {
			dealer.draw(deck.draw());
		}
	}

	public void calculateResults() {
		for (Player player : players) {
			int profit = calculatePlayerProfit(player);
			profitResult.put(player.getName(), profit);
			profitResult.put(dealer.getName(), profitResult.getOrDefault(dealer.getName(), 0) - profit);
		}
	}

	private int calculatePlayerProfit(Player player) {
		int betMoney = player.getBetMoney();

		if (player.isBurst()) return -betMoney;
		if (dealer.isBurst()) return betMoney;
		if (player.isBlackjack() && !dealer.isBlackjack()) return (int) (betMoney * 1.5);
		if (!player.isBlackjack() && dealer.isBlackjack()) return -betMoney;
		if (player.getScore() > dealer.getScore()) return betMoney;
		if (player.getScore() < dealer.getScore()) return -betMoney;

		return 0; // 무승부
	}

	public int getProfit(Player player) {
		return profitResult.getOrDefault(player.getName(), 0);
	}

	public int getProfit(Dealer dealer) {
		return profitResult.getOrDefault(dealer.getName(), 0);
	}

	public Dealer getDealer() {
		return dealer;
	}

	public Map<String, Integer> getResultMap() {
		return Collections.unmodifiableMap(profitResult);
	}
}
