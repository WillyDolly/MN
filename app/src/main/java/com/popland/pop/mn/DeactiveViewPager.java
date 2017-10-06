package com.popland.pop.mn;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by hai on 08/06/2017.
 */

public class DeactiveViewPager extends ViewPager {
    public DeactiveViewPager(Context context) {
        super(context);
    }

    public DeactiveViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return isEnabled() && super.onInterceptTouchEvent(event);
    }

        @Override
    public boolean onTouchEvent(MotionEvent event) {
        return !isEnabled() || super.onTouchEvent(event);//TouchEvent cancelled if ViewPager.setEnabled(false)
    }


}
