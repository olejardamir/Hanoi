package models;

/**
 * The model of the JSON return, has a message and a TowersModel state
 */
public class AnswerModel {
    private String message;
    private TowersModel currentState;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TowersModel getCurrentState() {
        return currentState;
    }

    public void setCurrentState(TowersModel currentState) {
        this.currentState = currentState;
    }
}
