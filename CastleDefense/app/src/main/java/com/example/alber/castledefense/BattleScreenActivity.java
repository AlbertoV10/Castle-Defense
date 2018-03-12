package com.example.alber.castledefense;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BattleScreenActivity extends AppCompatActivity {

    private boolean isPaused;

    private Button mPauseButton;
    private Button mExitButton;
    private Button mnextRound;
    private TextView pauseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_screen);

        isPaused = false;

        mPauseButton = (Button) findViewById(R.id.pause_button);
        mExitButton = (Button) findViewById(R.id.exit_button);
        pauseText = (TextView) findViewById(R.id.pause_text);

        mPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                togglePause();

                if(isPaused)
                {
                    pauseText.setAlpha(1);
                    mPauseButton.setText("Resume");
                }
                else
                {
                    pauseText.setAlpha(0);
                    mPauseButton.setText("Pause");
                }
            }
        });

        mExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent battleIntent = new Intent(BattleScreenActivity.this, StartActivity.class);
                startActivity(battleIntent);
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
