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
import android.widget.TextView;

import com.personal.tictactoe.GameModes.LocalGame.LocalGameFragmentDirections;
import com.personal.tictactoe.GameModes.LocalGame.PlayerTwoPromptFragmentDirections;
import com.personal.tictactoe.R;

import org.jetbrains.annotations.NotNull;

public class WinnerFragment extends Fragment {

    private String winner;
    private String loser;
    private TextView winnerTextView;
    private Button playAgain;
    private Button chooseGameMode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_winner, container, false);

        winner = WinnerFragmentArgs.fromBundle(getArguments()).getWinner();
        loser = WinnerFragmentArgs.fromBundle(getArguments()).getLoser();
        winnerTextView = view.findViewById(R.id.winner_placeholder);
        playAgain = view.findViewById(R.id.play_again_button_winner);
        chooseGameMode = view.findViewById(R.id.choose_game_mode_winner);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        winnerTextView.setText(winner + " is weiner :>");

        //setup button to play again
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WinnerFragmentDirections.ActionWinnerFragmentToLocalGameFragment action;
                action = WinnerFragmentDirections.actionWinnerFragmentToLocalGameFragment(loser, winner);
                Navigation.findNavController(view).navigate(action);
            }
        });

        //setup button to go back to main menu
        chooseGameMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_winnerFragment_to_gameModeSelectorFragment);
            }
        });

    }
}