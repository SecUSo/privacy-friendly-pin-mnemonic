package org.secuso.privacyfriendlypinmnenomic.pinhelpers;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

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
        canvas.drawLine(startView.getX() + 60, startView.getY() + 60, endView.getX() + 60, endView.getY() + 60, paint);
        drawArrowHead(canvas);
    }

    public void drawArrowHead(Canvas canvas) {

        if (startView.equals(endView)) {
            return;
        }

        Path path = new Path();

        //draws triangle pointing to the right
        path.moveTo(endView.getX() + 50, endView.getY() + 80);
        //arrow head
        path.lineTo(endView.getX() + 90, endView.getY() + 58);

        path.lineTo(endView.getX() + 50, endView.getY() + 40);
        path.close();

        //turns triangle
        Matrix matrix = new Matrix();
        matrix.reset();
        float rotate = whichArrow(startView, endView, digitOne, digitTwo);
        matrix.postRotate(rotate, endView.getX() + 60, endView.getY() + 60);
        path.transform(matrix);

        canvas.drawPath(path, paint);
    }

    public int whichArrow(View view1, View view2, int first, int second) {


        //Arrow should point from right to left
        if ((((first > second) || (second == 1)) && (view1.getY() == view2.getY())) || (first == 2 || first == 3) && second == 1) {
            return 180;
        }
        // Arrow should point downwards
        if ((((first < second) && first != 0) || (second == 0)) && (view1.getX() == view2.getX())) {
            return 90;
        }
        // Arrow should point upwards
        if ((view1.getX() == view2.getX())) {
            return 270;
        }
        //Arrow should point down from left to right
        if (((first < second) || second == 0) && (second - first == 4 || second - first == 8) || first - second == 7) {
            return 45;
        }
        //Arrow should point down from right to left
        if (((first < second) || second == 0) && (second - first == 4 || second - first == 2) || first - second == 9) {
            return 135;
        }
        //Arrow should point up from left to right
        if (((first > second) || first == 0) && ((first - second == 4 && first ==7) || first - second == 2) || second - first == 9) {
            return -45;
        }
        //Arrow should point up from right to left
        if ((((first > second) || first == 0)) && ((first - second == 4 || first - second == 8) || second - first == 7)) {
            return -135;
        }
        //Arrow should point down angularly from left to right TODO Arrow from 4 to 0
        if (((first < second) && (second - first == 7)) || (first == 4 && second == 0)) {
            return 67;
        }
        //Arrow should point down angularly from right to left
        if (((first < second) && (second - first == 5)) || (first == 6 && second == 0)) {
            return 112;
        }
        //Arrow should point up angularly from left to right
        if (((first > second) && (first - second == 7)) || (second == 4 && first == 0)) {
            return -112;
        }
        //Arrow should point up angularly from right to left TODO Arrow from 4 to 0
        if (((first > second) && (first - second == 5)) || (second == 6 && first == 0)) {
            return -67;
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
