package com.github.golabe.slidingmenu.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class SlidingMenu extends HorizontalScrollView {
    private final int screenWidth;
    private ViewGroup menu;
    private ViewGroup main;
    private float padding;
    private int menuWidth;
    private boolean isMeasure;
    private boolean isOpenAnimation;
    private int defaultShow;
    private static int DEFAULT_RIGHT = 0x0002;


    public SlidingMenu(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SlidingMenu);
            padding = dp2px(a.getDimension(R.styleable.SlidingMenu_sliding_menu_padding, 0));
            defaultShow = a.getInt(R.styleable.SlidingMenu_sliding_menu_default_show, DEFAULT_RIGHT);
            isOpenAnimation = a.getBoolean(R.styleable.SlidingMenu_sliding_menu_animation, true);
            a.recycle();
        }

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        screenWidth = outMetrics.widthPixels;
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if (!isMeasure) {
            LinearLayout container = (LinearLayout) getChildAt(0);
            menu = (ViewGroup) container.getChildAt(0);
            main = (ViewGroup) container.getChildAt(1);

            menuWidth = (int) (screenWidth - padding);
            menu.getLayoutParams().width = menuWidth;
            main.getLayoutParams().width = screenWidth;
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        if (changed) {
            isMeasure = true;
        }
        super.onLayout(changed, l, t, r, b);
        if (defaultShow == DEFAULT_RIGHT) {
            this.scrollTo(menuWidth, 0);
        }
    }


    private float downX;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = ev.getX();
                break;
            case MotionEvent.ACTION_UP:
                float dx = ev.getX() - downX;
                if (dx < screenWidth / 3) {
                    this.smoothScrollTo(menuWidth, 0);
                } else {
                    this.smoothScrollTo(0, 0);
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        if (isOpenAnimation){
            float factor = (float) l / menuWidth;
            menu.setTranslationX(menuWidth * factor * 0.6F);
            float lScale = 1 - factor * 0.4F;
            menu.setScaleX(lScale);
            menu.setScaleY(lScale);

            float rScale = factor * 0.2F + 0.8F;

            main.setScaleY(rScale);
            main.setScaleX(rScale);
            menu.setAlpha(1 - factor);
        }


    }

    private int dp2px(float dp) {
        float v = getContext().getResources().getDisplayMetrics().density;
        return (int) (v * dp + 0.5F);
    }
}
