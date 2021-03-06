package lottery;

import lottery.domain.*;
import lottery.view.ViewMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class LotteryTicketTest {

    private List<LotteryNumber> lotteryNumberList;

    @BeforeEach
    public void resetLotteryNumberList() {
        lotteryNumberList = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            lotteryNumberList.add(LotteryNumber.from(i));
        }
    }

    @DisplayName("LotteryTicket 객체 정상 생성 테스트")
    @Test
    public void makeLotteryTicket() {
        LotteryTicket lotteryTicket = LotteryTicket.from(lotteryNumberList);

        assertThat(lotteryTicket.getLotteryNumbers())
                .isEqualTo(Arrays.asList(1, 2, 3, 4, 5, 6));
    }

    @DisplayName("LotteryTicket 자동 생성 모드로 객체 생성 테스트")
    @Test
    public void makeLotteryTicketAutomaticMode() {
        assertThatCode(() -> {
            LotteryTicket.publishAutomaticLotteryTicket();
        }).doesNotThrowAnyException();
    }

    @DisplayName("LotteryTicket 문자열 배열을 받았을 때 객체 생성")
    @Test
    public void makeLotteryTicketFromStringArrays() {
        String[] input = "1, 2, 3, 4, 5, 6".split(ViewMessages.COMMA);
        LotteryTicket lotteryTicket = LotteryTicket.publishManualLotteryTicket(input);

        assertThat(lotteryTicket.getLotteryNumbers())
                .isEqualTo(Arrays.asList(1, 2, 3, 4, 5, 6));
    }

    @DisplayName("LotteryTicket 객체 생성 실패(개수 예외)")
    @Test
    public void throwExceptionOnMakingLotteryTicket() {
        lotteryNumberList.remove(0);

        assertThatExceptionOfType(LotteryBuildingException.class)
                .isThrownBy(() -> {
                    LotteryTicket.from(lotteryNumberList);
                });
    }

    @DisplayName("LotteryTicket 객체 생성 실패(중복된 번호)")
    @Test
    public void throwDuplicatedExceptionOnMakingLotteryTicket() {
        lotteryNumberList.remove(0);
        lotteryNumberList.add(LotteryNumber.from(2));

        assertThatExceptionOfType(LotteryBuildingException.class)
                .isThrownBy(() -> {
                    LotteryTicket.from(lotteryNumberList);
                });
    }

    @DisplayName("맞춘 로또 개수에 따라 그에 맞는 LotteryRank 반환(맞춘 개수가 5개 아니면서 보너스 볼을 포함하는 경우)")
    @Test
    public void getMatchLotteryRank() {
        LotteryTicket firstTicket = LotteryTicket.from(lotteryNumberList);
        LotteryTicket fourthTicket = LotteryTicket.publishManualLotteryTicket("1,2,3,4,10,15".split(","));

        WinningLottery winningLottery = WinningLottery.of("1,2,3,4,5,6".split(","), 10);

        assertThat(firstTicket.getMatchLotteryRank(winningLottery))
                .isEqualTo(LotteryRank.FIRST_PRIZE);
        assertThat(fourthTicket.getMatchLotteryRank(winningLottery))
                .isEqualTo(LotteryRank.FOURTH_PRIZE);
    }

    @DisplayName("당첨 번호에 보너스볼 포함 여부에 따른 2등 3등 로또 순위 반환 테스트")
    @Test
    public void getSecondOrThirdLotteryRankWhenBonusBallNotInWinnerTicket() {
        LotteryTicket secondWinnerTicket = LotteryTicket.from(lotteryNumberList);
        LotteryTicket thirdWinnerTicket = LotteryTicket.publishManualLotteryTicket("1,2,3,4,5,7".split(","));

        WinningLottery winningLottery = WinningLottery.of("1,2,3,4,5,8".split(","), 6);

        assertThat(secondWinnerTicket.getMatchLotteryRank(winningLottery))
                .isEqualTo(LotteryRank.SECOND_PRIZE);
        assertThat(thirdWinnerTicket.getMatchLotteryRank(winningLottery))
                .isEqualTo(LotteryRank.THIRD_PRIZE);
    }
}
