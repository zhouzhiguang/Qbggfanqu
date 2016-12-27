package com.qbgg.cenglaicengqu.main.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/12/27.
 */

public class DashedLine extends View {

        public DashedLine(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            // TODO Auto-generated method stub
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.RED);
            Path path = new Path();
            path.moveTo(500, 200);
            path.lineTo(800, 900);
            PathEffect effects = new DashPathEffect(new float[]{4, 4, 4, 4}, 2);
            paint.setPathEffect(effects);
            canvas.drawPath(path, paint);
        }
}
