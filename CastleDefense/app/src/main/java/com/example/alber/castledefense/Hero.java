package com.example.alber.castledefense;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.MotionEvent;
import android.view.ViewGroup;
import com.example.alber.castledefense.utils.PixelHelper;
import java.io.Serializable;

public class Hero  implements java.io.Serializable/*extends AppCompatImageView implements Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener*/{
    private int damage;
    private int damageUpgradePrice;
    private int damageLevel;
    private double damagePiercing;
    private int piercingUpgradePrice;
    private int piercingLevel;

    public Hero(Context context) {
        //super(context);
        // sets default values for tower
        setDamage(10);
        setDamagePiercing(.2);
    }

   /* public Hero(Context context, int color, int rawHeight)
    {
        super(context);

        //mListener = (Enemy.EnemyListener) context;

        this.setImageResource(R.drawable.temp_enemy3);
        this.setColorFilter(color);

        //int rawWidth = rawHeight / 2;
        int rawWidth = rawHeight;

        // Control size of Hero here
        int dpHeight = PixelHelper.pixelsToDp(rawHeight/2, context);
        int dpWidth = PixelHelper.pixelsToDp(rawWidth/2, context);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(dpWidth,dpHeight);
        setLayoutParams(params);

        setDamage(10);
        setDamageUpgradePrice(100);
        setDamageLevel(1);
        setDamagePiercing(.2);
        setPiercingUpgradePrice(100);
        setPiercingLevel(1);
    }*/

    public int getDamage(){
        return this.damage;
    }

    public int getDamageUpgradePrice() {
        return this.damageUpgradePrice;
    }

    public int getDamageLevel() {
        return this.damageLevel;
    }

    public double getDamagePiercing() {
        return this.damagePiercing;
    }

    public int getPiercingUpgradePrice() {
        return this.piercingUpgradePrice;
    }

    public int getPiercingLevel() {
        return this.piercingLevel;
    }

    public void setDamagePiercing(double newDP) {
        this.damagePiercing = newDP;
    }

    public void setDamage(int newDamage) {
        this.damage = newDamage;
    }

    public void setDamageUpgradePrice(int newDamageUpgradePrice) {
        this.damageUpgradePrice = newDamageUpgradePrice;
    }

    public void setDamageLevel(int newDamageLevel) {
        this.damageLevel = newDamageLevel;
    }

    public void setPiercingUpgradePrice(int newPiercingUpgradePrice) {
        this.piercingUpgradePrice = newPiercingUpgradePrice;
    }

    public void setPiercingLevel(int newPiercingLevel) {
        this.piercingLevel = newPiercingLevel;
    }

/*    @Override
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
        //setX((float) valueAnimator.getAnimatedValue());
    }*/
}
