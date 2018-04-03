package com.example.alber.castledefense;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

public class EndOfRoundActivity extends AppCompatActivity {

    private ViewGroup mContentView;
    private Button mUpgradeButton;
    private Button mNextWaveButton;
    private Intent intent;
    private GameManager gameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_of_round);

        mUpgradeButton = (Button) findViewById(R.id.to_upgrade_button);
        mNextWaveButton = (Button) findViewById(R.id.next_wave);

        //getWindow().setBackgroundDrawableResource(R.drawable.temp_statistics);

        intent = getIntent();
        this.gameManager = (GameManager) intent.getSerializableExtra("gameManager");

        mContentView =(ViewGroup) findViewById(R.id.activity_end_of_round);

        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setToFullScreen();
            }
        });

        mUpgradeButton = (Button) findViewById(R.id.to_upgrade_button);

        mUpgradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mUpgradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent battleIntent = new Intent(EndOfRoundActivity.this, UpgradeScreenActivity.class);
                // Pass gameManager object to next screen
                battleIntent.putExtra("gameManager", gameManager);
                startActivity(battleIntent);
            }
        });
        mNextWaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent battleIntent = new Intent(EndOfRoundActivity.this, BattleScreenActivity.class);
                // Pass gameManager object to next screen
                battleIntent.putExtra("gameManager", gameManager);
                startActivity(battleIntent);
            }
        });
    }
    // Provides Fullscreen functionality, hiding menu bars at the top
    private void setToFullScreen(){
        ViewGroup statisticsScreen = (ViewGroup) findViewById(R.id.activity_end_of_round);
        statisticsScreen.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
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
    public void onBackPressed() {
        // super.onBackPressed(); // Comment this super call to avoid calling finish() or fragmentmanager's backstack pop operation.
    }
}
