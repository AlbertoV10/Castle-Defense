package com.example.alber.castledefense;

import android.text.Layout;

import com.example.alber.castledefense.Enemy;

/**
 * Created by ryan.torres097 on 3/28/18.
 */

public class GameManager {
    Enemy enemies[];
    Tower towers[];
    //Town town;
    //Hero hero;
    int numOfEnemies;
    int currentWave;
    int remainingEnemies;
    int currentGold;
    Layout layout;
    
    public GameManager(Layout layout)
    {
        this.layout = layout;
        createEnemies();
        createHero();
        this.currentWave = 1;
        this.numOfEnemies = 10;
        this.remainingEnemies = 10;
        this.currentGold = 0;
    }

    public Enemy[] getEnemy()
    {
        return this.enemies;
    }

    public Tower[] getTowers()
    {
        return this.towers;
    }

    /*
    public hero getHero()
    {
        return this.hero;
    }*/

    /*
    public getUpgrades()
    {

    }*/

    public int getNumOfEnemies()
    {
        return this.numOfEnemies;
    }

    public int getRemainingEnemies()
    {
        return this.remainingEnemies;
    }

    public int getCurrentWave()
    {
        return this.currentWave;
    }

    public int getCurrentGold()
    {
        return this.currentGold;
    }

    public void addTower(Tower tower)
    {

    }

    public void increaseWave()
    {
        this.currentWave++;
    }

    public void decreaseEnemies()
    {
        this.remainingEnemies--;
    }

    public void createHero()
    {

    }

    public void createEnemies()
    {

    }


}
