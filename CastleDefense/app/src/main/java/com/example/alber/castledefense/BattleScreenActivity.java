package com.example.alber.castledefense;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.view.ViewTreeObserver;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class BattleScreenActivity extends AppCompatActivity implements EnemySprite.EnemyListener,Projectile.ProjectileListener{
    public Handler mHandler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            detectCollisions(enemyArray, heroArrowArray);
            isEnemyAttacking(enemyArray);
            damageCastle(acidBulletsArray);
        }
    };

    /*
    public Handler mBulletHandler = new Handler()
    {
        public void handleMessage(Message msg)
        {
        //
        }
    };
    */

    //private boolean isPaused;
    private boolean isWaveActive;
    // need more that 2 states for shooting, beginning (waits for wave to start), shooting, end wave (stop shooting)
    private int isShooting = 0;

    //private Button mPauseButton;
    private Button mExitButton;
    private Button mNextRoundButton;
    private Button mstartRoundButton;
    //private TextView pauseText;
    private ViewGroup mContentView;
    private int mScreenWidth;
    private int mScreenHeight;
    private int BASE_ENEMY_SPEED = 3000; // milliseconds seconds to travel across the screen
    private int ENEMY_SPAWN_RATE = 1000; // milliseconds between spawns
    private int mWave;
    private int[] yPositions = new int[3];
    private Intent intent;
    private GameManager gameManager;
    private TextView waveDisplay, EnemyCountDisplay, moneyDisplay, HPDisplay;

    private ArrayList enemyArray = new ArrayList<EnemySprite>();
    private ArrayList heroArrowArray = new ArrayList<Projectile>();
    private ArrayList acidBulletsArray = new ArrayList<EnemyProjectile>();
    MediaPlayer arrowSound;
    MediaPlayer punch;

    // Create local hero sprite, retrieve stats from game manager
    private HeroSprite hero;
    // Create local tower sprites, retrieve stats from game manager
    private TowerSprite towerOne;
    private TowerSprite towerTwo;
    private TowerSprite towerThree;
    private boolean roundWon;

    private TextView lossText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_screen);

        //isPaused = false;
        isWaveActive = false;
        mstartRoundButton = (Button) findViewById(R.id.start_wave);
        //mPauseButton = (Button) findViewById(R.id.pause_button);
        mExitButton = (Button) findViewById(R.id.exit_button);
        mNextRoundButton = (Button) findViewById(R.id.next_round);
        //pauseText = (TextView) findViewById(R.id.pause_text);
        getWindow().setBackgroundDrawableResource(R.drawable.temp_battle);
        mContentView =(ViewGroup) findViewById(R.id.battle_screen);
        arrowSound =  MediaPlayer.create(BattleScreenActivity.this, R.raw.arrowsound);
        punch = MediaPlayer.create(BattleScreenActivity.this, R.raw.punch);
        setToFullScreen();
        this.roundWon = false;

        // Get gameManager object from last screen
        intent = getIntent();
        this.gameManager = (GameManager) intent.getSerializableExtra("gameManager");
        gameManager.increaseWave();
        gameManager.setRemainingEnemies(5 + (3 * gameManager.getCurrentWave()) );
        gameManager.setNumOfEnemies(5 + (3 * gameManager.getCurrentWave()) );
        this.gameManager.newWave();

        ViewTreeObserver viewTreeObserver = mContentView.getViewTreeObserver();
        if(viewTreeObserver.isAlive())
        {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    mContentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    mScreenWidth = mContentView.getWidth();
                    mScreenHeight = mContentView.getHeight();
                }
            });
        }

        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setToFullScreen();
            }
        });

//        mPauseButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                togglePause();
//
//                if(isPaused)
//                {
//                    pauseText.setAlpha(1);
//                    mPauseButton.setText("Resume");
//                }
//                else
//                {
//                    pauseText.setAlpha(0);
//                    mPauseButton.setText("Pause");
//                }
//            }
//        });

        mExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent battleIntent = new Intent(BattleScreenActivity.this, StartActivity.class);
                startActivity(battleIntent);
            }
        });

        mNextRoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent battleEndIntent = new Intent(BattleScreenActivity.this, EndOfRoundActivity.class);
                // Pass gameManager object to next screen
                battleEndIntent.putExtra("gameManager", gameManager);
                startActivity(battleEndIntent);
            }
        });

        // create a projectile on touch
        mContentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(isWaveActive) {
                    int touchX = (int)motionEvent.getX();
                    int touchY = (int)motionEvent.getY();
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        Projectile arrow = new Projectile(BattleScreenActivity.this, 0x000000, 128);
                        arrowSound.start();
                        arrow.setX(mScreenWidth);
                        arrow.setY(motionEvent.getY()-64);
                        arrow.setImageResource(R.drawable.arrow_hero);
                        mContentView.addView(arrow);
                        //arrow.fireProjectile(mScreenWidth, 500, touchX);
                        heroArrowArray.add(arrow);
                        arrow.fireProjectile(mScreenWidth, 800, touchX);
                    }
                }
                return false;

            }
        });

        spawnTowers();
        waveDisplay = (TextView) findViewById(R.id.wave_text);
        waveDisplay.setText("Wave: " + gameManager.getCurrentWave());
        EnemyCountDisplay = (TextView) findViewById(R.id.enemies_text);
        moneyDisplay = (TextView) findViewById(R.id.money_text);
        HPDisplay = (TextView) findViewById(R.id.HP_text);

        lossText = (TextView) findViewById(R.id.loss_text);

        updateDisplay();
        startTimerForCollisions();
    }

    void startTimerForCollisions()
    {
        TimerTask collisions = new TimerTask() {
            //@Override
            public void run() {
                mHandler.obtainMessage(1).sendToTarget();
            }
        };

        Timer timer = new Timer();
        timer.schedule(collisions, 10, 10);
    }

    /*
    void timerForEnemyBullets()
    {
        TimerTask bullets = new TimerTask() {
            //@Override
            public void run() {
                mBulletHandler.obtainMessage(1).sendToTarget();
            }
        };
    }
    */


//    boolean togglePause()
//    {
//        if(!isPaused)
//        {
//            isPaused = true;
//            isWaveActive = false;
//        }
//        else
//        {
//            isPaused = false;
//            isWaveActive = true;
//        }
//
//        return isPaused;
//    }

    // Provides Fullscreen functionality, hiding menu bars at the top
    private void setToFullScreen(){
        ViewGroup battleScreen = (ViewGroup) findViewById(R.id.battle_screen);
        battleScreen.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
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

    private void startWave(){
        if(!gameManager.waveStarted())
        {
            mWave++;
            EnemyLauncher launcher = new EnemyLauncher();
            launcher.execute(mWave);
            gameManager.startWave();
            mstartRoundButton.setAlpha(0);

            // start tower shooting
            isShooting = 1;
            startTower(towerOne,2*mScreenHeight/10, (mScreenWidth - 2*mScreenWidth/10));
            startTower(towerTwo,4*mScreenHeight/10, (mScreenWidth - 2*mScreenWidth/10));
            startTower(towerThree,6*mScreenHeight/10, (mScreenWidth - 2*mScreenWidth/10));
            //mPauseButton.setClickable(true);
            //mPauseButton.setAlpha(1);
        }
    }

    public void startButtonClickHandler(View view) {
        isWaveActive = true;
        startWave();
    }

    //@Override
    public void damageEnemy(EnemySprite enemy, Projectile projectile) {
        //mContentView.removeView(enemy);
        //f(userTouch){
        int startingHealth = enemy.healthRemaining();
        if(projectile.getProjectileType() == 0)
        {
            enemy.touchEvent(gameManager.getHero()); // do hero arrow damage
        }
        else
        {
            enemy.projectileCollision(projectile); // do tower arrow damage
        }
        //enemy.updateHealthbar();
            if (enemy.isDead() && startingHealth > 0 && enemy.healthRemaining() <= 0)
            {
                gameManager.setEnemiesKilled(gameManager.getEnemiesKilled()+1);
                mContentView.removeView(enemy);
                //gameManager.removeEnemy(enemy);
                gameManager.decreaseEnemies();
                gameManager.setCurrentGold(gameManager.getCurrentGold() + 10);
            }
       // }
        updateDisplay();
    }

    public void removeProjectile(Projectile projectile) {
        mContentView.removeView(projectile);
        updateDisplay();
    }

    public void removeEnemy(EnemySprite enemy) {
        mContentView.removeView(enemy);
        updateDisplay();
    }

    public void removeEnemyBullet(EnemyProjectile enemyProjectile)
    {
        enemyProjectile.cancelAnimation();
        mContentView.removeView(enemyProjectile);
        updateDisplay();
    }

    private void updateDisplay() {
        waveDisplay.setText("Wave: " + String.valueOf(gameManager.getCurrentWave()));
        EnemyCountDisplay.setText("Enemies: " + String.valueOf(gameManager.getRemainingEnemies()));
        moneyDisplay.setText("Money: " + String.valueOf(gameManager.getCurrentGold()));
        HPDisplay.setText("HP: " + String.valueOf(gameManager.getTown().getWallHealth()) + "/" + String.valueOf(gameManager.getTown().getMaxWallHealth()));
        if(gameManager.getRemainingEnemies() == 0)
        {
            isShooting = 2;
        }
    }

    private class EnemyLauncher extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected Void doInBackground(Integer... params) {

            if (params.length != 1) {
                throw new AssertionError(
                        "Expected 1 param for current level");
            }

            int level = params[0]; // needed?
            int enemiesLaunched = 0;
            yPositions[0] = 2*mScreenHeight/10;
            yPositions[1] = 4*mScreenHeight/10;
            yPositions[2] = 6*mScreenHeight/10;
            while (enemiesLaunched < gameManager.getNumOfEnemies()) {
                // Get a random vertical position for the next enemy
                Random random = new Random(new Date().getTime());
                //int yPosition = random.nextInt(mScreenHeight/3)+mScreenHeight/4;
                int yPosition = random.nextInt(3);
                publishProgress(yPositions[yPosition]);
                enemiesLaunched++;

                //// Wait a random number of milliseconds before looping
                //int delay = random.nextInt(maxDelay) + minDelay;
                int delay = random.nextInt(ENEMY_SPAWN_RATE) + 500;
                //int delay = ENEMY_SPAWN_RATE;
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int yPosition = values[0];
            launchEnemy(yPosition);
        }
    }

    private void spawnTowers()
    {
        // For some reason, the mScreenWidth and mScreenHeight didn't work, so just use another method
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        // Create local hero sprite, retrieve stats from game manager
        hero = new HeroSprite(this, 0xFFFF00, 150);
        hero.setHero(gameManager.getHero());

        // Create local tower sprites, retrieve stats from game manager
        towerOne = new TowerSprite(this, 0xFFFF00, 150);
        towerOne.setTower(gameManager.getTowers()[0]);

        towerTwo = new TowerSprite(this, 0xFFFF00, 150);
        towerTwo.setTower(gameManager.getTowers()[1]);

        towerThree = new TowerSprite(this, 0xFFFF00, 150);
        towerThree.setTower(gameManager.getTowers()[2]);

        // set tower positions
        towerOne.setX(width - 2*width/10);
        towerOne.setY(2*height/10);
        towerTwo.setX(width - 2*width/10);
        towerTwo.setY(4*height/10);
        towerThree.setX(width - 2*width/10);
        towerThree.setY(6*height/10);

        // spawn towers
        mContentView.addView(towerOne);
        mContentView.addView(towerTwo);
        mContentView.addView(towerThree);
    }

    private void startTower(final TowerSprite tower, int height, int width)
    {

        final TowerSprite innerTower = tower;
        final int innerHeight = height;
        final int innerWidth = width;
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run()
            {
                if(isShooting == 1)
                {
                    runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            // stuff that updates ui
                            Projectile arrow = new Projectile(BattleScreenActivity.this, 0xFF606060, 128);
                            arrow.setX(innerWidth);
                            arrow.setY(innerHeight);
                            // retrieve tower arrow damage from the tower
                            arrow.setPiercingValue(innerTower.getTower().getArmorPiercing());
                            arrow.setDamage(innerTower.getTower().getDamage());
                            arrow.setProjectileType(1);
                            arrow.setImageResource(R.drawable.arrow_tower);
                            arrowSound.start();
                            mContentView.addView(arrow);
                            heroArrowArray.add(arrow);
                            arrow.fireProjectile(innerWidth, 2000, 0);
                        }
                    });
                }
                else if(isShooting == 2)
                {
                    timer.cancel();
                    timer.purge();
                    endRound();
                }
            }
        }, 100, tower.getTower().getRateOfFire());
    }

    private void launchEnemy(int y) {

        EnemySprite enemy = new EnemySprite(this, 0xFFFF0000, 150, mScreenWidth);

        // Set enemy vertical position and dimensions, add to container
        enemy.setX(0);
        //enemy.setY(mScreenHeight + enemy.getHeight());
        enemy.setY(y);
        enemy.getEnemy().setHealthRemaining(20 + (20 * gameManager.getCurrentWave()) );
        enemy.getEnemy().setArmor(.1 + (.1 * (gameManager.getCurrentWave()/4)) );
        mContentView.addView(enemy);

        // Move enemies
        Random random = new Random(new Date().getTime());
        // Uniform speed
        //int duration = random.nextInt(MAX_ANIMATION_DURATION-MIN_ANIMATION_DURATION) + MIN_ANIMATION_DURATION;
        enemyArray.add(enemy);
        int travelTime = BASE_ENEMY_SPEED - (100 * gameManager.getCurrentWave());
        if(travelTime < 1000)
        {
            travelTime = 1000;
        }
        enemy.releaseEnemy(mScreenWidth - mScreenWidth/4, travelTime);
    }

    public void detectCollisions(ArrayList<EnemySprite> enemies, ArrayList<Projectile> projectiles)
    {
        for(int currentProjectile = 0; currentProjectile < projectiles.size(); currentProjectile++)
        {
            boolean hit = false;
            for (int currentEnemy = 0; currentEnemy < enemies.size(); currentEnemy++)
            {

                if(Math.abs(projectiles.get(currentProjectile).getX()-enemies.get(currentEnemy).getX()) < 50 && Math.abs(projectiles.get(currentProjectile).getY()-enemies.get(currentEnemy).getY()) < 100)
                {
                    damageEnemy(enemies.get(currentEnemy),projectiles.get(currentProjectile));
                    punch.start();
                    if (enemies.get((currentEnemy)).isDead())
                    {
                        enemies.remove(currentEnemy);
                        currentEnemy--;
                    }
                    hit = true;

                }
            }

            if(hit || projectiles.get(currentProjectile).getX() <= 0)
            {
                removeProjectile(projectiles.get(currentProjectile));
                projectiles.remove(currentProjectile);
                currentProjectile--;
            }
        }
    }

    public void isEnemyAttacking(ArrayList<EnemySprite> enemies)
    {
        for(int currentEnemy = 0; currentEnemy < enemies.size(); currentEnemy++)
        {
            if(enemies.get(currentEnemy).checkForAttack())
            {
                EnemyProjectile acidBullet = new EnemyProjectile(BattleScreenActivity.this, 0x000000, 64);
                acidBullet.setX(enemies.get(currentEnemy).getX());
                acidBullet.setY(enemies.get(currentEnemy).getY());
                acidBullet.setDamage(3 + (3 * gameManager.getCurrentWave()/2));
                mContentView.addView(acidBullet);
                //arrow.fireProjectile(mScreenWidth, 500, touchX);
                acidBulletsArray.add(acidBullet);
                acidBullet.fireProjectile(mScreenWidth, 800, enemies.get(currentEnemy).getX());
            }
        }
    }

    public void damageCastle(ArrayList<EnemyProjectile> acidBullets)
    {
        for(int currentBullet = 0; currentBullet < acidBullets.size(); currentBullet++)
        {
            if(acidBullets.get(currentBullet).getX() >= (mScreenWidth - 300))
            {
                gameManager.getTown().setWallHealth(gameManager.getTown().getWallHealth() - acidBullets.get(currentBullet).getDamage());
                if(gameManager.getTown().getWallHealth() <= 0)

                {
                    youLose();
                }

                removeEnemyBullet(acidBullets.get(currentBullet));
                acidBullets.remove(currentBullet);
                currentBullet--;
            }
        }
    }

    private void endRound()
    {
        //arrowSound =  MediaPlayer.create(BattleScreenActivity.this,R.raw.arrowsound);
        MediaPlayer applause = MediaPlayer.create(BattleScreenActivity.this, R.raw.applause);
        applause.start();
        mNextRoundButton.setAlpha(1);
        mNextRoundButton.setClickable(true);
        mExitButton.setAlpha(1);
        mExitButton.setClickable(true);
    }

    public void youLose()

    {

        isWaveActive = false;

        isShooting = 2;

        gameManager.getTown().setWallHealth(0);

        gameManager.setCurrentGold(gameManager.getCurrentGold());

        mExitButton.setAlpha(1);

        mExitButton.setClickable(true);

        mNextRoundButton.setAlpha(0);

        lossText.setAlpha(1);

        lossText.bringToFront();



    }


}