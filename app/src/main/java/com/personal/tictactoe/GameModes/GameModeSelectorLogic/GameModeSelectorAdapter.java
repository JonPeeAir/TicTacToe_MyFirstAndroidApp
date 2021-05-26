package com.personal.tictactoe.GameModes.GameModeSelectorLogic;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class GameModeSelectorAdapter extends FragmentStateAdapter {

    private ArrayList<Fragment> gameModes;

    public GameModeSelectorAdapter(@NonNull @NotNull FragmentManager fragmentManager, @NonNull @NotNull Lifecycle lifecycle, ArrayList<Fragment> gameModes) {
        super(fragmentManager, lifecycle);
        this.gameModes = gameModes;
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        return gameModes.get(position);
    }

    @Override
    public int getItemCount() {
        return gameModes.size();
    }
}
