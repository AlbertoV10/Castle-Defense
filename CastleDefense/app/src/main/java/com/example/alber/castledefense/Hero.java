package com.example.alber.castledefense;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.MotionEvent;

public class Hero extends AppCompatImageView implements Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener{
    private int damage;
    private double damagePiercing;

    public Hero(Context context) {
        super(context);
        // sets default values for tower
        setDamage(10);
        setDamagePiercing(1.0);
    }


    public double getDamagePiercing() {
        return this.damagePiercing;
    }

    public int getDamage(){
        return this.damage;
    }

    public void setDamagePiercing(double newDP) {
        this.damagePiercing = newDP;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public void onAnimationStart(Animator animator) {
        // nothing yet
    }

    @Override
    public void onAnimationEnd(Animator animator) {
        // nothing yet
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
        void killEnemy(Enemy enemy, boolean userTouch);
    }
*/
}
