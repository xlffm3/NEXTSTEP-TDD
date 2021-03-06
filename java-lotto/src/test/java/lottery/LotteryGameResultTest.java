package lottery;

import lottery.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LotteryGameResultTest {

    private LotteryGame lotteryGame;
    private WinningLottery winningLottery;
    private WinningLottery thirdWinningLottery;
    private LotteryTicketsGroup winnerTicketsGroup;

    @BeforeEach
    public void setupVariables() {
        lotteryGame = LotteryGame.getInstance();
        winningLottery = WinningLottery.of("11, 22, 33, 44, 1, 2".split(", "), 45);
        thirdWinningLottery = WinningLottery.of("11, 22, 33, 44, 9, 3".split(", "), 45);
        winnerTicketsGroup = LotteryTicketsGroup.from(
                Arrays.asList(LotteryTicket.publishManualLotteryTicket("11, 22, 33, 44, 1, 2".split(", ")),
                        LotteryTicket.publishManualLotteryTicket("11, 22, 33, 44, 9, 3".split(", "))));
    }

    @DisplayName("우승 티켓 목록을 받으면 각 등수별 당첨자 수를 저장한 Map을 반환함")
    @Test
    public void getLotteryGameResult() {
        LotteryGameResult lotteryGameResult = lotteryGame
                .drawWinnerLotteryTickets(winnerTicketsGroup, winningLottery);

        assertThat(lotteryGameResult.findWinnerTicketCountsByRank(LotteryRank.FIRST_PRIZE))
                .isEqualTo(1);
        assertThat(lotteryGameResult.findWinnerTicketCountsByRank(LotteryRank.FOURTH_PRIZE))
                .isEqualTo(1);
    }

    @DisplayName("수익률 계산")
    @Test
    public void getReturnOfRate() {
        PurchasePrice purchasePrice = PurchasePrice.of(LotteryRank.FIRST_PRIZE.getPrizeMoney(), 0);
        LotteryGameResult lotteryGameResult = lotteryGame
                .drawWinnerLotteryTickets(winnerTicketsGroup, winningLottery);

        RateOfReturn returnOfRate = lotteryGameResult.calculateRateOfReturn(purchasePrice);
        double totalRevenue = LotteryRank.FIRST_PRIZE.getPrizeMoney() + LotteryRank.FOURTH_PRIZE.getPrizeMoney();

        assertThat(returnOfRate.getRateOfReturn()).isEqualTo(totalRevenue / purchasePrice.getPurchasePrice());
    }
}
