package com.example.alber.castledefense;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class LoadMenuActivity extends AppCompatActivity {

    private Button mFile1Button;
    private Button mFile2Button;
    private Button mFile3Button;
    private ViewGroup mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_menu);

        mContentView =(ViewGroup) findViewById(R.id.activity_load_menu);
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setToFullScreen();
            }
        });

        mFile1Button = (Button) findViewById(R.id.file_1_button);
        mFile2Button = (Button) findViewById(R.id.file_2_button);
        mFile3Button = (Button) findViewById(R.id.file_3_button);



        mFile1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoadMenuActivity.this, R.string.loading_toast, Toast.LENGTH_SHORT).show();
                Intent battleIntent = new Intent(LoadMenuActivity.this, BattleScreenActivity.class);
                startActivity(battleIntent);
            }
        });

        mFile2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoadMenuActivity.this, R.string.loading_toast, Toast.LENGTH_SHORT).show();
                Intent battleIntent = new Intent(LoadMenuActivity.this, BattleScreenActivity.class);
                startActivity(battleIntent);
            }
        });

        mFile3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoadMenuActivity.this, R.string.loading_toast, Toast.LENGTH_SHORT).show();
                Intent battleIntent = new Intent(LoadMenuActivity.this, BattleScreenActivity.class);
                startActivity(battleIntent);
            }
        });
    }

    private void setToFullScreen(){
        ViewGroup mainMenu = (ViewGroup) findViewById(R.id.activity_load_menu);
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
}
