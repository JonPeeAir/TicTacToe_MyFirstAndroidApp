package com.personal.tictactoe.GameModes.AgainstJeffGame;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.personal.tictactoe.GameModes.LocalGame.LocalGameFragmentArgs;
import com.personal.tictactoe.R;

import org.jetbrains.annotations.NotNull;

public class AgainstJeffFragment extends Fragment {

    // Log tag for debugging
    private static final String TAG = "AgainstJeffFragment";

    private TextView currentPlayerTextView;

    private String playerInitialChoice;

    private Button[][] TicTacToeGrid;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_against_jeff, container, false);

        //initialize current player text view
        currentPlayerTextView = view.findViewById(R.id.current_player_jeff);

        //get human player's initial choice
        playerInitialChoice = AgainstJeffFragmentArgs.fromBundle(getArguments()).getPlayerChoice();

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

        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: player's choice: " + playerInitialChoice);
    }
}