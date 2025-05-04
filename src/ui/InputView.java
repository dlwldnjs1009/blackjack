package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domain.Player;

public class InputView {
	private final Scanner scanner = new Scanner(System.in);

	public List<Player> readPlayers() {
		System.out.print("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준): ");
		String[] names = scanner.nextLine().split(",");
		List<Player> players = new ArrayList<>();

		for (String name : names) {
			name = name.trim();
			int bet = readBetAmount(name);

			players.add(new Player(name, bet, true));
		}

		return players;
	}

	private int readBetAmount(String name) {
		System.out.printf("%s의 베팅 금액은? ", name);
		return Integer.parseInt(scanner.nextLine().trim());
	}

	public boolean askDrawCard(String name) {
		System.out.printf("%s는 카드를 더 받겠습니까? (y/n): ", name);
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine().trim().equalsIgnoreCase("y");
	}
}
