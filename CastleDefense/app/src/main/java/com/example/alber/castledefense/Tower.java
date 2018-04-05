package com.example.alber.castledefense;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.MotionEvent;
import android.view.ViewGroup;
import com.example.alber.castledefense.utils.PixelHelper;
import java.io.Serializable;

public class Tower implements Serializable{
    private int rateOfFire;
    private int damage;
    private double damagePiercing;
    private int upgradeLevel;
    private int upgradeCost;

    public Tower() {
        // sets default values for tower
        setDamage(10);
        setRateOfFire(10);
        setDamagePiercing(.1);
        setUpgradeLevel(1);
        setUpgradeCost(100);
    }

    public double getDamagePiercing() {
        return this.damagePiercing;
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

    public void setDamagePiercing(double reduce) {
        this.damagePiercing = reduce;
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

    public void upgradeTower(){
        // Right now it upgrades everything, this will change in later sprints
        setUpgradeLevel(getUpgradeLevel()+1);
        setRateOfFire(getRateOfFire() + 1);
        setDamage(getDamage() + 5);
        setDamagePiercing(getDamagePiercing() + .2);
        setUpgradeCost(getUpgradeCost()*2);
    }

}
