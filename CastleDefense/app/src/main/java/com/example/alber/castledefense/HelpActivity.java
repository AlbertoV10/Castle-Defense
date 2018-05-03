package com.example.alber.castledefense;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.Button;

public class HelpActivity extends AppCompatActivity {

    private ViewGroup mContentView;
    private Button mBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        //getWindow().setBackgroundDrawableResource(R.drawable.temp_statistics);

        mContentView = (ViewGroup) findViewById(R.id.activity_help);
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setToFullScreen();
            }
        });

        mBackButton = (Button) findViewById(R.id.button2);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(HelpActivity.this, StartActivity.class);
                startActivity(mainIntent);
            }
        });
    }

    // Provides Fullscreen functionality, hiding menu bars at the top
    private void setToFullScreen(){
        ViewGroup statisticsScreen = (ViewGroup) findViewById(R.id.activity_help);
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
}
