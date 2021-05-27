package com.personal.tictactoe.GameModes.AgainstJeffGame;

public class ActionValue {

    private int[] action;
    private int value;

    public ActionValue(int[] action, int value) {
        this.action = action;
        this.value = value;
    }

    public int[] getAction() {
        return action;
    }

    public int getValue() {
        return value;
    }
}
