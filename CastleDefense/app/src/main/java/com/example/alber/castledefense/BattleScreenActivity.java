package com.example.alber.castledefense;

import android.os.AsyncTask;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.view.ViewTreeObserver;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class BattleScreenActivity extends AppCompatActivity implements Enemy.EnemyListener,Projectile.ProjectileListener{

    private boolean isPaused;
    private boolean isWaveActive;

    private Button mPauseButton;
    private Button mExitButton;
    private Button mNextRoundButton;
    private Button mstartRoundButton;
    private TextView pauseText;
    private ViewGroup mContentView;
    private int mScreenWidth;
    private int mScreenHeight;
    private Button nextScreen;
    public static final int MIN_ANIMATION_DELAY = 500;
    public static final int MAX_ANIMATION_DELAY = 1000;
    public static final int MIN_ANIMATION_DURATION = 1000;
    public static final int MAX_ANIMATION_DURATION = 4000;
    private int mWave;
    private int mEnemiesKilled;
    private int[] yPositions = new int[3];
    private Intent intent;
    private GameManager gameManager;
    private Hero hero;

    private ArrayList enemyArray = new ArrayList<Enemy>();
    private ArrayList heroArrowArray = new ArrayList<Projectile>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_screen);

        isPaused = false;
        isWaveActive = false;
        mstartRoundButton = (Button) findViewById(R.id.start_wave);
        mPauseButton = (Button) findViewById(R.id.pause_button);
        mExitButton = (Button) findViewById(R.id.exit_button);
        mNextRoundButton = (Button) findViewById(R.id.next_round);
        pauseText = (TextView) findViewById(R.id.pause_text);
        getWindow().setBackgroundDrawableResource(R.drawable.temp_battle);
        mContentView =(ViewGroup) findViewById(R.id.battle_screen);
        setToFullScreen();

        // Get gameManager object from last screen
        intent = getIntent();
        this.gameManager = (GameManager) intent.getSerializableExtra("gameManager");

        this.hero = new Hero(this);
        this.gameManager.setHero(this.hero);
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
                        arrow.setX(mScreenWidth);
                        arrow.setY(motionEvent.getY());
                        mContentView.addView(arrow);
                        arrow.fireProjectile(mScreenWidth, 500, touchX);

                    }
                }
                return false;

            }
        });

    }

    boolean togglePause()
    {
        if(!isPaused)
        {
            isPaused = true;
            isWaveActive = false;
        }
        else
        {
            isPaused = false;
            isWaveActive = true;
        }

        return isPaused;
    }

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
        }
    }

    public void startButtonClickHandler(View view) {
        isWaveActive = true;
        startWave();
    }

    //@Override
    public void damageEnemy(Enemy enemy, boolean userTouch) {
        //mContentView.removeView(enemy);
        //f(userTouch){
            enemy.touchEvent(gameManager.getHero());

            if (enemy.isDead())
            {
                mEnemiesKilled++;
                mContentView.removeView(enemy);
                //gameManager.removeEnemy(enemy);
                gameManager.decreaseEnemies();
            }
       // }
        updateDisplay();
    }

    public void removeProjectile(Projectile projectile) {
        mContentView.removeView(projectile);
        updateDisplay();
    }

    public void removeEnemy(Enemy enemy) {
        mContentView.removeView(enemy);
        updateDisplay();
    }

    private void updateDisplay() {
        // nothing yet
    }

    private class EnemyLauncher extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected Void doInBackground(Integer... params) {

            if (params.length != 1) {
                throw new AssertionError(
                        "Expected 1 param for current level");
            }

            int level = params[0];
            //int maxDelay = Math.max(MIN_ANIMATION_DELAY,
            //        (MAX_ANIMATION_DELAY - ((level - 1) * 500)));
            int maxDelay = MAX_ANIMATION_DELAY;
            //int minDelay = maxDelay / 2;
            int minDelay = MIN_ANIMATION_DELAY;
            int enemiesLaunched = 0;
            yPositions[0] = mScreenHeight/8;
            yPositions[1] = 3*mScreenHeight/8;
            yPositions[2] = 5*mScreenHeight/8;
            while (enemiesLaunched < 5) {
                // Get a random vertical position for the next enemy
                Random random = new Random(new Date().getTime());
                //int yPosition = random.nextInt(mScreenHeight/3)+mScreenHeight/4;
                int yPosition = random.nextInt(3);
                publishProgress(yPositions[yPosition]);
                enemiesLaunched++;

                // Wait a random number of milliseconds before looping
                int delay = random.nextInt(maxDelay) + minDelay;
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

    private void launchEnemy(int y) {

        Enemy enemy = new Enemy(this, 0xFFFF0000, 150);

        // Set enemy vertical position and dimensions, add to container
        enemy.setX(0);
        //enemy.setY(mScreenHeight + enemy.getHeight());
        enemy.setY(y);
        mContentView.addView(enemy);

        // Move enemies
        Random random = new Random(new Date().getTime());
        //int duration = Math.max(MIN_ANIMATION_DURATION, MAX_ANIMATION_DURATION - (mWave * 1000));
        int duration = random.nextInt(MAX_ANIMATION_DURATION-MIN_ANIMATION_DURATION) + MIN_ANIMATION_DURATION;
        enemy.releaseEnemy(mScreenWidth - mScreenWidth/4, duration);
    }

    public void detectCollisions(ArrayList<Enemy> enemies, ArrayList<Projectile> projectiles)
    {
        for(Projectile projectile: projectiles)
        {
            for (Enemy enemy: enemies)
            {
                if(projectile.getX() > enemy.getX() && Math.abs(projectile.getY()-enemy.getY()) < 50)
                {
                    enemies.remove(enemy);
                    removeEnemy(enemy);
                }
            }
        }
    }

}