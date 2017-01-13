package com.fanqu.main.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.fanqu.R;
import com.nineoldandroids.animation.ObjectAnimator;


/**
 * @author htq
 *         一个圆形进度条
 */
public class CircleProgressBar extends View {

    private int min = 0;
    private int max = 100;
    private float progress = 0;
    private int startAngle = 0;
    private int direction = 1;
    private float strokeWidth = 8;
    private int color = Color.CYAN;
    private float alpha = 0;
    private float backgroundAlpha = 0.3f;
    private int backgroundColor = Color.DKGRAY;
    private float backgroundStrokeWidth = 12;
    private String text = "";
    private Paint backgroundPaint;
    private Paint foregroundPaint;
    private RectF rectF;
    private Paint textPaint;
    private TextPaint mtextPaint;
    private int textColor = Color.WHITE;
    private float textSize = 120;
    private float textPosY;
    //TODO: add custom Font support
    //private Typeface mTypeface;
    private Rect textBounds;
    private ObjectAnimator objectAnimator;
    private int center;


    public CircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /**
     * Inits all parameters from the XML layout. Inits paint objects for displaying circles and text
     *
     * @param context current context
     * @param attrs   XML Layout attributes
     */
    private void init(Context context, AttributeSet attrs) {
        rectF = new RectF();
//        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
//                attrs,
//                R.styleable.CircleProgressBar,
//                0, 0);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressBar);
        //Reading values from the XML layout
        try {
            strokeWidth = typedArray.getDimension(R.styleable.CircleProgressBar_progressBarThickness, strokeWidth);
            progress = typedArray.getFloat(R.styleable.CircleProgressBar_progress, progress);
            color = typedArray.getInt(R.styleable.CircleProgressBar_progressBarColor, color);
            min = typedArray.getInt(R.styleable.CircleProgressBar_min, min);
            max = typedArray.getInt(R.styleable.CircleProgressBar_max, max);
            alpha = typedArray.getFloat(R.styleable.CircleProgressBar_progressBarAlpha, alpha);
            direction = typedArray.getInt(R.styleable.CircleProgressBar_direction, direction);
            startAngle = typedArray.getInt(R.styleable.CircleProgressBar_startAngle, startAngle);
            backgroundAlpha = typedArray.getFloat(R.styleable.CircleProgressBar_progressBarBackgroundAlpha, backgroundAlpha);
            backgroundColor = typedArray.getInt(R.styleable.CircleProgressBar_progressBarBackgroundColor, backgroundColor);
            backgroundStrokeWidth = typedArray.getDimension(R.styleable.CircleProgressBar_progressBarBackgroundThickness, backgroundStrokeWidth);
            text = typedArray.getString(R.styleable.CircleProgressBar_text);
            textSize = typedArray.getDimension(R.styleable.CircleProgressBar_textSize, textSize);
            textColor = typedArray.getInt(R.styleable.CircleProgressBar_textColor, textColor);

        } finally {
            typedArray.recycle();
        }

        //Init Paint object for displaying first circle
        foregroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        foregroundPaint.setColor(adjustAlpha(color, alpha));
        foregroundPaint.setStyle(Paint.Style.STROKE);
        foregroundPaint.setStrokeWidth(strokeWidth);

        //Init Paint object for displaying second circle
        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setColor(adjustAlpha(backgroundColor, backgroundAlpha));
        backgroundPaint.setStyle(Paint.Style.STROKE);
        backgroundPaint.setStrokeWidth(backgroundStrokeWidth);

        //Init Paint object for displaying text
        textPaint = new Paint();
        textPaint.setColor(textColor);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(textSize);
        //TODO: add custom Font support
        //textPaint.setTypeface(mTypeface);
        textPaint.setTextAlign(Paint.Align.CENTER);

        mtextPaint = new TextPaint();
        mtextPaint.setColor(textColor);
        mtextPaint.setStyle(Paint.Style.FILL);
        mtextPaint.setAntiAlias(true);
        mtextPaint.setTextSize(textSize);
        //TODO: add custom Font support
        //textPaint.setTypeface(mTypeface);
        mtextPaint.setTextAlign(Paint.Align.CENTER);
        //该方法必须在onDraw或者onMeasure中调用，否则不起作用d

    }

    /**
     * Adds the Alpha value to specific Color
     *
     * @param color  color which alpha value shall be adjusted
     * @param factor alpha value
     * @return new color with adjusted alpha value
     */
    private int adjustAlpha(int color, float factor) {
        int alpha = Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        final int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        final int min = Math.min(width, height);
        float stroke = Math.max(strokeWidth, backgroundStrokeWidth); //calculate the right width for measuring correct boundarys
        setMeasuredDimension(min, min);
        textBounds = new Rect();
        textPaint.getTextBounds("1", 0, 1, textBounds);
        textPosY = rectF.centerY() + (textBounds.height() / 2f);
        rectF.set(0 + stroke / 2, 0 + stroke / 2, min - stroke / 2, min - stroke / 2);

        center = getWidth() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawOval(rectF, backgroundPaint);
        float angle = direction * 360 * progress / max;
        canvas.drawArc(rectF, startAngle, angle, false, foregroundPaint);
   /*
   * 1.字符串子资源

     2 .画笔对象

    3.layout的宽度，字符串超出宽度时自动换行。

    4.layout的样式，有ALIGN_CENTER， ALIGN_NORMAL， ALIGN_OPPOSITE 三种。

    5.相对行间距，相对字体大小，1.5f表示行间距为1.5倍的字体高度。

     6.相对行间距，0表示0个像素。
   * */

        //getWidth()表示绘制多宽后换行
        if (!TextUtils.isEmpty(text)) {
            StaticLayout sl = new StaticLayout(text, mtextPaint, (int)rectF.width() / 2, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
            //从0,0开始绘制
           //canvas.translate(center, center - textSize);
            canvas.translate(rectF.centerX(), rectF.centerY()-textSize-10);
            sl.draw(canvas);
        }
        //canvas.drawText(text, rectF.centerX(), textPosY, textPaint);
        canvas.restore();
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();// Notify the view to redraw it self (the onDraw method is called)
    }

    public void setText(String text) {
        this.text = text;
        invalidate();
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }


    public int getColor() {

        return color;
    }

    public void setColor(int color) {

        this.color = color;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public float getBackgroundAlpha() {
        return backgroundAlpha;
    }

    public void setBackgroundAlpha(float backgroundAlpha) {
        this.backgroundAlpha = backgroundAlpha;
    }

    public void setBackgroundColor(int color) {
        this.backgroundColor = color;
        backgroundPaint.setColor(backgroundColor);
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public float getBackgroundStrokeWidth() {
        return backgroundStrokeWidth;
    }

    public void setBackgroundStrokeWidth(float backgroundStrokeWidth) {
        this.backgroundStrokeWidth = backgroundStrokeWidth;
    }

    public int getStartAngle() {
        return startAngle;
    }

    public void setStartAngle(int startAngle) {
        this.startAngle = startAngle;
    }

    public void setProgressWithAnimation(float progress, int speed) {
        objectAnimator = ObjectAnimator.ofFloat(this, "progress", progress);
        objectAnimator.setDuration(speed);
        objectAnimator.setInterpolator(new DecelerateInterpolator());
        objectAnimator.start();
    }

}
