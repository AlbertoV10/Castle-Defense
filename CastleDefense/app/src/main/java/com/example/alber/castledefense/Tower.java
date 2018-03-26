package com.example.alber.castledefense;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.MotionEvent;

/**
 * Created by ryan.torres097 on 3/26/18.
 */

public class Tower extends AppCompatImageView implements Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener{
    private int rateOfFire;
    private int damage;
    private double damagePiercing;
    private int upgradeLevel;
    private int upgradeCost;

    public Tower(Context context) {
        super(context);
        // sets default values for tower
        setDamage(10);
        setRateOfFire(10);
        setDamagePiercing(1);
        this.upgradeLevel = 1;
        this.upgradeCost = 100;
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

    public void upgradeTower(){
        this.upgradeLevel++;

        // Mock Values, will implemenet algorithm in later sprint
        setRateOfFire(this.rateOfFire + 10);
        setDamage(this.damage + 10);
        setDamagePiercing(this.damagePiercing + 10);
        this.upgradeCost = this.upgradeCost *2;
    }

    // Name changed from setDamageReduction
    public void setDamagePiercing(double reduce) {
        this.damagePiercing = reduce;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setRateOfFire(int rate)
    {
        this.rateOfFire = rate;
    }

    @Override
    public void onAnimationStart(Animator animator) {
        // nothing yet
    }

    @Override
    public void onAnimationEnd(Animator animator) {

    }

    @Override
    public void onAnimationCancel(Animator animator) {
        // nothing yet
    }

    @Override
    public void onAnimationRepeat(Animator animator) {
        // nothing yet
    }

    @Override
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        setX((float) valueAnimator.getAnimatedValue());
    }

    // Touching the enemy picture will remove it from the display
    @Override
    public boolean onTouchEvent(MotionEvent event){
        return  true;
    }

    // Waits for user touch
    public interface EnemyListener{
        void killEnemy(Enemy enemy, boolean userTouch);
    }
}
