package com.example.alber.castledefense;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.example.alber.castledefense.Projectile;
import com.example.alber.castledefense.R;
import com.example.alber.castledefense.utils.PixelHelper;

public class EnemyProjectile extends AppCompatImageView implements Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener{

    private ValueAnimator mAnimator;
    private EnemyProjectileListener mListener;
    private int stopPositionX;
    private int damage;
    private int piercingValue;

    public EnemyProjectile(Context context)
    {
        super(context);
    }

    public EnemyProjectile(Context context, int color, int rawHeight)
    {
        super(context);
        mListener = (EnemyProjectileListener) context;
        // will need to change image
        this.setImageResource(R.drawable.arrow);
        this.setColorFilter(color);
        int rawWidth = rawHeight;

        // Control size of arrow here
        int dpHeight = PixelHelper.pixelsToDp(rawHeight/2, context);
        int dpWidth = PixelHelper.pixelsToDp(rawWidth/2, context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(dpWidth,dpHeight);
        setLayoutParams(params);

        this.damage = 5;   // Mock Data
        this.piercingValue = 10; // Mock data

    }

    public int getDamage()
    {
        return this.damage;
    }

    public int getPiercingValue()
    {
        return  this.piercingValue;
    }

    public void fireProjectile(int screenWidth, int duration, int stopPositionX)
    {
        this.stopPositionX = stopPositionX;
        mAnimator = new ValueAnimator();
        mAnimator.setDuration(duration);
        mAnimator.setFloatValues(0f,screenWidth);
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
        mListener.removeEnemyProjectile(this);
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

    public interface EnemyProjectileListener{
        void removeEnemyProjectile(EnemyProjectile ep);
    }
}
