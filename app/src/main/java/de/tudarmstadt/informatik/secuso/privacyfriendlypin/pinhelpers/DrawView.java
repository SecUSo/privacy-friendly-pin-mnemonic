package de.tudarmstadt.informatik.secuso.privacyfriendlypin.pinhelpers;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;
import android.widget.Button;

/**
 * Created by yonjuni on 10.11.15.
 */
public class DrawView extends View {

    Paint paint;
    View startView;
    View endView;
    int digitOne;
    int digitTwo;

    public DrawView(Context context, View startView, View endView, int digitOne, int digitTwo) {
        super(context);
        this.startView = startView;
        this.endView = endView;
        this.digitOne = digitOne;
        this.digitTwo = digitTwo;
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(startView.getX() + 80, startView.getY() + 80, endView.getX() + 80, endView.getY() + 80, paint);
        drawArrowHead(canvas);
    }

    public void drawArrowHead(Canvas canvas) {
        Path path = new Path();
        path.moveTo(endView.getX() + 70, endView.getY() + 35);
        path.lineTo(endView.getX() + 85, endView.getY() + 85);
        path.lineTo(endView.getX() + 35, endView.getY() + 70);
        path.close();
        Matrix matrix = new Matrix();
        matrix.reset();
        float rotate = whichArrow(startView, endView, digitOne, digitTwo);
        matrix.postRotate(rotate, endView.getX() + 80, endView.getY() + 80);
        path.transform(matrix);
        canvas.drawPath(path, paint);
    }

    public int whichArrow(View view1, View view2, int one, int two){
        //Arrow should point from left to right
        if (((one < two) && (view1.getY() == view2.getY())) || ((one == 1) && (two == 2))) {
            return 315;
        }
        //Arrow should point from right to left
        if ((one > two) && (view1.getY() == view2.getY())) {
            return 135;
        }
        // Arrow should point downwards
        if ((one < two) && (view1.getX() == view2.getX())) {
            return 45;
        }
        // Arrow should point upwards
        if ((one > two) && (view1.getX() == view2.getX())) {
            return 225;
        }
        // Arrow should point down from left to right
        if ((one < two) && (two-one == 4)) {
            return 0;
        }
        if ((one > two) && (one-two == 4)) {
            return 180;
        }

        return 0;
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
