package de.tudarmstadt.informatik.secuso.privacyfriendlypin.pinhelpers;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by yonjuni on 10.11.15.
 */
public class DrawView extends View {

    Paint paint;
    View startView;
    View endView;

    public DrawView(Context context, View startView, View endView) {
        super(context);
        this.startView = startView;
        this.endView = endView;
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(startView.getX() + 80, startView.getY() + 80, endView.getX() + 80, endView.getY() + 80, paint);
    }

    public void computeLinePoints() {

    }

    public void setStrokeWidth(int strokeWidth) {
        paint.setStrokeWidth(strokeWidth);
        invalidate();
    }

    public void setColor(int color) {
        paint.setColor(color);
        invalidate();
    }

}
