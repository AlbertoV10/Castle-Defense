package com.example.alber.castledefense;

import android.text.Layout;

import com.example.alber.castledefense.Enemy;

import java.io.Serializable;

/**
 * Created by ryan.torres097 on 3/28/18.
 */

/*  https://stackoverflow.com/questions/35168981/how-can-i-share-code-between-multiple-activities-in-android
 *  Try to allow multiple activities to access this data.
 */
public class GameManager implements Serializable{
    // set defaults here?
    private Enemy enemies[];
    private Tower towers[];
    private Town town;
    private Hero hero;
    private int numOfEnemies;
    private int currentWave;
    private int remainingEnemies;
    private int currentGold;
    private Layout layout;
    private int[] towerUpgradePrice = new int[]{100,100,100};
    private int[] townUpgradePrice = new int[]{100,100};
    private int[] heroUpgradePrice = new int[]{100,100};

    //public GameManager(Layout layout) // needed?

    public GameManager()
    {
        this.layout = layout;
        //createEnemies();
        //createHero();
        //createTown();
        this.currentWave = 1;
        this.numOfEnemies = 5;
        this.remainingEnemies = 5;     //Mock data
        this.currentGold = 100;
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

    public void setCurrentGold(int newGold)
    {
        this.currentGold = newGold;
    }

    public int getTowerUpgradePrice(int towerNumber)
    {
        return towerUpgradePrice[towerNumber];
    }

    public void setTowerUpgradePrice(int towerNumber, int newPrice)
    {
        this.towerUpgradePrice[towerNumber] = newPrice;
    }

    public int getTownUpgradePrice(int townNumber)
    {
        return townUpgradePrice[townNumber];
    }

    public void setTownUpgradePrice(int townNumber, int newPrice)
    {
        this.townUpgradePrice[townNumber] = newPrice;
    }

    public int getHeroUpgradePrice(int heroNumber)
    {
        return heroUpgradePrice[heroNumber];
    }

    public void setHeroUpgradePrice(int heroNumber, int newPrice)
    {
        this.heroUpgradePrice[heroNumber] = newPrice;
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
