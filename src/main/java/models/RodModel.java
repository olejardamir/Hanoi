package models;

import java.util.Stack;

public class RodModel {

    private int totalPegs;
    private int rodNumber;
    private boolean isFinalRod;
    private Stack<Integer> stack;

    public int getTotalPegs() {
        return totalPegs;
    }

    public void setTotalPegs(int totalPegs) {
        this.totalPegs = totalPegs;
    }

    public int getRodNumber() {
        return rodNumber;
    }

    public void setRodNumber(int rodNumber) {
        this.rodNumber = rodNumber;
    }

    public boolean isFinalRod() {
        return isFinalRod;
    }

    public void setFinalRod(boolean finalRod) {
        isFinalRod = finalRod;
    }

    public Stack<Integer> getStack() {
        return stack;
    }

    public void setStack(Stack<Integer> stack) {
        this.stack = stack;
    }
}
