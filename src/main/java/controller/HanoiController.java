package controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import models.AnswerModel;
import ninja.Result;
import ninja.Results;
import ninja.params.PathParam;
import service.TowersService;

@Singleton
 public class HanoiController {

    @Inject
    TowersService towersService;

    @Inject
    AnswerModel answerModel;

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
