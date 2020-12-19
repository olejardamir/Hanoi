package models;

import java.util.ArrayList;
import java.util.List;

/**
 * A model of all rods with all pegs, marked by the final rod number.
 * Has the list of rods for the game.
 */
public class TowersModel {

    private List<RodModel> rods = new ArrayList<>();
    private int finalRod;

    public List<RodModel> getRods() {
        return rods;
    }

    public void setRods(List<RodModel> rods) {
        this.rods = rods;
    }

    public int getFinalRod() {
        return finalRod;
    }

    public void setFinalRod(int finalRod) {
        this.finalRod = finalRod;
    }

    public RodModel getRod(int number) {
        return rods.get(number);
    }

    public void addRod(RodModel rod) {
        rods.add(rod);
    }
}
