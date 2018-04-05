package com.example.alber.castledefense;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.MotionEvent;
import java.io.Serializable;

//public class Town extends AppCompatImageView implements Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener{
public class Town implements Serializable{
    private int income;
    private int incomeLevel;
    private int incomeUpgradePrice;
    private int wallHealth;
    private int wallLevel;
    private int wallUpgradePrice;
    private int maxWallHealth;
    private int restoreHealthPrice;

    public Town() {
        // sets default values for town
        setIncome(100);
        setIncomeLevel(1);
        setIncomeUpgradePrice(200);

        setWallHealth(100);
        setWallLevel(1);
        setWallUpgradePrice(100);

        setMaxWallHealth(100);
        setRestoreHealthPrice(getMaxWallHealth());
    }

    public int getIncome(){ return this.income; }

    public int getIncomeLevel(){ return this.incomeLevel;}

    public int getIncomeUpgradePrice(){ return this.incomeUpgradePrice;}

    public int getWallHealth() { return this.wallHealth; }

    public int getMaxWallHealth(){ return this.maxWallHealth; }

    public int getWallLevel(){ return this.wallLevel;}

    public int getWallUpgradePrice(){ return this.wallUpgradePrice;}

    public int getRestoreHealthPrice(){ return this.restoreHealthPrice;}

    public void setIncome(int newIncome) { this.income = newIncome; }

    public void setIncomeLevel(int newLevel){ this.incomeLevel = newLevel;}

    public void setIncomeUpgradePrice(int newPrice){ this.incomeUpgradePrice = newPrice;}

    public void setWallHealth(int newWallHealth) { this.wallHealth = newWallHealth; }

    public void setMaxWallHealth(int newMaxWallHealth) {this.maxWallHealth = newMaxWallHealth;}

    public void setWallLevel(int newLevel) { this.wallLevel = newLevel;}

    public void setWallUpgradePrice(int newPrice){ this.wallUpgradePrice = newPrice;}

    public void setRestoreHealthPrice(int newPrice){ this.restoreHealthPrice = getMaxWallHealth();}

    public void upgradeIncome(){
        setIncome(getIncome() + 100);
        setIncomeUpgradePrice(getIncomeUpgradePrice()*2);
        setIncomeLevel(getIncomeLevel()+1);
    }

    public void upgradeWall(){
        setWallHealth(getMaxWallHealth()+100);
        setWallUpgradePrice(getWallUpgradePrice()*2);
        setWallLevel(getWallLevel()+1);
    }

    public void restoreHealth(){
        setWallHealth(getMaxWallHealth());
    }
}
