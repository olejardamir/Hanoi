package service;

import models.RodModel;

import java.util.LinkedList;
import java.util.Queue;

/**
 * The main logical component for the game, per single rod.
 */
public class RodService {

    /**
     * Tells us whether we can make the move (add) or not.
     * @param n the peg number (or size)
     * @param rodModel the rod model in question
     * @return whether the add is legal or not
     */
    public boolean testAdd(int n, RodModel rodModel) {
        try {
            Integer lastPeg = rodModel.getStack().peek();
            if (n < lastPeg) {
                return true;
            }
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    /**
     * Takes a look into a stack and tells us the rod number/size that is on top
     * @param rodModel the rod model in question
     * @return the rod number/size that is on top of a stack
     */
    public int peek(RodModel rodModel) {
        try {
            return rodModel.getStack().peek();
        } catch (Exception ignored) {
        }
        return -1;
    }

    /**
     * Adds the peg/disc to a stack, assuming we have previously tested it
     * @param n the peg number (or size)
     * @param rodModel the rod model in question
     */
    public void addPeg(int n, RodModel rodModel) {
        rodModel.getStack().push(n);
    }

    /**
     * Removes the peg/disc from a stack, assuming that rod has discs on it
     * @param rodModel the rod model in question
     */
    public void removePeg(RodModel rodModel) {
        rodModel.getStack().pop();
    }

    /**
     * Checks the victory status for a rod
     * @param rodModel the rod model in question
     * @return is it a victory or not
     */
    public boolean isVictory(RodModel rodModel) {
        if (!rodModel.isFinalRod()) return false;
        if (rodModel.getStack().size() < rodModel.getTotalPegs()) return false;
        Queue<Integer> clone = new LinkedList<>(rodModel.getStack());
        for (int t = clone.size() - 1; t >= 0; t--) {
            if (t != clone.remove()) return false;
        }
        return true;
    }


}
