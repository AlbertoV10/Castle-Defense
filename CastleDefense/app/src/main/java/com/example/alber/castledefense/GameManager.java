package com.example.alber.castledefense;

import android.text.Layout;

import com.example.alber.castledefense.Enemy;

/**
 * Created by ryan.torres097 on 3/28/18.
 */

public class GameManager {
    Enemy enemies[];
    Tower towers[];
    Town town;
    Hero hero;
    int numOfEnemies;
    int currentWave;
    int remainingEnemies;
    int currentGold;
    Layout layout;

    public GameManager()
    {
        this.layout = layout;
        //createEnemies();
        //createHero();
        //createTown();
        this.currentWave = 1;
        this.numOfEnemies = 10;
        this.remainingEnemies = 10;     //Mock data
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


    public Hero getHero()
    {
        return this.hero;
    }

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

    public void setTowers(Tower[] tower)
    {
        this.towers = tower;
    }

    public void increaseWave()
    {
        this.currentWave++;
    }

    public void decreaseEnemies()
    {
        this.remainingEnemies--;
    }

    public void setHero(Hero hero)
    {
        this.hero = hero;
    }

    public void setEnemies(Enemy[] enemies)
    {
        this.enemies = enemies;
    }

    public void createTown()
    {
        this.town = new Town();
    }

    public void removeEnemy(Enemy enemy)
    {
        for (int enemyIndex = 0; enemyIndex <  enemies.length; enemyIndex++)
        {
            if (enemy.equals(enemies[enemyIndex]))
            {
                enemies[enemyIndex] = null; // Nulls out an enemy
                break;
            }
        }
    }


}
