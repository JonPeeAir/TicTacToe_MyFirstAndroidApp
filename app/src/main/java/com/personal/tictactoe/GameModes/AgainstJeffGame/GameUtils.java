package com.personal.tictactoe.GameModes.AgainstJeffGame;

import android.util.Log;
import android.widget.Button;

import com.personal.tictactoe.R;

import java.util.ArrayList;

public class GameUtils {

    // Log tag for debugging
    private static final String TAG = "GameUtils";

    private String X;
    private String O;
    private String EMPTY;

    public GameUtils() {
        X = "X";
        O = "O";
        EMPTY = " ";
    }

    public static String[][] readBoard(Button[][] rawBoard){

        Log.d(TAG, "readBoard: rawBoard size: [" + rawBoard.length + "][" + rawBoard[0].length + "]");

        String[][] board = new String[3][3];
        for (int i = 0; i < rawBoard.length; i++) {
            for (int j = 0; j < rawBoard[i].length; j++) {
                board[i][j] = rawBoard[i][j].getText().toString();
            }
        }
        return board;
    }

    public String player(String[][] board) throws Exception {

        int xCount = 0;
        int oCount = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].equals("X")) {
                    xCount++;
                } else if (board[i][j].equals("O")) {
                    oCount++;
                }
            }
        }

        if (xCount == oCount) {
            return X;
        } else if (xCount > oCount) {
            return O;
        } else {
            throw new Exception("GameBoard Error: Cannot determine current player");
        }

    }

    public ArrayList<int[]> actions(String[][] board) {

        ArrayList<int[]> possibleActions = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].equals(EMPTY)) {
                    int[] cell = {i, j};
                    possibleActions.add(cell);
                }
            }
        }


        return possibleActions;
    }

    public String[][] result(String[][] board, int[] action) throws Exception {

        ArrayList<int[]> possibleActions = actions(board);
        boolean actionIsPossible = false;
        for (int[] possibleAction: possibleActions) {
            if (possibleAction[0] == action[0] && possibleAction[1] == action[1]) {
                actionIsPossible = true;
            }
        }

        if (actionIsPossible == false) {
            throw new Exception("Action error: invalid action");
        }

        String symbol = player(board);

        String[][] resultBoard = new String[3][3];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                resultBoard[i][j] = board[i][j];
            }
        }

        resultBoard[action[0]][action[1]] = symbol;

        return resultBoard;
    }

    public String winner(String[][] board) {
        if (checkRow(board, "X") || checkColumn(board, "X") || checkDiagonal(board, "X")) {
            return X;
        } else if (checkRow(board, "O") || checkColumn(board, "O") || checkDiagonal(board, "O")) {
            return O;
        }

        // Return null if the game is still in progress or if the game is a tie
        return null;
    }

// ------------------------CHECK FOR WINNER HELPERS-----------------------------
    private boolean checkRow(String[][] board, String value) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(value) &&
                board[i][1].equals(value) &&
                board[i][2].equals(value)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumn(String[][] board, String value) {
        for (int i = 0; i < 3; i++) {
            if (board[0][i].equals(value) &&
                board[1][i].equals(value) &&
                board[2][i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonal(String[][] board, String value) {
        boolean diagonal1 = board[0][0].equals(value) &&
                            board[1][1].equals(value) &&
                            board[2][2].equals(value);

        boolean diagonal2 = board[0][2].equals(value) &&
                            board[1][1].equals(value) &&
                            board[2][0].equals(value);

        return diagonal1 || diagonal2;
    }
// ------------------------CHECK FOR WINNER HELPERS-----------------------------

    public boolean terminal(String[][] board) {

        if (winner(board) != null || actions(board).isEmpty()) {
            return true;
        }
        return false;

    }

    public int utility(String[][] board) {

        if (winner(board) == null) {
            return 0;
        } else if (winner(board).equals(X)) {
            return 1;
        } else if (winner(board).equals(O)) {
            return -1;
        }
        return 0;

    }

    public ActionValue minimax(String[][] board, int alpha, int beta) throws Exception {

        if (terminal(board)) {
            return null;
        }

        String currentPlayer = null;

        try {
            currentPlayer = player(board);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (currentPlayer.equals(X)) {

            ActionValue bestAction = null;

            for (int[] action : actions(board)) {

                String[][] resultBoard = new String[3][3];

                try {
                    resultBoard = result(board, action);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                ActionValue resultValue = minimax(resultBoard, alpha, beta);

                if (resultValue == null) {
                    int score = utility(resultBoard);
                    if (bestAction == null || score > bestAction.getValue()) {
                        bestAction = new ActionValue(action, score);
                    }
                    if (score >= beta) {
                        return bestAction;
                    }
                    if (score > alpha) {
                        alpha = score;
                    }
                } else {
                    int resultScore = resultValue.getValue();
                    if (bestAction == null || resultScore > bestAction.getValue()) {
                        bestAction = new ActionValue(action, resultScore);
                    }
                    if (resultScore >= beta) {
                        return bestAction;
                    }
                    if (resultScore > alpha) {
                        alpha = resultScore;
                    }
                }
            }

            return bestAction;

        } else if (currentPlayer.equals(O)){

            ActionValue bestAction = null;

            for (int[] action : actions(board)) {

                String[][] resultBoard = new String[3][3];

                try {
                    resultBoard = result(board, action);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                ActionValue resultValue = minimax(resultBoard, alpha, beta);

                if (resultValue == null) {
                    int score = utility(resultBoard);
                    if (bestAction == null || score < bestAction.getValue()) {
                        bestAction = new ActionValue(action, score);
                    }
                    if (score <= alpha) {
                        return bestAction;
                    }
                    if (score < beta) {
                        beta = score;
                    }
                } else {
                    int resultScore = resultValue.getValue();
                    if (bestAction == null || resultScore < bestAction.getValue()) {
                        bestAction = new ActionValue(action, resultScore);
                    }
                    if (resultScore <= alpha) {
                        return bestAction;
                    }
                    if (resultScore < beta) {
                        beta = resultScore;
                    }
                }
            }

            return bestAction;

        } else {
            throw new Exception("An error occurred with the minimax algorithm");
        }

    }


}
