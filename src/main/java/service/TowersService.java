package service;

import models.AnswerModel;
import models.RodModel;
import models.TowersModel;

import java.util.Stack;

public class TowersService {
    private RodService rodService;
    private TowersModel towersModel;
    private int finalRod;
    private boolean victory;

    public AnswerModel newGame(int pegs, int rods) {
        if (pegs == 0) {
            return generateAnswer("Number of pegs must be greater than 0");
        }
        else if(pegs==1 && rods<2){
            return generateAnswer("Number of rods must be at least 2 if number of pegs is 1");
        }
        else if(pegs>1 && rods<3){
            return generateAnswer("Number of rods must be at least 3 if number of pegs is greater than 1");
        }

        victory = false;
        rodService = new RodService();
        towersModel = new TowersModel();
        finalRod = rods - 1;
        addNewRods(pegs, rods);
        towersModel.getRod(finalRod).setFinalRod(true);
        populate(pegs);
        return generateAnswer("New game started");
    }


    public AnswerModel makeMove(int from, int to) {

        if (victory) {
            return generateAnswer("You already won, please start a new game");
        }

        int peg = rodService.peek(towersModel.getRod(from));
        if (peg < 0) {
            return generateAnswer("Illegal move, no pegs on a rod number " + from + " to make a FROM move.");
        }

        boolean testAdd = rodService.testAdd(peg, towersModel.getRod(to));

        if (!testAdd) {
            return generateAnswer("Illegal move, the rod " + to + " does not allow the TO move.");
        }

        rodService.removePeg(towersModel.getRod(from));
        rodService.addPeg(peg, towersModel.getRod(to));

        victory = rodService.isVictory(towersModel.getRod(finalRod));
        if (victory) {
            return generateAnswer("VICTORY!");
        }

        return generateAnswer("Please make the next move.");

    }

    private AnswerModel generateAnswer(String s) {
        AnswerModel ret = new AnswerModel();
        ret.setMessage(s);
        ret.setCurrentState(towersModel);
        return ret;
    }


    private void populate(int pegs) {
        for (int t = pegs - 1; t >= 0; t--) {
            towersModel.getRod(0).getStack().push(t);
        }
    }

    private void addNewRods(int pegs, int rods) {
        for (int t = 0; t < rods; t++) {
            RodModel rodModel = new RodModel();
            rodModel.setStack(new Stack<Integer>());
            rodModel.setTotalPegs(pegs);
            rodModel.setRodNumber(t);
            towersModel.addRod(rodModel);
        }
    }


}
