package views.ApplicationController;

import com.google.gson.Gson;
import models.AnswerModel;
import org.junit.Test;
import service.TowersService;

import java.util.Random;

/**
 * Tests the Service components
 */
public class HanoiServiceTest {

    private Gson gson = new Gson();

    /**
     * Tests the tower initialization, with all possible errors
     */
    @Test
    public void testTowerInit() {
        TowersService towersLogic = new TowersService();
        AnswerModel answerModel = towersLogic.newGame(0, 0);
        assert (answerModel.getMessage().equals("Number of pegs must be greater than 0"));

        answerModel = towersLogic.newGame(1, 1);
        assert (answerModel.getMessage().equals("Number of rods must be at least 2 if number of pegs is 1"));

        answerModel = towersLogic.newGame(2, 2);
        assert (answerModel.getMessage().equals("Number of rods must be at least 3 if number of pegs is greater than 1"));

        answerModel = towersLogic.newGame(1, 2);
        assert (answerModel.getMessage().equals("New game started"));
    }

    /**
     * Tests the victory scenario
     */
    @Test
    public void testEasyVictory() {
        TowersService towersLogic = new TowersService();
        AnswerModel answerModel = towersLogic.newGame(1, 2);
        assert (answerModel.getMessage().equals("New game started"));

        answerModel = towersLogic.makeMove(0, 1);
        System.out.println();
        assert (answerModel.getMessage().equals("VICTORY!"));

        answerModel = towersLogic.makeMove(0, 1);
        assert (answerModel.getMessage().equals("You already won, please start a new game"));
    }

    /**
     * Tests possible illegal moves
     */
    @Test
    public void testIllegalMoves() {
        TowersService towersLogic = new TowersService();
        AnswerModel answerModel = towersLogic.newGame(4, 3);
        assert (answerModel.getMessage().equals("New game started"));

        answerModel = towersLogic.makeMove(1, 2);
        assert (answerModel.getMessage().equals("Illegal move, no pegs on a rod number 1 to make a FROM move."));

        towersLogic.makeMove(0, 1);
        towersLogic.makeMove(0, 2);

        answerModel = towersLogic.makeMove(2, 1);
        assert (answerModel.getMessage().equals("Illegal move, the rod 1 does not allow the TO move."));

    }

    /**
     * Randomly gets to victory, may take a few seconds
     */
    @Test
    public void testRandomVictory() {
        TowersService towersLogic = new TowersService();
        AnswerModel answerModel = towersLogic.newGame(4, 3);
        String expected = gson.toJson(answerModel.getCurrentState().getRod(0).getStack());
        Random rnd = new Random();
        while (true) {
            int from = rnd.nextInt(3);
            int to = rnd.nextInt(3);
            answerModel = towersLogic.makeMove(from, to);
            if (answerModel.getMessage().equals("VICTORY!")) {
                String outcome = gson.toJson(answerModel.getCurrentState().getRod(2).getStack());
                assert (expected.equals(outcome));
                assert (gson.toJson(answerModel.getCurrentState().getRod(0).getStack()).equals("[]"));
                assert (gson.toJson(answerModel.getCurrentState().getRod(1).getStack()).equals("[]"));
                break;
            }
        }
    }
}
