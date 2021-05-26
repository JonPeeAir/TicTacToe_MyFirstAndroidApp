package com.personal.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    AppBarConfiguration appBarConfiguration;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set initial view to a splash screen
        setContentView(R.layout.splash_screen);
        getSupportActionBar().hide();

        //make splash screen go full screen boi
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);

        //create a timer for when when we switch to the main view
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getSupportActionBar().show();
                setContentView(R.layout.activity_main);
                decorView.setSystemUiVisibility(0);

                NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.NavHostFragment);
                navController = navHostFragment.getNavController();
                appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
                NavigationUI.setupActionBarWithNavController(MainActivity.this, navController, appBarConfiguration);
            }
        }, 2000);

    }

    //setup automatic navigation support for fragments
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }
}