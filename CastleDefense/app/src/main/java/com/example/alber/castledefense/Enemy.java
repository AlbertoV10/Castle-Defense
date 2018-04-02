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

public class Enemy extends AppCompatImageView implements Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {

    private ValueAnimator mAnimator;
    private EnemyListener mListener;
    private boolean mHit;
    private double armor;
    private int healthRemaining;
    private boolean isDead;

    public Enemy(Context context)
    {
        super(context);
    }
    public Enemy(Context context, int color, int rawHeight)
    {
        super(context);

        mListener = (EnemyListener) context;

        this.setImageResource(R.drawable.temp_enemy3);
        this.setColorFilter(color);

        //int rawWidth = rawHeight / 2;
        int rawWidth = rawHeight;

        // Control size of enemy here
        int dpHeight = PixelHelper.pixelsToDp(rawHeight/2, context);
        int dpWidth = PixelHelper.pixelsToDp(rawWidth/2, context);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(dpWidth,dpHeight);
        setLayoutParams(params);


        this.armor = .10;
        this.healthRemaining = 30;
        this.isDead = false;
    }


    public boolean isDead()
    {
        return this.isDead;
    }

    public int healthRemaining()
    {
        return this.healthRemaining;
    }

    public void touchEvent(Hero hero)
    {
        double damageMultiplier  = 1 - this.armor + hero.getDamagePiercing();
        this.healthRemaining -=  damageMultiplier * hero.getDamage();
        if (this.healthRemaining <= 0)
        {
            this.isDead = true;
        }
    }

    public void projectileCollision(Projectile projectile)
    {
        double damageMultiplier  = 1 - this.armor + projectile.getPiercingValue();
        this.healthRemaining -=  damageMultiplier * projectile.getDamage();
        if (this.healthRemaining <= 0)
        {
            this.isDead = true;
        }
    }

    // Starts the enemy at x position 0, running to screenWidth
    // duration is the time it will take to reach the end position in milliseconds
    public void releaseEnemy(int screenWidth, int duration){
        mAnimator = new ValueAnimator();
        mAnimator.setDuration(duration);
        // mAnimator.setFloatValues(START, END);
        mAnimator.setFloatValues(0f,screenWidth);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.setTarget(this);
        mAnimator.addListener(this);
        mAnimator.addUpdateListener(this);
        mAnimator.start();
    }

    @Override
    public void onAnimationStart(Animator animator) {
        // nothing yet
    }

    @Override
    public void onAnimationEnd(Animator animator) {
        if(!mHit)
        {
            mListener.damageEnemy(this, false);
        }
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

        //if(!mHit && event.getAction() == MotionEvent.ACTION_DOWN)
        //{
            mListener.damageEnemy(this, true);
           // mHit = true;
            if(this.isDead)
            {
                mAnimator.cancel();
            }
        //}
        return super.onTouchEvent(event);
    }

    // Waits for user touch
        public interface EnemyListener{
            void damageEnemy(Enemy enemy, boolean userTouch);
        }
    }
