package com.personal.tictactoe.GameModes.LocalGame;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.personal.tictactoe.R;

import org.jetbrains.annotations.NotNull;

public class LocalGameFragment extends Fragment {

    private String playerOneName;
    private String playerTwoName;
    private TextView currentPlayerTextView;

    private Button[][] TicTacToeGrid;

    private boolean itsPlayerOnesTurn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_local_game, container, false);
        playerOneName = LocalGameFragmentArgs.fromBundle(getArguments()).getPlayerOneName();
        playerTwoName = LocalGameFragmentArgs.fromBundle(getArguments()).getPlayerTwoName();

        //start with player one
        currentPlayerTextView = view.findViewById(R.id.player_one_nameholder);
        itsPlayerOnesTurn = true;

        //create 2D array of gridButtons and initialize all of them
        TicTacToeGrid = new Button[3][3];
        TicTacToeGrid[0][0] = view.findViewById(R.id.button00);
        TicTacToeGrid[0][1] = view.findViewById(R.id.button01);
        TicTacToeGrid[0][2] = view.findViewById(R.id.button02);
        TicTacToeGrid[1][0] = view.findViewById(R.id.button10);
        TicTacToeGrid[1][1] = view.findViewById(R.id.button11);
        TicTacToeGrid[1][2] = view.findViewById(R.id.button12);
        TicTacToeGrid[2][0] = view.findViewById(R.id.button20);
        TicTacToeGrid[2][1] = view.findViewById(R.id.button21);
        TicTacToeGrid[2][2] = view.findViewById(R.id.button22);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //start with player one
        currentPlayerTextView.setText(playerOneName + "\'s turn");

        //set button functionality for all buttons
        for (int i = 0; i < TicTacToeGrid.length; i++) {
            for (int j = 0; j < TicTacToeGrid[i].length; j++) {
                Button button = TicTacToeGrid[i][j];
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setButtonFunctionality(button, v);
                    }
                });
            }
        }

    }

    //abstracted button functionality setup
    private void setButtonFunctionality(Button button, View view) {
        boolean buttonIsBlank = button.getText().toString().equals(" ");
        if (itsPlayerOnesTurn && buttonIsBlank){
            button.setText("X");

            int gameStatus = didGameEnd();

            if (gameStatus == 1) {
                //player 1 wins
                LocalGameFragmentDirections.ActionLocalGameFragmentToWinnerFragment action;
                action = LocalGameFragmentDirections.actionLocalGameFragmentToWinnerFragment(playerOneName, playerTwoName);
                Navigation.findNavController(view).navigate((NavDirections) action);
            } else if (gameStatus == 2) {
                //player 2 wins
                LocalGameFragmentDirections.ActionLocalGameFragmentToWinnerFragment action;
                action = LocalGameFragmentDirections.actionLocalGameFragmentToWinnerFragment(playerTwoName, playerOneName);
                Navigation.findNavController(view).navigate((NavDirections) action);
            } else if (gameStatus == 0) {
                //its a tie
                LocalGameFragmentDirections.ActionLocalGameFragmentToTieFragment action;
                action = LocalGameFragmentDirections.actionLocalGameFragmentToTieFragment(playerOneName, playerTwoName);
                Navigation.findNavController(view).navigate((NavDirections) action);
            } else {
                //continue the game
                currentPlayerTextView.setText(playerTwoName + "\'s turn");
                itsPlayerOnesTurn = false;
            }

        } else if (buttonIsBlank) {
            button.setText("O");

            int gameStatus = didGameEnd();

            if (gameStatus == 1) {
                //player 1 wins
                LocalGameFragmentDirections.ActionLocalGameFragmentToWinnerFragment action;
                action = LocalGameFragmentDirections.actionLocalGameFragmentToWinnerFragment(playerOneName, playerTwoName);
                Navigation.findNavController(view).navigate((NavDirections) action);
            } else if (gameStatus == 2) {
                //player 2 wins
                LocalGameFragmentDirections.ActionLocalGameFragmentToWinnerFragment action;
                action = LocalGameFragmentDirections.actionLocalGameFragmentToWinnerFragment(playerTwoName, playerOneName);
                Navigation.findNavController(view).navigate((NavDirections) action);
            } else if (gameStatus == 0) {
                //its a tie
                LocalGameFragmentDirections.ActionLocalGameFragmentToTieFragment action;
                action = LocalGameFragmentDirections.actionLocalGameFragmentToTieFragment(playerOneName, playerTwoName);
                Navigation.findNavController(view).navigate((NavDirections) action);
            } else {
                //continue the game
                currentPlayerTextView.setText(playerOneName + "\'s turn");
                itsPlayerOnesTurn = true;
            }

        }
    }

    //logic for checking if game ended
    private int didGameEnd() {
        if (checkRow("X") || checkColumn("X") || checkDiagonal("X")) {
            return 1;
        } else if (checkRow("O") || checkColumn("O") || checkDiagonal("O")) {
            return 2;
        } else if (thereAreNoMoreMoves()) {
            return 0;
        }
        //game can still go on
        return -1;
    }

    //logic for checking if player won by row
    private boolean checkRow(String value) {
        for (int i = 0; i < 3; i++) {
            if (TicTacToeGrid[i][0].getText().toString().equals(value) &&
                TicTacToeGrid[i][1].getText().toString().equals(value) &&
                TicTacToeGrid[i][2].getText().toString().equals(value)) {
                return true;
            }
        }
        return false;
    }

    //logic for checking if player won by column
    private boolean checkColumn(String value) {
        for (int i = 0; i < 3; i++) {
            if (TicTacToeGrid[0][i].getText().toString().equals(value) &&
                TicTacToeGrid[1][i].getText().toString().equals(value) &&
                TicTacToeGrid[2][i].getText().toString().equals(value)) {
                return true;
            }
        }
        return false;
    }

    //logic for checking if player won by diagonal
    private boolean checkDiagonal(String value) {
        boolean diagonal1 = TicTacToeGrid[0][0].getText().toString().equals(value) &&
                            TicTacToeGrid[1][1].getText().toString().equals(value) &&
                            TicTacToeGrid[2][2].getText().toString().equals(value);

        boolean diagonal2 = TicTacToeGrid[0][2].getText().toString().equals(value) &&
                            TicTacToeGrid[1][1].getText().toString().equals(value) &&
                            TicTacToeGrid[2][0].getText().toString().equals(value);

        return diagonal1 || diagonal2;
    }

    //logic for checking if there are no more possible moves
    private boolean thereAreNoMoreMoves() {
        for (int i = 0; i < TicTacToeGrid.length; i++) {
            for (int j = 0; j < TicTacToeGrid[i].length; j++) {

                Button button = TicTacToeGrid[i][j];
                boolean there_is_a_blank_button = button.getText().toString().equals(" ");

                if(there_is_a_blank_button){
                    return false;
                }
            }
        }
        return true;
    }


}