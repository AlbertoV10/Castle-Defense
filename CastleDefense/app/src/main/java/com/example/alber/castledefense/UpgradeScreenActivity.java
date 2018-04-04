package com.example.alber.castledefense;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import java.io.Serializable;

public class UpgradeScreenActivity extends AppCompatActivity {

    private ViewGroup mContentView;
    private Button nextScreen;
    private Intent intent;
    private GameManager gameManager;
    private ImageButton upgradeTowerOne;
    private ImageButton upgradeTowerTwo;
    private ImageButton upgradeTowerThree;
    private ImageButton upgradeTownOne;
    private ImageButton upgradeTownTwo;
    private ImageButton upgradeTownThree;
    private ImageButton upgradeHeroOne;
    private ImageButton upgradeHeroTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_screen);

        getWindow().setBackgroundDrawableResource(R.drawable.temp_upgrade);

        // Get gameManager object from last screen
        intent = getIntent();
        this.gameManager = (GameManager) intent.getSerializableExtra("gameManager");

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
                // Pass gameManager object to next screen
                androidsolved_intent.putExtra("gameManager", gameManager);
                startActivity(androidsolved_intent);
            }
        });

        // Tower Upgrade 1
        upgradeTowerOne=(ImageButton)findViewById(R.id.tower1);
        upgradeTowerOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gameManager.getCurrentGold() < gameManager.getTowerUpgradePrice(0)) {
                    Toast.makeText(UpgradeScreenActivity.this, R.string.not_enough_money, Toast.LENGTH_SHORT).show();
                }
                else{
                    // TODO
                }
            }
        });

        // Tower Upgrade 2
        upgradeTowerTwo=(ImageButton)findViewById(R.id.tower2);
        upgradeTowerTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gameManager.getCurrentGold() < gameManager.getTowerUpgradePrice(0)) {
                    Toast.makeText(UpgradeScreenActivity.this, R.string.not_enough_money, Toast.LENGTH_SHORT).show();
                }
                else{
                    // TODO
                }
            }
        });

        // Tower Upgrade 3
        upgradeTowerThree=(ImageButton)findViewById(R.id.tower3);
        upgradeTowerThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gameManager.getCurrentGold() < gameManager.getTowerUpgradePrice(0)) {
                    Toast.makeText(UpgradeScreenActivity.this, R.string.not_enough_money, Toast.LENGTH_SHORT).show();
                }
                else{
                    // TODO
                }
            }
        });

        // Town Upgrade 1, Income Upgrade
        upgradeTownOne=(ImageButton)findViewById(R.id.town1);
        upgradeTownOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gameManager.getCurrentGold() < gameManager.getTownIncomeUpgradePrice()) {
                    Toast.makeText(UpgradeScreenActivity.this, R.string.not_enough_money, Toast.LENGTH_SHORT).show();
                }
                else{
                    // TODO
                }
            }
        });

        // Town Upgrade 2, Wall Upgrade
        upgradeTownTwo=(ImageButton)findViewById(R.id.town2);
        upgradeTownTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gameManager.getCurrentGold() < gameManager.getTownWallUpgradePrice()) {
                    Toast.makeText(UpgradeScreenActivity.this, R.string.not_enough_money, Toast.LENGTH_SHORT).show();
                }
                else{
                    // TODO
                }
            }
        });

        // Town Upgrade 3, Restore Wall Health
        upgradeTownThree=(ImageButton)findViewById(R.id.town3);
        upgradeTownThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gameManager.getCurrentGold() < gameManager.getTownRestoreHealthPrice()) {
                    Toast.makeText(UpgradeScreenActivity.this, R.string.not_enough_money, Toast.LENGTH_SHORT).show();
                }
                else{
                    // TODO
                }
            }
        });

        // Hero Upgrade 1, Increase Base Damage
        upgradeHeroOne=(ImageButton)findViewById(R.id.hero1);
        upgradeHeroOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gameManager.getCurrentGold() < gameManager.getHeroDamageUpgradePrice()) {
                    Toast.makeText(UpgradeScreenActivity.this, R.string.not_enough_money, Toast.LENGTH_SHORT).show();
                }
                else{
                    // TODO
                }
            }
        });

        // Hero Upgrade 2, Increase Piercing
        upgradeHeroTwo=(ImageButton)findViewById(R.id.hero2);
        upgradeHeroTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gameManager.getCurrentGold() < gameManager.getHeroPiercingUpgradePrice()) {
                    Toast.makeText(UpgradeScreenActivity.this, R.string.not_enough_money, Toast.LENGTH_SHORT).show();
                }
                else{
                    // TODO
                }
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
