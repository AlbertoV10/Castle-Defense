package com.example.alber.castledefense;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.MotionEvent;
import android.view.ViewGroup;
import com.example.alber.castledefense.utils.PixelHelper;
import java.io.Serializable;

public class TowerSprite extends AppCompatImageView implements Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener{
    private Tower tower;

    public TowerSprite(Context context) {
        super(context);
    }

    public TowerSprite(Context context, int color, int rawHeight)
    {
        super(context);

        //mListener = (Enemy.EnemyListener) context;

        this.setImageResource(R.drawable.temp_enemy3);
        this.setColorFilter(color);

        //int rawWidth = rawHeight / 2;
        int rawWidth = rawHeight;

        // Control size of Tower here
        int dpHeight = PixelHelper.pixelsToDp(rawHeight/2, context);
        int dpWidth = PixelHelper.pixelsToDp(rawWidth/2, context);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(dpWidth,dpHeight);
        setLayoutParams(params);

        // Create and store new tower object for stats
        tower = new Tower();
    }

    public Tower getTower() {
        return tower;
    }

    public void setTower(Tower tower) {
        this.tower = tower;
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
}
