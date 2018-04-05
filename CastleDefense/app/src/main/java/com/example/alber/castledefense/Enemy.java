package com.example.alber.castledefense;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.util.Log;

import com.example.alber.castledefense.utils.PixelHelper;

import java.io.Serializable;

public class Enemy implements Serializable {

    private double armor;
    private int healthRemaining;
    private boolean isDead;

    public Enemy()
    {
        this.armor = .10;
        this.healthRemaining = 30;
        this.isDead = false;
    }

    public boolean getIsDead()
    {
        return this.isDead;
    }

    public int getHealthRemaining()
    {
        return this.healthRemaining;
    }

    public double getArmor() {
        return this.armor;
    }

    public void setIsDead(boolean newState){
        this.isDead = newState;
    }

    public void setHealthRemaining(int newHealth)
    {
        this.healthRemaining = newHealth;
    }

    public void setArmor(double newArmor){
        this.armor = newArmor;
    }

    // Waits for user touch
    public interface EnemyListener{
            void damageEnemy(Enemy enemy, boolean userTouch);
            void removeEnemy(Enemy enemy);
        }
    }
