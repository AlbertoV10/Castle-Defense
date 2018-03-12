package com.example.alber.castledefense;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BattleScreenActivity extends AppCompatActivity {

    private boolean isPaused;

    private Button mPauseButton;
    private Button mExitButton;
    private Toast pausedToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_screen);

        isPaused = false;

        mPauseButton = (Button) findViewById(R.id.pause_button);
        mExitButton = (Button) findViewById(R.id.exit_button);

        mPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                togglePause();
                pausedToast = Toast.makeText(BattleScreenActivity.this, R.string.paused_toast, Toast.LENGTH_SHORT);

                if(isPaused)
                {
                    pausedToast.show();
                }
                else
                {
                    pausedToast.cancel();
                }
            }
        });

        mExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    boolean togglePause()
    {
        if(!isPaused)
        {
            isPaused = true;
        }
        else
        {
            isPaused = false;
        }

        return isPaused;
    }
}
