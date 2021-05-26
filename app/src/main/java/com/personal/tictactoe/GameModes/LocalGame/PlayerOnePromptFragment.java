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

public class PlayerOnePromptFragment extends Fragment {

    PlayerOnePromptFragmentDirections.ActionPlayerOnePromptFragmentToPlayerTwoPromptFragment action;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player_one_prompt, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText playerOneNamePrompt = view.findViewById(R.id.player_one_name);

        //setup button that navigates to the player two prompt
        //also pass in player one's name as an argument
        Button toPlayerTwo = view.findViewById(R.id.to_player_two);
        toPlayerTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String playerOneName = playerOneNamePrompt.getText().toString();
                if (playerOneName.equals("")) { playerOneName = "Player 1"; }
                action = PlayerOnePromptFragmentDirections.actionPlayerOnePromptFragmentToPlayerTwoPromptFragment(playerOneName);
                Navigation.findNavController(view).navigate(action);
            }
        });

    }
}