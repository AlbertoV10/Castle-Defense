package com.example.alber.castledefense;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.MotionEvent;

public class Town extends AppCompatImageView implements Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener{
    private int income;
    private int wallHealth;
    private int maxWallHealth;

    public Town(Context context) {
        super(context);
        // sets default values for town
        setIncome(100);
        setWallHealth(100);
        setMaxWallHealth(100);
    }

    public int getIncome(){
        return this.income;
    }

    public int getWallHealth() {
        return this.wallHealth;
    }

    public int getMaxWallHealth(){
        return this.maxWallHealth;
    }

    public void setIncome(int newIncome) {
        this.income = newIncome;
    }

    public void setWallHealth(int newWallHealth) {
        this.wallHealth = newWallHealth;
    }

    public void setMaxWallHealth(int newMaxWallHealth) {this.maxWallHealth = newMaxWallHealth;}

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
/*
    // Touching the enemy picture will remove it from the display
    @Override
    public boolean onTouchEvent(MotionEvent event){
        return  true;
    }

    // Waits for user touch
    public interface EnemyListener{
       //void killEnemy(Enemy enemy, boolean userTouch);
    }
*/
}
