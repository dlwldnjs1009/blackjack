import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackGame {
	private final Dealer dealer = new Dealer();
	private final List<Player> players;
	private final Deck deck;
	private final Map<String, Integer> profitResult = new HashMap<>();

	public BlackjackGame(List<Player> players) {
		this(players, new Deck());
	}

	public BlackjackGame(List<Player> players, Deck deck) {
		this.players = players;
		this.deck = deck;
	}

	public void play() {
		dealInitialCards();
		playPlayersTurn();
		playDealerTurn();
		calculateResults();
	}

	private void dealInitialCards() {
		for (int i = 0; i < 2; i++) {
			dealer.draw(deck.draw());
			for (Player player : players) {
				player.draw(deck.draw());
			}
		}
	}

	private void playPlayersTurn() {
		for (Player player : players) {
			while (!player.isBurst() && player.shouldDrawCard()) {
				player.draw(deck.draw());
			}
		}
	}

	private void playDealerTurn() {
		while (!dealer.isBurst() && dealer.shouldDrawCard()) {
			dealer.draw(deck.draw());
		}
	}

	// todo: 리팩토링 할 것
	private void calculateResults() {
		for (Player player : players) {
			int betMoney = player.getBetMoney();
			int profit = 0;

			if (player.isBurst()) { // 플레이어가 버스트
				profit = -betMoney;
			} else if (dealer.isBurst()) { // 딜러가 버스트
				profit = betMoney;
			} else if (player.isBlackjack() && !dealer.isBlackjack()) { // 플레이어가 블랙잭
				profit = (int) (betMoney * 1.5);
			} else if (!player.isBlackjack() && dealer.isBlackjack()) { // 딜러가 블랙잭
				profit = -betMoney;
			} else if (player.isBlackjack() && dealer.isBlackjack()) { // 둘 다 블랙잭
				profit = 0;
			} else if (player.getScore() > dealer.getScore()) { // 플레이어가 이김
				profit = betMoney;
			} else if (player.getScore() < dealer.getScore()) { // 플레이어가 짐
				profit = -betMoney;
			} else { // 그 외
				profit = 0;
			}

			profitResult.put(player.getName(), profit);
			profitResult.put(dealer.getName(), profitResult.getOrDefault(dealer.getName(), 0) - profit);
		}
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
}
