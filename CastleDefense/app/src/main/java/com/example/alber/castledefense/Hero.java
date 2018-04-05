package com.example.alber.castledefense;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.MotionEvent;
import android.view.ViewGroup;
import com.example.alber.castledefense.utils.PixelHelper;
import java.io.Serializable;

public class Hero implements Serializable {
    private int damage;
    private int damageUpgradePrice;
    private int damageLevel;
    private double damagePiercing;
    private int piercingUpgradePrice;
    private int piercingLevel;

    public Hero() {
        setDamage(10);
        setDamagePiercing(.2);
        setDamageUpgradePrice(100);
        setDamageLevel(1);
    }

    public int getDamage() {
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

    public void upgradeHero() {
        // Right now it upgrades everything, this will change in later sprints
        setDamage(getDamage() + 10);
        setDamagePiercing(getDamagePiercing()+.1);

        setDamageLevel(getDamageLevel()+1);
        setPiercingLevel(getPiercingLevel()+1);

        setDamageUpgradePrice(getDamageUpgradePrice()*2);
        setPiercingUpgradePrice(getDamageUpgradePrice()*2);
    }
}
