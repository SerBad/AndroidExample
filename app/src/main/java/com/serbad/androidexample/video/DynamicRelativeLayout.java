package com.serbad.androidexample.video;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.facebook.drawee.view.AspectRatioMeasure;


/**
 */
public class DynamicRelativeLayout extends RelativeLayout {

    private final AspectRatioMeasure.Spec mMeasureSpec = new AspectRatioMeasure.Spec();
    private float mAspectRatio = 0;


    public DynamicRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DynamicRelativeLayout(Context context) {
        super(context);
    }

    /**
     * Gets the desired aspect ratio (w/h).
     */
    public float getAspectRatio() {
        return mAspectRatio;
    }

    /**
     * Sets the desired aspect ratio (w/h).
     */
    public void setAspectRatio(float aspectRatio) {
        if (aspectRatio == mAspectRatio) {
            return;
        }
        mAspectRatio = aspectRatio;
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mMeasureSpec.width = widthMeasureSpec;
        mMeasureSpec.height = heightMeasureSpec;
        AspectRatioMeasure.updateMeasureSpec(
                mMeasureSpec,
                mAspectRatio,
                getLayoutParams(),
                getPaddingLeft() + getPaddingRight(),
                getPaddingTop() + getPaddingBottom());
        super.onMeasure(mMeasureSpec.width, mMeasureSpec.height);
    }
}