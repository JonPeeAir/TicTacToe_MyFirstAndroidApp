package com.personal.tictactoe.GameModes.GameModeSelectorLogic;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.personal.tictactoe.GameModes.AgainstJeffGame.AgainstJeffMenuFragment;
import com.personal.tictactoe.GameModes.LocalGame.LocalGameMenuFragment;
import com.personal.tictactoe.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class GameModeSelectorFragment extends Fragment {

    private ViewPager2 gameModeSelector;
    private final String[] gameModeNames = {"Local", "Against Jeff"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_mode_selector, container, false);

        //compile the menu fragments to place in view pager
        ArrayList<Fragment> gameModes = new ArrayList<>();
        gameModes.add(new LocalGameMenuFragment());
        gameModes.add(new AgainstJeffMenuFragment());

        //create view pager adapter and a view pager
        GameModeSelectorAdapter gameModeSelectorAdapter = new GameModeSelectorAdapter(requireActivity().getSupportFragmentManager(), getLifecycle(), gameModes);
        gameModeSelector = view.findViewById(R.id.view_pager2);

        //setup the view pager adapter with the view pager
        gameModeSelector.setAdapter(gameModeSelectorAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //setup the tab layout with the view pager, as well as the tab names
        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout, gameModeSelector, (tab, position) -> tab.setText(gameModeNames[position])).attach();

    }
}