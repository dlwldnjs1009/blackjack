package ui;

import java.util.List;
import java.util.Map;

import domain.Dealer;
import domain.Player;

public class OutputView {
	public void showInitialCards(Dealer dealer, List<Player> players) {
		System.out.println("딜러와 플레이어에게 2장의 카드를 나누었습니다.");
		System.out.printf("딜러: %s%n", dealer.getHand().getCards().get(0)); // 한 장만 공개
		for (Player player : players) {
			System.out.printf("%s카드: %s%n", player.getName(), player.getHand().getCards());
		}
	}

	public void showFinalCards(Dealer dealer, List<Player> players) {
		System.out.println("\n=== 최종 카드 및 점수 ===");
		System.out.printf("딜러: %s - 결과: %d%n", dealer.getHand().getCards(), dealer.getScore());
		for (Player player : players) {
			System.out.printf("%s카드: %s - 결과: %d%n", player.getName(), player.getHand().getCards(), player.getScore());
		}
	}

	public void showProfitResults(Map<String, Integer> profits) {
		System.out.println("\n=== 최종 수익 결과 ===");
		profits.forEach((name, amount) -> {
			System.out.printf("%s: %d원%n", name, amount);
		});
	}
}
