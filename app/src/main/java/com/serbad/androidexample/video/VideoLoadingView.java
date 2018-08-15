package com.serbad.androidexample.video;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.serbad.androidexample.R;


/**
 * _______________#########_______________________
 * ______________############_____________________
 * ______________#############____________________
 * _____________##__###########___________________
 * ____________###__######_#####__________________
 * ____________###_#######___####_________________
 * ___________###__##########_####________________
 * __________####__###########_####_______________
 * ________#####___###########__#####_____________
 * _______######___###_########___#####___________
 * _______#####___###___########___######_________
 * ______######___###__###########___######_______
 * _____######___####_##############__######______
 * ____#######__#####################_#######_____
 * ____#######__##############################____
 * ___#######__######_#################_#######___
 * ___#######__######_######_#########___######___
 * ___#######____##__######___######_____######___
 * ___#######________######____#####_____#####____
 * ____######________#####_____#####_____####_____
 * _____#####________####______#####_____###______
 * ______#####______;###________###______#________
 * ________##_______####________####______________
 */

public class VideoLoadingView extends View {

    private Paint backgroundPaint;
    private RectF backgroundRectF;
    private Paint progressPaint;
    private RectF progressRectF;
    private float progressLeft;
    private float progressRight;
    private int alpha = 255;
    private Animation animation;

    public VideoLoadingView(Context context) {
        this(context, null);
    }

    public VideoLoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VideoLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.post(new Runnable() {
            @Override
            public void run() {
                backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                backgroundPaint.setColor(getResources().getColor(R.color.white_50));
                backgroundRectF = new RectF(getLeft(), (getBottom() - getTop()) / 2 - LocalDisplay.dp2px(0.25f), getRight(), (getBottom() - getTop()) / 2 + LocalDisplay.dp2px(0.25f));
                progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                progressPaint.setColor(getResources().getColor(R.color.white));
                progressLeft = getRight() / 2;
                progressRight = getRight() / 2;
                progressRectF = new RectF(progressLeft, (getBottom() - getTop()) / 2 - LocalDisplay.dp2px(0.25f), progressRight, (getBottom() - getTop()) / 2 + LocalDisplay.dp2px(0.25f));
                startLoading();
            }
        });
    }


    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (visibility == VISIBLE) {
            this.startLoading();
        } else {
            if (getAnimation() != null)
                this.clearAnimation();
        }
    }

    private void startLoading() {
        if (animation == null)
            animation = new Animation() {
                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    progressLeft = getRight() / 2 - ((interpolatedTime) * getRight() / 2);
                    progressRight = getRight() / 2 + (interpolatedTime * getRight() / 2);
                    if (backgroundRectF != null) {
                        if (progressLeft <= backgroundRectF.left) {
                            progressLeft = getRight() / 2;
                            progressRight = getRight() / 2;
                        }
                    }
                    alpha = 255 - (int) (interpolatedTime * 155);
                    if (progressRectF == null)
                        progressRectF = new RectF();
                    progressRectF.set(progressLeft, (getBottom() - getTop()) / 2 - LocalDisplay.dp2px(0.5f), progressRight, (getBottom() - getTop()) / 2 + LocalDisplay.dp2px(0.5f));
                    postInvalidate();
                    if (interpolatedTime == 1) {
                        clearAnimation();
                        startAnimation(animation);
                    }
                }
            };
        animation.setDuration(800);
        this.clearAnimation();
        this.startAnimation(animation);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        if (backgroundPaint != null) {
//            canvas.drawRect(backgroundRectF, backgroundPaint);
//        }
        if (progressPaint != null) {
            canvas.drawRect(progressRectF, progressPaint);

        }
//        Log.e("VideoLoadingView","alpha:"+alpha+"  progressLeft:"+progressLeft+"  progressRight:"+progressRight);
        this.setAlpha(alpha);
    }
}
