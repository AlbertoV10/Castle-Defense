package com.example.alber.castledefense;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.Button;

public class UpgradeScreenActivity extends AppCompatActivity {

    private ViewGroup mContentView;
    private Button nextScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_screen);

        getWindow().setBackgroundDrawableResource(R.drawable.temp_upgrade);

        mContentView =(ViewGroup) findViewById(R.id.activity_upgrade_screen);

        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setToFullScreen();
            }
        });

        // assigns start button to proceed to battle screen
        nextScreen=(Button)findViewById(R.id.button);
        nextScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent androidsolved_intent = new Intent(getApplicationContext(), BattleScreenActivity.class);
                startActivity(androidsolved_intent);
            }
        });

    }

    // Provides Fullscreen functionality, hiding menu bars at the top
    private void setToFullScreen(){
        ViewGroup upgradeScreen = (ViewGroup) findViewById(R.id.activity_upgrade_screen);
        upgradeScreen.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
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
