package com.example.alber.castledefense;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.example.alber.castledefense.utils.PixelHelper;

public class Projectile extends AppCompatImageView implements Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener{

    private ValueAnimator mAnimator;
    private ProjectileListener mListener;
    private int stopPositionX;
    private int damage;
    private double piercingValue;
    private int projectileType = 0;

    public Projectile(Context context)
    {
        super(context);
    }

    public Projectile (Context context, int color, int rawHeight)
    {
        super(context);
        mListener = (ProjectileListener) context;
        // will need to change image
        this.setImageResource(R.drawable.arrow_lower_res);
        this.setColorFilter(color);
        int rawWidth = rawHeight;

        // Control size of arrow here
        int dpHeight = PixelHelper.pixelsToDp(rawHeight/2, context);
        int dpWidth = PixelHelper.pixelsToDp(rawWidth/2, context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(dpWidth,dpHeight);
        setLayoutParams(params);

        setDamage(5);
        setPiercingValue(.2);
        setProjectileType(0);
    }

    public int getDamage()
    {
        return this.damage;
    }

    public double getPiercingValue()
    {
        return  this.piercingValue;
    }

    public int getProjectileType() { return this.projectileType; }

    public void setDamage(int newDamage){ this.damage = newDamage; }

    public void setPiercingValue(double newValue) { this.piercingValue = newValue; }

    public void setProjectileType(int newType) {this.projectileType = newType;}

    public void fireProjectile(int screenWidth, int duration, int stopPositionX)
    {
        this.stopPositionX = stopPositionX;
        mAnimator = new ValueAnimator();
        mAnimator.setDuration(duration);
        mAnimator.setFloatValues(screenWidth,0f);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.setTarget(this);
        mAnimator.addListener(this);
        mAnimator.addUpdateListener(this);
        mAnimator.start();
    }

    @Override
    public void onAnimationStart(Animator animator) {

    }

    @Override
    public void onAnimationEnd(Animator animator) {
        mListener.removeProjectile(this);
    }

    @Override
    public void onAnimationCancel(Animator animator) {

    }

    @Override
    public void onAnimationRepeat(Animator animator) {

    }

    @Override
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        setX((float) valueAnimator.getAnimatedValue());
//        if(!(getX() > stopPositionX))
//        {
//            mAnimator.cancel();
 //       }
    }

    public int getProjectileXPosition()
    {
        return (int)getX();
    }

    public interface ProjectileListener{
        void removeProjectile(Projectile projectile);
    }
}
