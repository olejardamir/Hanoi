package controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import models.AnswerModel;
import ninja.Result;
import ninja.Results;
import ninja.params.PathParam;
import service.TowersService;

/**
 * The Hanoi Towers Controller
 */
@Singleton
 public class HanoiController {

    @Inject
    TowersService towersService;

    @Inject
    AnswerModel answerModel;

    /**
     * Initiates a new game for the player
     * @param rods the number of rods we want to play with
     * @param pegs the number of pegs (or discs) we want to play with
     * @return the JSON representation of the AnswerModel
     */
    public Result newGame(@PathParam("rods") int rods,
                          @PathParam("pegs") int pegs) {
        try {
            towersService = new TowersService();
            answerModel = towersService.newGame(pegs, rods);
            return Results.json().render(answerModel);
        }catch (Exception e){
            AnswerModel exception = new AnswerModel();
            exception.setMessage("Exception caught");
            if(towersService!=null){
                exception.setCurrentState(towersService.getTowersModel());
            }
            return Results.json().render(exception);
        }
    }

    /**
     * Allows the player to make the next move
     * @param from the number of a rod to play from
     * @param to the number of a rod to play to
     * @return the JSON representation of the AnswerModel
     */
    public Result playGame(@PathParam("from") int from,
                           @PathParam("to") int to) {
        try {
            answerModel = towersService.makeMove(from, to);
            return Results.json().render(answerModel);
        }catch (Exception e){
            AnswerModel exception = new AnswerModel();
            exception.setMessage("Illegal move, cannot move from "+from+" to "+to+".");
            if(towersService!=null){
                exception.setCurrentState(towersService.getTowersModel());
            }
            return Results.json().render(exception);
        }
    }
}
