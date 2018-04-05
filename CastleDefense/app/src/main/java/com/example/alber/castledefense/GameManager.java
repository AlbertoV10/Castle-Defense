package com.example.alber.castledefense;

import android.text.Layout;
import android.support.v7.app.AppCompatActivity;
import java.io.Serializable;

public class GameManager extends AppCompatActivity implements Serializable{
    private Enemy enemies[];
    private Tower towers[];
    private Hero hero;
    private Town town;
    private int numOfEnemies;
    private int currentWave;
    private int remainingEnemies;
    private int currentGold;
    private Projectile playerProjectile;
    //private Layout layout; // needed?
    //public GameManager(Layout layout) // needed?

    public GameManager()
    {
        //this.layout = layout;
        //createEnemies();
        createTown();
        this.currentWave = 1;
        this.numOfEnemies = 5;
        this.remainingEnemies = 5; // Mock data
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

    public Town getTown()
    {
        return this.town;
    }

    public boolean projectileFiled()
    {
        boolean fired = false;
        if(playerProjectile == null)
        {
            fired = true;
        }
        return fired;
    }

    public void setPlayerProjectile(Projectile playerProjectile)
    {
        this.playerProjectile = playerProjectile;
    }

    //public void remove

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

    public int getTowerUpgradePrice()
    {
        return towers[0].getUpgradeCost();
    }

    public void setCurrentGold(int newGold)
    {
        this.currentGold = newGold;
    }

    public void setTowerUpgradePrice(int newPrice)
    {
        this.towers[0].setUpgradeCost(newPrice);
    }

    public void setHero(Hero hero)
    {
        this.hero = hero;
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
