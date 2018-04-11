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

    private TextView HPDisplay, moneyDisplay;
    private TextView towerOneLevel, towerOnePrice;
    private TextView towerTwoLevel, towerTwoPrice;
    private TextView towerThreeLevel, towerThreePrice;
    private TextView townOneLevel, townOnePrice;
    private TextView townTwoLevel, townTwoPrice;
    private TextView townThreeLevel, townThreePrice;
    private TextView heroOneLevel, heroOnePrice;
    private TextView heroTwoLevel, heroTwoPrice;

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
                if(gameManager.getCurrentGold() < gameManager.getTowerUpgradePrice()) {
                    Toast.makeText(UpgradeScreenActivity.this, R.string.not_enough_money, Toast.LENGTH_SHORT).show();
                }
                else{
                    gameManager.setCurrentGold(gameManager.getCurrentGold() - gameManager.getTowerUpgradePrice());
                    for(int index = 0; index < gameManager.getTowers().length; index++)
                    {
                        gameManager.getTowers()[index].upgradeTower();
                        updateDisplay();
                    }
                }
            }
        });

        // Tower Upgrade 2
        upgradeTowerTwo=(ImageButton)findViewById(R.id.tower2);
        upgradeTowerTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gameManager.getCurrentGold() < gameManager.getTowerUpgradePrice()) {
                    Toast.makeText(UpgradeScreenActivity.this, R.string.not_enough_money, Toast.LENGTH_SHORT).show();
                }
                else{
                    // TODO
                    updateDisplay();
                }
            }
        });

        // Tower Upgrade 3
        upgradeTowerThree=(ImageButton)findViewById(R.id.tower3);
        upgradeTowerThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gameManager.getCurrentGold() < gameManager.getTowerUpgradePrice()) {
                    Toast.makeText(UpgradeScreenActivity.this, R.string.not_enough_money, Toast.LENGTH_SHORT).show();
                }
                else{
                    // TODO
                    updateDisplay();
                }
            }
        });

        // Town Upgrade 1, Income Upgrade
        upgradeTownOne=(ImageButton)findViewById(R.id.town1);
        upgradeTownOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gameManager.getCurrentGold() < gameManager.getTown().getIncomeUpgradePrice()) {
                    Toast.makeText(UpgradeScreenActivity.this, R.string.not_enough_money, Toast.LENGTH_SHORT).show();
                }
                else{
                    gameManager.setCurrentGold(gameManager.getCurrentGold() - gameManager.getTown().getIncomeUpgradePrice());
                    gameManager.getTown().upgradeIncome();
                    updateDisplay();
                }
            }
        });

        // Town Upgrade 2, Wall Upgrade
        upgradeTownTwo=(ImageButton)findViewById(R.id.town2);
        upgradeTownTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gameManager.getCurrentGold() < gameManager.getTown().getWallUpgradePrice()) {
                    Toast.makeText(UpgradeScreenActivity.this, R.string.not_enough_money, Toast.LENGTH_SHORT).show();
                }
                else{
                    gameManager.setCurrentGold(gameManager.getCurrentGold() - gameManager.getTown().getWallUpgradePrice());
                    gameManager.getTown().upgradeWall();
                    updateDisplay();
                }
            }
        });

        // Town Upgrade 3, Restore Wall Health
        upgradeTownThree=(ImageButton)findViewById(R.id.town3);
        upgradeTownThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gameManager.getCurrentGold() < gameManager.getTown().getRestoreHealthPrice()) {
                    Toast.makeText(UpgradeScreenActivity.this, R.string.not_enough_money, Toast.LENGTH_SHORT).show();
                }
                else if(gameManager.getTown().getWallHealth() == gameManager.getTown().getMaxWallHealth()){
                    Toast.makeText(UpgradeScreenActivity.this, R.string.already_max_health, Toast.LENGTH_SHORT).show();
                }
                else{
                    gameManager.setCurrentGold(gameManager.getCurrentGold() - gameManager.getTown().getRestoreHealthPrice());
                    gameManager.getTown().restoreHealth();
                    updateDisplay();
                }
            }
        });

        // Hero Upgrade 1
        upgradeHeroOne=(ImageButton)findViewById(R.id.hero1);
        upgradeHeroOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gameManager.getCurrentGold() < gameManager.getHero().getDamageUpgradePrice()) {
                    Toast.makeText(UpgradeScreenActivity.this, R.string.not_enough_money, Toast.LENGTH_SHORT).show();
                }
                else{
                    gameManager.setCurrentGold(gameManager.getCurrentGold() - gameManager.getHero().getDamageUpgradePrice());
                    gameManager.getHero().upgradeHero();
                    updateDisplay();
                }
            }
        });

        // Hero Upgrade 2
        upgradeHeroTwo=(ImageButton)findViewById(R.id.hero2);
        upgradeHeroTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gameManager.getCurrentGold() < gameManager.getHero().getDamageUpgradePrice()) {
                    Toast.makeText(UpgradeScreenActivity.this, R.string.not_enough_money, Toast.LENGTH_SHORT).show();
                }
                else{
                    // TODO
                    updateDisplay();
                }
            }
        });

        // assign TextViews
        moneyDisplay = (TextView) findViewById(R.id.money_text);
        HPDisplay = (TextView) findViewById(R.id.textView2);

        towerOneLevel = (TextView) findViewById(R.id.textView6);
        towerOnePrice = (TextView) findViewById(R.id.textView7);
        towerTwoLevel = (TextView) findViewById(R.id.textView10);
        towerTwoPrice = (TextView) findViewById(R.id.textView12);
        towerThreeLevel = (TextView) findViewById(R.id.textView13);
        towerThreePrice = (TextView) findViewById(R.id.textView14);

        townOneLevel = (TextView) findViewById(R.id.textView15);
        townOnePrice = (TextView) findViewById(R.id.textView16);
        townTwoLevel = (TextView) findViewById(R.id.textView17);
        townTwoPrice = (TextView) findViewById(R.id.textView18);
        townThreeLevel = (TextView) findViewById(R.id.textView8);
        townThreePrice = (TextView) findViewById(R.id.textView9);

        heroOneLevel = (TextView) findViewById(R.id.textView19);
        heroOnePrice = (TextView) findViewById(R.id.textView20);
        heroTwoLevel = (TextView) findViewById(R.id.textView21);
        heroTwoPrice = (TextView) findViewById(R.id.textView22);

        updateDisplay();
    }

    private void updateDisplay() {
        moneyDisplay.setText("Money: " + String.valueOf(gameManager.getCurrentGold()));
        HPDisplay.setText("HP: " + String.valueOf(gameManager.getTown().getWallHealth()) + "/" +String.valueOf(gameManager.getTown().getMaxWallHealth()));

        towerOneLevel.setText("Level: " + String.valueOf(gameManager.getTowers()[0].getUpgradeLevel()));
        towerOnePrice.setText("Cost: " + String.valueOf(gameManager.getTowers()[0].getUpgradeCost()));
        // IMPLEMENT LATER
        //towerTwoLevel.setText("Level: " + String.valueOf(gameManager.getTowers()[1].getUpgradeLevel()));
        //towerTwoPrice.setText("Cost: " + String.valueOf(gameManager.getTowers()[1].getUpgradeCost()));
        //towerThreeLevel.setText("Level: " + String.valueOf(gameManager.getTowers()[2].getUpgradeLevel()));
        //towerThreePrice.setText("Cost: " + String.valueOf(gameManager.getTowers()[2].getUpgradeCost()));
        // PLACEHOLDER
        towerTwoLevel.setText("Level: ?");
        towerTwoPrice.setText("Cost: ?");
        towerThreeLevel.setText("Level: ?");
        towerThreePrice.setText("Cost: ?");

        townOneLevel.setText("Level: " + String.valueOf(gameManager.getTown().getIncomeLevel()));
        townOnePrice.setText("Cost: " + String.valueOf(gameManager.getTown().getIncomeUpgradePrice()));
        townTwoLevel.setText("Level: " + String.valueOf(gameManager.getTown().getWallLevel()));
        townTwoPrice.setText("Cost: " + String.valueOf(gameManager.getTown().getWallUpgradePrice()));
        townThreeLevel.setText("Level: N/A");
        townThreePrice.setText("Cost: " + String.valueOf(gameManager.getTown().getRestoreHealthPrice()));

        heroOneLevel.setText("Level: " + String.valueOf(gameManager.getHero().getDamageLevel()));
        heroOnePrice.setText("Cost: " + String.valueOf(gameManager.getHero().getDamageUpgradePrice()));
        // IMPLEMENT LATER
        //heroTwoLevel.setText("Level: " + String.valueOf(gameManager.getHero().getDamageLevel()));
        //heroTwoPrice.setText("Cost: " + String.valueOf(gameManager.getHero().getDamageUpgradePrice()));
        // PLACEHOLDER
        heroTwoLevel.setText("Level: ?");
        heroTwoPrice.setText("Cost: ?");
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
