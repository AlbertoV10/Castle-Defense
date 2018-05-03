package com.example.alber.castledefense;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.app.Activity;
import java.io.Serializable;

public class StartActivity extends AppCompatActivity {

    private ViewGroup mContentView;
    private Button mStartButton;
    private Button mLoadButton;
    private Button mHelpButton;
    private GameManager gameManager = new GameManager();
    private Hero hero;
    private Tower[] towers = new Tower[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getWindow().setBackgroundDrawableResource(R.drawable.temp_main_menu);

        mContentView =(ViewGroup) findViewById(R.id.activity_main);

        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setToFullScreen();
            }
        });

        mStartButton = (Button) findViewById(R.id.start_button);
        mLoadButton = (Button) findViewById(R.id.load_button);
        mHelpButton = (Button) findViewById(R.id.tutorial_button);

        // Create Hero
        hero = new Hero();
        gameManager.setHero(hero);

        // Create Towers
        for(int index = 0; index < 3; index++)
        {
            towers[index] = new Tower();
        }
        gameManager.setTowers(towers);

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Toast.makeText(StartActivity.this, R.string.loading_toast, Toast.LENGTH_SHORT).show();
            Intent battleIntent = new Intent(StartActivity.this, BattleScreenActivity.class);
            // Pass gameManager object to next screen
            battleIntent.putExtra("gameManager", gameManager);
            startActivity(battleIntent);
            }
        });

        mLoadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loadIntent = new Intent(StartActivity.this, LoadMenuActivity.class);
                startActivity(loadIntent);
            }
        });

        mHelpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent helpIntent = new Intent(StartActivity.this, HelpActivity.class);
                startActivity(helpIntent);
            }
        });
    }

    // Provides Fullscreen functionality, hiding menu bars at the top
    private void setToFullScreen(){
        ViewGroup mainMenu = (ViewGroup) findViewById(R.id.activity_main);
        mainMenu.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    // After closing the app and reopening it, sets fullscreen mode
    @Override
    protected void onResume(){
        super.onResume();
        setToFullScreen();
    }

    // Prevent back button from doing anything
    @Override
    public void onBackPressed()
    {
        // super.onBackPressed(); // Comment this super call to avoid calling finish() or fragmentmanager's backstack pop operation.
    }
}
