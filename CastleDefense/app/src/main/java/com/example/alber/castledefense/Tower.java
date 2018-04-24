package com.example.alber.castledefense;

import java.io.Serializable;

public class Tower implements Serializable
{
    private int towerType;
    private int rateOfFire;
    private int damage;
    private double armorPiercing;
    private int upgradeLevel;
    private int upgradeCost;

    public Tower() {
        // sets default values for tower
        towerType = 0;
        setDamage(5);
        setRateOfFire(2000);
        setArmorPiercing(.1);
        setUpgradeLevel(1);
        setUpgradeCost(100);
    }

    public double getArmorPiercing() {
        return this.armorPiercing;
    }

    public int getDamage(){
        return  this.damage;
    }

    public int getTowerLevel() {
        return  this.upgradeLevel;
    }

    public int getUpgradeCost(){
        return  this.upgradeCost;
    }

    public int getRateOfFire(){
        return this.rateOfFire;
    }

    public int getUpgradeLevel() {
        return upgradeLevel;
    }

    public void setArmorPiercing(double reduce) {
        this.armorPiercing = reduce;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setRateOfFire(int rate) {
        this.rateOfFire = rate;
    }

    public void setUpgradeLevel(int upgradeLevel) {
        this.upgradeLevel = upgradeLevel;
    }

    public void setUpgradeCost(int upgradeCost) {
        this.upgradeCost = upgradeCost;
    }

    public void upgradeTower()
    {
        // Right now it upgrades everything, this will change in later sprints
        setUpgradeLevel(getUpgradeLevel()+1);
        setRateOfFire(getRateOfFire() + 100);
        setDamage(getDamage() + 5);
        setArmorPiercing(getArmorPiercing() + .2);
        setUpgradeCost(getUpgradeCost()*2);
    }
}
