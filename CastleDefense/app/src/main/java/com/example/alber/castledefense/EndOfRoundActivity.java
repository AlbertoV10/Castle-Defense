package com.example.alber.castledefense;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EndOfRoundActivity extends AppCompatActivity {

    private Button mUpgradeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mUpgradeButton = (Button) findViewById(R.id.to_upgrade_button);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_of_round);

        mUpgradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
