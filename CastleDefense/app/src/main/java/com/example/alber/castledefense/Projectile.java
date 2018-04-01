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

    public Projectile(Context context)
    {
        super(context);
    }

    public Projectile (Context context, int color, int rawHeight)
    {
        super(context);

        // will need to change image
        this.setImageResource(R.drawable.temp_enemy2);
        this.setColorFilter(color);
        int rawWidth = rawHeight;

        // Control size of arrow here
        int dpHeight = PixelHelper.pixelsToDp(rawHeight/2, context);
        int dpWidth = PixelHelper.pixelsToDp(rawWidth/2, context);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(dpWidth,dpHeight);
        setLayoutParams(params);
    }

    public void fireProjectile(int screenWidth, int duration)
    {
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
    }
}
