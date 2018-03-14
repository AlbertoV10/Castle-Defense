package com.example.alber.castledefense;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.app.Activity;

public class StartActivity
        extends AppCompatActivity {

    private ViewGroup mContentView;
    private Button mStartButton;
    private Button mLoadButton;

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

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Toast.makeText(StartActivity.this, R.string.loading_toast, Toast.LENGTH_SHORT).show();
            Intent battleIntent = new Intent(StartActivity.this, BattleScreenActivity.class);
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
