package service;

import models.RodModel;

import java.util.LinkedList;
import java.util.Queue;

public class RodService {

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


    public int peek(RodModel rodModel) {
        try {
            return rodModel.getStack().peek();
        } catch (Exception ignored) {
        }
        return -1;
    }

    public void addPeg(int n, RodModel rodModel) {
        rodModel.getStack().push(n);
    }

    public void removePeg(RodModel rodModel) {
        rodModel.getStack().pop();
    }

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
