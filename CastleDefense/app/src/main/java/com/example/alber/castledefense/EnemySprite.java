package com.example.alber.castledefense;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
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

public class EnemySprite extends AppCompatImageView implements Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {

    private ValueAnimator mAnimator;
    private EnemyListener mListener;
    private boolean mHit;
    private boolean isAttacking;
    private Enemy enemy;
    private int screenWidth;
    private int rangedAttackDistance = 750;

    private ImageView imageView;
    private AnimationDrawable animationDrawableWalking;
    private AnimationDrawable animationDrawableAttacking;

    public EnemySprite(Context context)
    {
        super(context);
    }
    public EnemySprite(Context context, int color, int rawHeight, int screenWidth)
    {
        super(context);

        mListener = (EnemyListener) context;
        this.screenWidth = screenWidth;

        this.setBackgroundResource(R.drawable.dark_mage_movement);
        animationDrawableWalking = (AnimationDrawable) this.getBackground();
        animationDrawableWalking.start();
        isAttacking = false;
        //this.setImageResource(R.drawable.temp_enemy3);
        this.setColorFilter(color);

        //int rawWidth = rawHeight / 2;
        int rawWidth = rawHeight;

        // Control size of enemy here
        int dpHeight = PixelHelper.pixelsToDp(rawHeight/2, context);
        int dpWidth = PixelHelper.pixelsToDp(rawWidth/2, context);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(dpWidth,dpHeight);
        setLayoutParams(params);
        //this.setTooltipText("testing");
        // Create and store new enemy object for stats
        enemy = new Enemy();
        updateHealthbar();
    }

    public Enemy getEnemy(){
        return this.enemy;
    }

    public void setEnemy(Enemy newEnemy){
        this.enemy = newEnemy;
    }

    public boolean isDead()
    {
        return this.enemy.getIsDead();
    }

    public int healthRemaining()
    {
        return this.enemy.getHealthRemaining();
    }

    public void touchEvent(Hero hero)
    {
        double damageMultiplier  = 1 - this.enemy.getArmor() + hero.getDamagePiercing();
        this.enemy.setHealthRemaining(healthRemaining() - (int) (damageMultiplier * hero.getDamage()));
        if (healthRemaining() <= 0)
        {
            this.enemy.setIsDead(true);
        }
    }

    public void projectileCollision(Projectile projectile)
    {
        double damageMultiplier  = this.enemy.getArmor() + projectile.getPiercingValue();
        this.enemy.setHealthRemaining(healthRemaining() - (int) (damageMultiplier * projectile.getDamage()));
        if (healthRemaining() <= 0)
        {
            this.enemy.setIsDead(true);
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

    public void updateHealthbar()
    {
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.temp_enemy4).copy(Bitmap.Config.ARGB_8888, true);

        Paint paint = new Paint();
        // paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        Canvas canvas = new Canvas(bm);
        canvas.drawText(Integer.toString(this.enemy.getHealthRemaining()), 0,bm.getHeight(),paint);
        this.setImageBitmap(new BitmapDrawable(bm).getBitmap());
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
        if(Math.abs(getX()- screenWidth) < rangedAttackDistance)
        {
            animationDrawableWalking.stop();
            this.setBackgroundResource(R.drawable.dark_mage_attack);
            animationDrawableAttacking = (AnimationDrawable) this.getBackground();
            mAnimator.pause();
            animationDrawableAttacking.start();
            isAttacking = true;

        }
    }

    public boolean isAttacking()
    {
        return isAttacking;
    }

    // Touching the enemy picture will remove it from the display
    @Override
    public boolean onTouchEvent(MotionEvent event){

        //if(!mHit && event.getAction() == MotionEvent.ACTION_DOWN)
        //{
        mListener.damageEnemy(this, true);
        // mHit = true;
        if(this.enemy.getIsDead())
        {
            mAnimator.cancel();
        }
        //}
        return super.onTouchEvent(event);
    }

    // Waits for user touch
    public interface EnemyListener{
        void damageEnemy(EnemySprite enemy, boolean userTouch);
    }
}
