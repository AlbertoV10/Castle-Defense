package com.example.alber.castledefense;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.MotionEvent;
import android.view.ViewGroup;

import com.example.alber.castledefense.utils.PixelHelper;

public class Hero extends AppCompatImageView implements Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener{
    private int damage;
    private double damagePiercing;

    public Hero(Context context) {
        super(context);
        // sets default values for tower
        setDamage(10);
        setDamagePiercing(1.0);
    }
    public Hero(Context context, int color, int rawHeight)
    {
        super(context);

        //mListener = (Enemy.EnemyListener) context;

        this.setImageResource(R.drawable.temp_enemy3);
        this.setColorFilter(color);

        //int rawWidth = rawHeight / 2;
        int rawWidth = rawHeight;

        // Control size of enemy here
        int dpHeight = PixelHelper.pixelsToDp(rawHeight/2, context);
        int dpWidth = PixelHelper.pixelsToDp(rawWidth/2, context);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(dpWidth,dpHeight);
        setLayoutParams(params);
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
