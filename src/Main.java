import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {
		InputView inputView = new InputView();
		OutputView outputView = new OutputView();

		List<Player> players = inputView.readPlayers();
		BlackjackGame game = new BlackjackGame(players);

		game.dealInitialCards();                    // 카드 분배
		outputView.showInitialCards(game.getDealer(), players);

		game.playPlayersTurn(inputView);            // 각 플레이어 턴 
		game.playDealerTurn();                      // 딜러 턴
		outputView.showFinalCards(game.getDealer(), players);

		game.calculateResults();                    // 수익 계산
		outputView.showProfitResults(game.getResultMap());
	}
}
