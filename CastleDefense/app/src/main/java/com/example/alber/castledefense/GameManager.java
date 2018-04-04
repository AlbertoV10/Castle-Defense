package com.example.alber.castledefense;

import android.text.Layout;
import android.support.v7.app.AppCompatActivity;
import java.io.Serializable;
import java.util.ArrayList;

/*
    Problems Encountered:
    Very Difficult to store Hero, Enemy, and Tower objects, probably since they extend AppCompatView
    solutions?
    - keep track of relevant data in the game manager, such as enemy positions and upgrades
    - spawn hero, enemies, towers in battlescreen activity
    - use GetX or whatever command every animation update and update the game manager arrays

    //TODO
    How are upgrades applied?
    Are they individual upgrades? (tower 1 damage, tower 1 ROF, tower 1 pierce) <-- leads to too many buttons
    or does upgrading the tower upgrade the damage + ROF + pierce etc (everything at once)

    Current Planned Upgrades
    - Town - increase income, wall health, restore wall health to max
    - Hero - increase base damage, piercing modifier/damage
    - Tower - increase base damage, piercing modifier/damage, rate of fire
 */

public class GameManager extends AppCompatActivity implements Serializable{
    private Enemy enemies[];
    private Tower towers[];
    private Hero hero;
    private Town town;
    private int numOfEnemies;
    private int currentWave;
    private int remainingEnemies;
    private int currentGold;
    //private Layout layout;
    private int[] towerUpgradePrice = new int[]{100,100,100};
    //private int[] tower

    // Hero data
    private int heroDamageUpgradePrice = 100;
    private int heroDamage = 10;
    private int heroDamageLevel = 1;
    private double heroDamagePiercing = .2;
    private int heroPiercingUpgradePrice = 100;
    private int heroPiercingLevel = 1;

    //public GameManager(Layout layout) // needed?

    public GameManager()
    {
        //this.layout = layout;
        //createEnemies();
        //createHero();
        hero = null;
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

    public int getTowerUpgradePrice(int towerNumber)
    {
        return towerUpgradePrice[towerNumber];
    }

    // Town Getters
    public int getTownIncomeUpgradePrice()
    {
        return town.getIncomeUpgradePrice();
    }

    public int getTownWallUpgradePrice()
    {
        return town.getWallUpgradePrice();
    }

    public int getTownRestoreHealthPrice()
    {
        return town.getRestoreHealthPrice();
    }
    //
    // Hero Getters
    public int getHeroDamage()
    {
        return this.heroDamage;
    }

    public int getHeroDamageUpgradePrice()
    {
        return this.heroDamageUpgradePrice;
    }

    public int getHeroDamageLevel()
    {
        return this.heroDamageLevel;
    }

    public double getHeroDamagePiercing()
    {
        return this.heroDamagePiercing;
    }

    public int getHeroPiercingUpgradePrice()
    {
        return this.heroPiercingUpgradePrice;
    }

    public int getHeroPiercingLevel()
    {
        return this.heroPiercingLevel;
    }
    //

    public void setCurrentGold(int newGold)
    {
        this.currentGold = newGold;
    }

    public void setTowerUpgradePrice(int towerNumber, int newPrice)
    {
        this.towerUpgradePrice[towerNumber] = newPrice;
    }

    // Town Setters
    public void setTownIncomeUpgradePrice(int newPrice)
    {
        this.town.setIncomeUpgradePrice(newPrice);
    }

    public void setTownWallUpgradePrice(int newPrice)
    {
        this.town.setWallUpgradePrice(newPrice);
    }

    public void setTownRestoreHealthPrice(int newPrice)
    {
        this.town.setRestoreHealthPrice(newPrice);
    }

    // Hero Setters
    public void setHeroDamage(int newDamage)
    {
        this.heroDamage = newDamage;
    }

    public void setHeroDamageUpgradePrice(int newPrice)
    {
        this.heroDamageUpgradePrice = newPrice;
    }

    public void setHeroDamageLevel(int newLevel)
    {
        this.heroDamageLevel = newLevel;
    }

    public void setHeroDamagePiercing(double newPiercing)
    {
        this.heroDamagePiercing = newPiercing;
    }

    public void setHeroPiercingUpgradePrice(int newPrice)
    {
        this.heroPiercingUpgradePrice = newPrice;
    }

    public void setHeroPiercingLevel(int newLevel)
    {
        this.heroPiercingLevel = newLevel;
    }
    //

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

    public void createHero()
    {
        this.hero = new Hero(this, 0x0000FF, 150);
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
