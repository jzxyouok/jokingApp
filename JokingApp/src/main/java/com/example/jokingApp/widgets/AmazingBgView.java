package com.example.jokingApp.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AmazingBgView extends View {

    private final Paint paint = new Paint();
    private final List<Ring> ringList = new ArrayList<>();

    static class Ring {
        int radius, x, y, color, rx, ry;
        boolean direction;
        float angle, speed;
    }

    private boolean anim = false;

    Random random = new Random();

    private Ring createByRandom(int left, int top, int right, int bottom) {
        int w = right - left;
        int h = bottom - top;

        Ring ring = new Ring();
        ring.radius = w / 3 + (int) ((w / 4) * random.nextFloat());
        ring.x = (int) (w * random.nextFloat());
        ring.y = (int) (h * random.nextFloat());
        ring.color = 0xFF1D222B;
        ring.rx = ring.x + (int) (((ring.x < w / 2) ? 1 : -1) * random.nextFloat() * w / 2);
        ring.ry = ring.y + (int) (((ring.y < h / 2) ? 1 : -1) * random.nextFloat() * h / 2);
        ring.speed = 0.2f + random.nextInt(4) * 0.01f;
        ring.direction = random.nextBoolean();
        ring.angle = (int) (360 * random.nextFloat());
        return ring;
    }

    ;

    private int mWidth, mHeight;

    public AmazingBgView(Context context) {
        this(context, null);
    }


    public AmazingBgView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth((int) (1 * context.getResources().getDisplayMetrics().density + 0.5f));
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

        ringList.clear();
        for (int i = 0; i < 9; i++) {
            ringList.add(createByRandom(0, 0, mWidth, mHeight));
        }

        anim = true;
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Ring r : ringList) {
            canvas.save();
            canvas.rotate(r.angle, r.rx, r.ry);
            paint.setColor(r.color);
            canvas.drawCircle(r.x, r.y, r.radius, paint);
            canvas.restore();
            r.angle = r.angle + r.speed * (r.direction ? 1 : -1);
        }

        if (anim) invalidate();
    }

    public void pause() {
        anim = false;
    }

    public void resume() {
        anim = true;
        invalidate();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        anim = false;
    }
}
