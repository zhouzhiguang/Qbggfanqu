package com.fanqu.main.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.EditText;

import com.fanqu.R;

/**
 * 仿支付宝密码输入框
 */

public class PayEditPasswordText extends EditText {
    private int mBorderColor;
    private int mBorderWidth;
    private int mBorderRadius;

    private int mCodeLength;
    private int mCodeColor;
    private int mCodeSize;

    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private RectF mRectF;
    private int Padding;//竖线距离上下的间距

    private String mTextContent;
    private int mTextLength;

    public PayEditPasswordText(Context context) {
        this(context, null);
    }

    public PayEditPasswordText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PayEditPasswordText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PayEditPassword, defStyleAttr, 0);
        mBorderColor = a.getColor(R.styleable.PayEditPassword_VerifyBorderColor, ContextCompat.getColor(context, R.color.fragment_person_bg));
        mBorderWidth = a.getDimensionPixelSize(R.styleable.PayEditPassword_VerifyBorderWidth, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics()));
        mBorderRadius = a.getDimensionPixelSize(R.styleable.PayEditPassword_VerifyBorderRadius, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()));

        mCodeColor = a.getColor(R.styleable.PayEditPassword_VerifyCodeTextColor, ContextCompat.getColor(context, R.color.fragment_person_bg));
        mCodeLength = a.getInt(R.styleable.PayEditPassword_VerifyCodeLength, 6);
        mCodeSize = a.getDimensionPixelSize(R.styleable.PayEditPassword_VerifyCodeTextSize, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 24, getResources().getDisplayMetrics()));
        a.recycle();

        Padding = (int) getResources().getDimension(R.dimen.dimen_50px);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mPaint == null) {
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
        }
        mWidth = getWidth();
        mHeight = getHeight();

        mRectF = new RectF(0, 0, mWidth, mHeight);
        //圆角矩形
        mPaint.setColor(mBorderColor);
        mPaint.setStyle(Paint.Style.STROKE);                       //设置画笔为空心
        mPaint.setStrokeWidth(mBorderWidth);
        //mPaint.setStyle(Paint.Style.STROKE);
        //canvas.drawRoundRect(mRectF,mBorderRadius,mBorderRadius,mPaint);

        //分割线
        float offset = mRectF.width() / mCodeLength;
        float lineX;

        for (int i = 1; i < mCodeLength; i++) {
            lineX = mRectF.left + offset * i;
            //float startX, float startY, float stopX, float stopY, Paint paint
            canvas.drawLine(lineX, Padding, lineX, mRectF.height() - Padding, mPaint);
        }

        mPaint.setColor(mCodeColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(mCodeSize);
        mPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float baseLine = (mRectF.bottom + mRectF.top - fontMetrics.bottom - fontMetrics.top) / 2;
        float codeX;
        //更新数字
        for (int i = 0; i < mTextLength; i++) {
            codeX = mRectF.left + offset * i + offset / 2;
            canvas.drawText(mTextContent.substring(i, i + 1), codeX, baseLine, mPaint);
        }

    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        this.mTextContent = text.toString();
        this.mTextLength = text.toString().length();
        invalidate();
    }
}
