package com.example.monitoringandfeedback6.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

import com.example.monitoringandfeedback6.R;

public class UglyButton extends View {

    private Rect buttonRect = new Rect(0, 0, 0, 0);
    private Paint buttonPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private GestureDetector detector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
    });

    private String buttonText;
    private Color buttonColor;

    private OnUglyButtonClickedListener onUglyButtonClickedListener;

    public UglyButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context
                .getTheme()
                .obtainStyledAttributes(
                        attrs,
                        R.styleable.UglyButton,
                        0, 0
                );
        try {
            buttonText = a.getString(R.styleable.UglyButton_buttonText);
            buttonColor = Color.valueOf(a.getColor(R.styleable.UglyButton_buttonColor, Color.BLACK));
        } catch (Throwable tr) {
            Log.e(UglyButton.class.getSimpleName(), tr.getMessage());
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int buttonStrokeWidth = 20;

        buttonPaint.setColor(Color.BLACK);
        buttonPaint.setTextSize(50);
        buttonPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(buttonText, buttonRect.centerX(), buttonRect.centerY() + buttonStrokeWidth, buttonPaint);

        buttonPaint.setColor(buttonColor.toArgb());
        buttonPaint.setStrokeWidth(buttonStrokeWidth);
        buttonPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(buttonRect, buttonPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        buttonRect.left = getPaddingLeft();
        buttonRect.right = w - getPaddingRight();
        buttonRect.top = getPaddingTop();
        buttonRect.bottom = h - getPaddingBottom();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean res = detector.onTouchEvent(event);
        if (!res && event.getAction() == MotionEvent.ACTION_UP) {
            if (onUglyButtonClickedListener != null) {
                onUglyButtonClickedListener.onUglyButtonClicked(this);
            }
            return true;
        }
        return res;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
        invalidate();
    }

    public void setButtonColor(Color buttonColor) {
        this.buttonColor = buttonColor;
        invalidate();
    }

    public void setButtonColor(@ColorInt int buttonColor) {
        this.buttonColor = Color.valueOf(buttonColor);
        invalidate();
    }

    public void setOnUglyButtonClickedListener(OnUglyButtonClickedListener onUglyButtonClickedListener) {
        this.onUglyButtonClickedListener = onUglyButtonClickedListener;
    }

    public interface OnUglyButtonClickedListener {
        void onUglyButtonClicked(UglyButton uglyButton);
    }
}
