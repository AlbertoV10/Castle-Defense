package com.example.alber.castledefense;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.media.AudioManager;
import android.media.SoundPool;

import java.io.Serializable;

public class UpgradeScreenActivity extends AppCompatActivity {

    private ViewGroup mContentView;
    private Button nextScreen;
    private Intent intent;
    private GameManager gameManager;
    private ImageButton upgradeTowerOne;
    private ImageButton upgradeTownOne;
    private ImageButton upgradeHeroOne;

    private TextView HPDisplay, moneyDisplay;
    private TextView towerOneLevel, towerOnePrice;
    private TextView townOneLevel, townOnePrice;
    private TextView heroOneLevel, heroOnePrice;

    private SoundPool spend;
    private int spendSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_screen);

        //getWindow().setBackgroundDrawableResource(R.drawable.temp_upgrade);

        spend = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        //spend.setOnLoadCompleteListener(this);
        spendSound = spend.load(this, R.raw.spend_money, 1);

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
                spend.release();
                Intent androidsolved_intent = new Intent(getApplicationContext(), BattleScreenActivity.class);
                // Pass gameManager object to next screen
                androidsolved_intent.putExtra("gameManager", gameManager);
                startActivity(androidsolved_intent);
            }
        });

        // Towers Upgrade
        upgradeTowerOne=(ImageButton)findViewById(R.id.tower1);
        upgradeTowerOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gameManager.getCurrentGold() < gameManager.getTowerUpgradePrice()) {
                    Toast.makeText(UpgradeScreenActivity.this, R.string.not_enough_money, Toast.LENGTH_SHORT).show();
                    upgradeTowerOne.setClickable(false);
                }
                else{
                    gameManager.setCurrentGold(gameManager.getCurrentGold() - gameManager.getTowerUpgradePrice());
                    spend.play(spendSound,1,1,1,0,1);
                    for(int index = 0; index < gameManager.getTowers().length; index++)
                    {
                        gameManager.getTowers()[index].upgradeTower();
                        updateDisplay();
                    }
                }
            }
        });

        // Town Upgrade, Upgrade Wall HP
        upgradeTownOne=(ImageButton)findViewById(R.id.town1);
        upgradeTownOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gameManager.getCurrentGold() < gameManager.getTown().getWallUpgradePrice()) {
                    Toast.makeText(UpgradeScreenActivity.this, R.string.not_enough_money, Toast.LENGTH_SHORT).show();
                    upgradeTownOne.setClickable(false);
                }
                else{
                    gameManager.setCurrentGold(gameManager.getCurrentGold() - gameManager.getTown().getWallUpgradePrice());
                    spend.play(spendSound,1,1,1,0,1);
                    gameManager.getTown().upgradeWall();
                    updateDisplay();
                }
            }
        });

        // Hero Damage Upgrade
        upgradeHeroOne=(ImageButton)findViewById(R.id.hero1);
        upgradeHeroOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gameManager.getCurrentGold() < gameManager.getHero().getDamageUpgradePrice()) {
                    Toast.makeText(UpgradeScreenActivity.this, R.string.not_enough_money, Toast.LENGTH_SHORT).show();
                    upgradeHeroOne.setClickable(false);
                }
                else{
                    gameManager.setCurrentGold(gameManager.getCurrentGold() - gameManager.getHero().getDamageUpgradePrice());
                    spend.play(spendSound,1,1,1,0,1);
                    gameManager.getHero().upgradeHero();
                    updateDisplay();
                }
            }
        });


        // assign TextViews
        moneyDisplay = (TextView) findViewById(R.id.money_text);
        HPDisplay = (TextView) findViewById(R.id.textView2);

        towerOneLevel = (TextView) findViewById(R.id.textView6);
        towerOnePrice = (TextView) findViewById(R.id.textView7);

        townOneLevel = (TextView) findViewById(R.id.textView15);
        townOnePrice = (TextView) findViewById(R.id.textView16);

        heroOneLevel = (TextView) findViewById(R.id.textView19);
        heroOnePrice = (TextView) findViewById(R.id.textView20);

        updateDisplay();
    }

    private void updateDisplay() {
        moneyDisplay.setText("Money: " + String.valueOf(gameManager.getCurrentGold()));
        HPDisplay.setText("HP: " + String.valueOf(gameManager.getTown().getWallHealth()) + "/" + String.valueOf(gameManager.getTown().getMaxWallHealth()));

        towerOneLevel.setText("Level: " + String.valueOf(gameManager.getTowers()[0].getUpgradeLevel()));
        towerOnePrice.setText("Cost: " + String.valueOf(gameManager.getTowers()[0].getUpgradeCost()));

        townOneLevel.setText("Level: " + String.valueOf(gameManager.getTown().getWallLevel()));
        townOnePrice.setText("Cost: " + String.valueOf(gameManager.getTown().getWallUpgradePrice()));

        heroOneLevel.setText("Level: " + String.valueOf(gameManager.getHero().getDamageLevel()));
        heroOnePrice.setText("Cost: " + String.valueOf(gameManager.getHero().getDamageUpgradePrice()));
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
