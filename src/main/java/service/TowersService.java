package service;

import models.AnswerModel;
import models.RodModel;
import models.TowersModel;

import java.util.Stack;

/**
 * The main logical component of the whole game
 */
public class TowersService {
    private RodService rodService;
    private TowersModel towersModel;
    private int finalRod;
    private boolean victory;

    /**
     * Initiates the new game.
     * @param pegs the number of pegs/discs we want to use
     * @param rods the number of rods we want to use
     * @return the AnswerModel telling us the current state of the game
     */
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
        towersModel.setFinalRod(finalRod);
        addNewRods(pegs, rods);
        towersModel.getRod(finalRod).setFinalRod(true);
        populate(pegs);
        return generateAnswer("New game started");
    }

    /**
     * Getter for TowersModel
     * @return TowersModel, the POJO with all rods and discs
     */
    public TowersModel getTowersModel() {
        return towersModel;
    }

    /**
     * Checks validity of a move, makes a move
     * @param from a rod number to remove a disc from
     * @param to a rod number to add a disc too
     * @return the AnswerModel telling us the current state of the game
     */
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

    /**
     * Helper method for generating and returning AnswerModel
     * @param s the message we want to display
     * @return the AnswerModel telling us the current state of the game
     */
    private AnswerModel generateAnswer(String s) {
        AnswerModel ret = new AnswerModel();
        ret.setMessage(s);
        ret.setCurrentState(towersModel);
        return ret;
    }

    /**
     * Helper method, populates the rod with pegs. For starting rod only (sorted)
     * @param pegs number of discs/pegs
     */
    private void populate(int pegs) {
        for (int t = pegs - 1; t >= 0; t--) {
            towersModel.getRod(0).getStack().push(t);
        }
    }

    /**
     * Helper, populates game with Rods
     * @param pegs how many pegs we have in a game
     * @param rods how many rods we have in a game
     */
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
