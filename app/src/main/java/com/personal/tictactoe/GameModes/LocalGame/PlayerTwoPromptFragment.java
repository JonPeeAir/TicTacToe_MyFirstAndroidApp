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
import android.widget.EditText;

import com.personal.tictactoe.R;

import org.jetbrains.annotations.NotNull;

public class PlayerTwoPromptFragment extends Fragment {

    private String playerOneName;
    private PlayerTwoPromptFragmentDirections.ActionPlayerTwoPromptFragmentToLocalGameFragment action;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_player_two_prompt, container, false);
        playerOneName = PlayerTwoPromptFragmentArgs.fromBundle(getArguments()).getPlayerOneName();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText playerTwoNamePrompt = view.findViewById(R.id.player_two_name);

        //setup button that starts the game
        //also pass in both players one and two's names as arguments
        Button startGame = view.findViewById(R.id.start_game);
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String playerTwoName = playerTwoNamePrompt.getText().toString();
                if (playerTwoName.isEmpty()) {
                    playerTwoName = "Player 2";
                }
                action = PlayerTwoPromptFragmentDirections.actionPlayerTwoPromptFragmentToLocalGameFragment(playerOneName, playerTwoName);
                Navigation.findNavController(view).navigate(action);
            }
        });

    }
}