package views.ApplicationController;

import com.google.gson.Gson;
import models.AnswerModel;
import ninja.NinjaTest;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class HanoiAPITest extends NinjaTest
{

    private Gson gson = new Gson();

    @Before
    public void setup() {
        ninjaTestBrowser.makeRequest(getServerAddress() + "setup");
    }


    @Test
    public void testTowerInit() {
        String answer = ninjaTestBrowser.postJson(getServerAddress() + "/newGame/0/0/",null);
        AnswerModel answerModel = gson.fromJson(answer,AnswerModel.class);
        assert (answerModel.getMessage().equals("Number of pegs must be greater than 0"));

         answer = ninjaTestBrowser.postJson(getServerAddress() + "/newGame/1/1/",null);
         answerModel = gson.fromJson(answer,AnswerModel.class);
        assert (answerModel.getMessage().equals("Number of rods must be at least 2 if number of pegs is 1"));

        answer = ninjaTestBrowser.postJson(getServerAddress() + "/newGame/2/2/",null);
        answerModel = gson.fromJson(answer,AnswerModel.class);
        assert (answerModel.getMessage().equals("Number of rods must be at least 3 if number of pegs is greater than 1"));

        answer = ninjaTestBrowser.postJson(getServerAddress() + "/newGame/1/2/",null);
        answerModel = gson.fromJson(answer,AnswerModel.class);
        assert (answerModel.getMessage().equals("New game started"));
    }

    @Test
    public void testEasyVictory() {
        String answer = ninjaTestBrowser.postJson(getServerAddress() + "/newGame/1/2/",null);
        AnswerModel answerModel = gson.fromJson(answer,AnswerModel.class);
        assert (answerModel.getMessage().equals("New game started"));

        answer = ninjaTestBrowser.postJson(getServerAddress() + "/playGame/0/1/",null);
        answerModel = gson.fromJson(answer,AnswerModel.class);
        assert (answerModel.getMessage().equals("VICTORY!"));

        answer = ninjaTestBrowser.postJson(getServerAddress() + "/playGame/0/1/",null);
        answerModel = gson.fromJson(answer,AnswerModel.class);
        assert (answerModel.getMessage().equals("You already won, please start a new game"));
    }

    @Test
    public void testIllegalMoves() {
        String answer = ninjaTestBrowser.postJson(getServerAddress() + "/newGame/4/3/",null);
        AnswerModel answerModel = gson.fromJson(answer,AnswerModel.class);
        assert (answerModel.getMessage().equals("New game started"));

        answer = ninjaTestBrowser.postJson(getServerAddress() + "/playGame/1/2/",null);
        answerModel = gson.fromJson(answer,AnswerModel.class);
        assert (answerModel.getMessage().equals("Illegal move, no pegs on a rod number 1 to make a FROM move."));

        ninjaTestBrowser.postJson(getServerAddress() + "/playGame/0/1/",null);
        ninjaTestBrowser.postJson(getServerAddress() + "/playGame/0/2/",null);

        answer = ninjaTestBrowser.postJson(getServerAddress() + "/playGame/2/1/",null);
        answerModel = gson.fromJson(answer,AnswerModel.class);
        assert (answerModel.getMessage().equals("Illegal move, the rod 1 does not allow the TO move."));

    }


    @Test
    public void testRandomVictory() {
        String answer = ninjaTestBrowser.postJson(getServerAddress() + "/newGame/4/3/",null);
        AnswerModel answerModel = gson.fromJson(answer,AnswerModel.class);
        String expected = gson.toJson(answerModel.getCurrentState().getRod(0).getStack());
        Random rnd = new Random();
        while (true) {
            int from = rnd.nextInt(3);
            int to = rnd.nextInt(3);
            answer = ninjaTestBrowser.postJson(getServerAddress() + "/playGame/"+from+"/"+to+"/",null);
            answerModel = gson.fromJson(answer,AnswerModel.class);
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