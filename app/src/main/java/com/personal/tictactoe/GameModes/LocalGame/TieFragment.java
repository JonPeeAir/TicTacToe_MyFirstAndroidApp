package com.personal.tictactoe.GameModes.LocalGame;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.personal.tictactoe.R;

import org.jetbrains.annotations.NotNull;

public class TieFragment extends Fragment {

    private String playerOne;
    private String playerTwo;
    private Button playAgain;
    private Button chooseGameMode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tie, container, false);

        playerOne = TieFragmentArgs.fromBundle(getArguments()).getPlayerOne();
        playerTwo = TieFragmentArgs.fromBundle(getArguments()).getPlayerTwo();
        playAgain = view.findViewById(R.id.play_again_button_tie);
        chooseGameMode = view.findViewById(R.id.choose_game_mode_tie);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //set up button to play again
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TieFragmentDirections.ActionTieFragmentToLocalGameFragment action;
                action = TieFragmentDirections.actionTieFragmentToLocalGameFragment(playerOne, playerTwo);
                Navigation.findNavController(view).navigate(action);
            }
        });

        //set up button to go back to main menu
        chooseGameMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_tieFragment_to_gameModeSelectorFragment);
            }
        });
    }
}