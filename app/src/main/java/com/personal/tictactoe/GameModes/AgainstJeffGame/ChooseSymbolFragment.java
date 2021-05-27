package com.personal.tictactoe.GameModes.AgainstJeffGame;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.personal.tictactoe.R;

import org.jetbrains.annotations.NotNull;

public class ChooseSymbolFragment extends Fragment {

    private Button xButton;
    private Button oButton;

    private ChooseSymbolFragmentDirections.ActionChooseSymbolFragmentToAgainstJeffFragment action;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_choose_symbol, container, false);

        xButton = view.findViewById(R.id.choice_x_button);
        oButton = view.findViewById(R.id.choice_o_button);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set up the "X" choice
        xButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action = ChooseSymbolFragmentDirections.actionChooseSymbolFragmentToAgainstJeffFragment("X");
                Navigation.findNavController(view).navigate(action);
            }
        });

        // Set up the "O" choice
        oButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action = ChooseSymbolFragmentDirections.actionChooseSymbolFragmentToAgainstJeffFragment("O");
                Navigation.findNavController(view).navigate(action);
            }
        });





    }
}