package com.example.alber.castledefense;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.AppCompatImageView;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.example.alber.castledefense.utils.PixelHelper;
import java.io.Serializable;
import  android.graphics.Paint.Style;
import android.graphics.Color;

public class HeroSprite extends AppCompatImageView implements Serializable, Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener{
    private Hero hero;




    public HeroSprite(Context context) {
        super(context);
    }

    public HeroSprite(Context context, int color, int rawHeight)
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

        // Create and store new hero object for stats
        hero = new Hero();
    }

    public Hero getHero(){
        return this.hero;
    }

    public void setHero(Hero newHero){
        this.hero = newHero;
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
        //setX((float) valueAnimator.getAnimatedValue());
    }
}
