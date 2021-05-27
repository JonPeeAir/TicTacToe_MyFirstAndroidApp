package com.personal.tictactoe.GameModes.AgainstJeffGame;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.personal.tictactoe.R;

import org.jetbrains.annotations.NotNull;

public class AgainstJeffFragment extends Fragment {

    // Log tag for debugging
    private static final String TAG = "AgainstJeffFragment";

    private GameUtils game;

    private TextView currentPlayerTextView;

    private String playerSymbol;
    private String jeffSymbol;

    private Button[][] TicTacToeGrid;

    private boolean itsPlayersTurn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_against_jeff, container, false);

        // Initialize our game tools/utils/jeff's brain?
        game = new GameUtils();

        // Initialize current player text view
        currentPlayerTextView = view.findViewById(R.id.current_player_jeff);

        // Get human player's initial choice
        playerSymbol = AgainstJeffFragmentArgs.fromBundle(getArguments()).getPlayerChoice();

        // Initialize what Jeff's symbol would be depending on player
        if (playerSymbol.equals("X")) {
            jeffSymbol = "O";
        } else if (playerSymbol.equals("O")) {
            jeffSymbol = "X";
        }

        //create 2D array of gridButtons and initialize all of them
        TicTacToeGrid = new Button[3][3];
        TicTacToeGrid[0][0] = view.findViewById(R.id.button00_jeff);
        TicTacToeGrid[0][1] = view.findViewById(R.id.button01_jeff);
        TicTacToeGrid[0][2] = view.findViewById(R.id.button02_jeff);
        TicTacToeGrid[1][0] = view.findViewById(R.id.button10_jeff);
        TicTacToeGrid[1][1] = view.findViewById(R.id.button11_jeff);
        TicTacToeGrid[1][2] = view.findViewById(R.id.button12_jeff);
        TicTacToeGrid[2][0] = view.findViewById(R.id.button20_jeff);
        TicTacToeGrid[2][1] = view.findViewById(R.id.button21_jeff);
        TicTacToeGrid[2][2] = view.findViewById(R.id.button22_jeff);

        // Initialize who starts first
        if (playerSymbol.equals("X")) {
            itsPlayersTurn = true;
        } else if (playerSymbol.equals("O")) {
            itsPlayersTurn = false;
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: player's choice: " + playerSymbol);

        setCurrentPlayerTextView();

        // If Jeff gets first move
        if (!itsPlayersTurn) {
            playJeffMove(null, view);
            // Reload current player text view
            setCurrentPlayerTextView();
        }

        // This code runs whenever you're about to make your first move
        // Set button functionality for all buttons
        // Button functionality is now responsible for the rest of the game
        initializeButtonFunctionality(view);

    }

    private void initializeButtonFunctionality(View view){
        for (int i = 0; i < TicTacToeGrid.length; i++) {
            for (int j = 0; j < TicTacToeGrid[i].length; j++) {
                int[] buttonLocation = {i, j};
                Button button = TicTacToeGrid[buttonLocation[0]][buttonLocation[1]];
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonFunctionality(button, buttonLocation, view);
                    }
                });
            }
        }
    }

    private void enableButtonFunctionality(){
        for (int i = 0; i < TicTacToeGrid.length; i++) {
            for (int j = 0; j < TicTacToeGrid[i].length; j++) {
                TicTacToeGrid[i][j].setEnabled(true);
            }
        }
    }

    private void disableButtonFunctionality(int[] buttonLocation){
        for (int i = 0; i < TicTacToeGrid.length; i++) {
            for (int j = 0; j < TicTacToeGrid[i].length; j++) {

                if (buttonLocation == null) {
                    // Do nothing
                } else if (i == buttonLocation[0] && j == buttonLocation[1]) {
                    continue;
                }
                TicTacToeGrid[i][j].setEnabled(false);
            }
        }
    }

    //abstracted button functionality setup
    private void buttonFunctionality(Button button, int[] buttonLocation, View view) {
        // If button is empty...
        if (button.getText().toString().equals(" ")) {
            // Put player's symbol on there then switch to Jeff's turn
            button.setText(playerSymbol);
            itsPlayersTurn = false;

            // Read game state and if the board is terminal navigate to the appropriate results fragment
            checkGameBoard(view);

            // We did not navigate to a results fragment so game is not yet terminal
            // Reload current player text view
            setCurrentPlayerTextView();

            // Jeff's turn to move
            playJeffMove(buttonLocation, view);

            // If game board is terminal, navigate to result and stuff
            checkGameBoard(view);

            // We did not navigate to a results fragment so game is not yet terminal
            // Reload current player text view
            setCurrentPlayerTextView();

        }


    }

    private void playJeffMove(int[] buttonLocation, View view){
        // Read the current game board
        String[][] readableBoard = GameUtils.readBoard(TicTacToeGrid);

        // Disable game board temporarily
        disableButtonFunctionality(buttonLocation);

        // Get Jeff's action from minimax algorithm
        ActionValue jeffDecision = null;
        int[] jeffMove = null;
        try {
            jeffDecision = game.minimax(readableBoard, Integer.MIN_VALUE, Integer.MAX_VALUE);
            jeffMove = jeffDecision.getAction();
        } catch (Exception e) {
            e.printStackTrace();
        }


        // Play that action on the board
        TicTacToeGrid[jeffMove[0]][jeffMove[1]].setText(jeffSymbol);

        // Re-enable button functionality
        enableButtonFunctionality();

        // Switch it to player's turn
        itsPlayersTurn = true;
    }

    private void checkGameBoard(View view){
        String[][] readableBoard = GameUtils.readBoard(TicTacToeGrid);
        if (game.terminal(readableBoard)) {
            // Navigate to appropriate results fragment passing in the winner if there is one
            String winner = game.winner(readableBoard);
            if (winner == null) {
                AgainstJeffFragmentDirections.ActionAgainstJeffFragmentToTieFragment action;
                action = AgainstJeffFragmentDirections.actionAgainstJeffFragmentToTieFragment("You", "Jeff");
                Navigation.findNavController(view).navigate(action);

            } else if (winner.equals(playerSymbol)) {
                AgainstJeffFragmentDirections.ActionAgainstJeffFragmentToWinnerFragment action;
                action = AgainstJeffFragmentDirections.actionAgainstJeffFragmentToWinnerFragment("You", "Jeff");
                Navigation.findNavController(view).navigate(action);

            } else if (winner.equals(jeffSymbol)) {
                AgainstJeffFragmentDirections.ActionAgainstJeffFragmentToWinnerFragment action;
                action = AgainstJeffFragmentDirections.actionAgainstJeffFragmentToWinnerFragment("Jeff", "You");
                Navigation.findNavController(view).navigate(action);
            }
        }
    }

    private void setCurrentPlayerTextView(){
        if (itsPlayersTurn) {
            currentPlayerTextView.setText("Your turn");
        } else {
            currentPlayerTextView.setText("Jeff is thinking");
        }
    }
}