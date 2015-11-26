package org.secuso.privacyfriendlypinmnenomic.pinhelpers;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
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
        paint.setAlpha(150);
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawArrowHead(canvas);

        int xStartView = startView.getLeft() + startView.getWidth()/2;
        int yStartView = startView.getTop() + startView.getHeight()/2;

        int xEndView = endView.getLeft() + endView.getWidth()/2;
        int yEndView = endView.getTop() + endView.getHeight() / 2;


        canvas.drawLine(xStartView, yStartView, xEndView, yEndView, paint);
    }

    public void drawArrowHead(Canvas canvas) {

        if (startView.equals(endView)) {
            return;
        }

        Path path = new Path();

        //draws triangle pointing to the right
        path.moveTo( endView.getLeft() + endView.getWidth()/2, endView.getTop() + endView.getHeight()/2 - 17);
        //arrow head
        path.lineTo( endView.getLeft() + endView.getWidth()/2 + 30, endView.getTop() + endView.getHeight()/2);

        path.lineTo( endView.getLeft() + endView.getWidth()/2, endView.getTop() + endView.getHeight()/2 + 17);
        path.close();

        //turns triangle
        Matrix matrix = new Matrix();
        matrix.reset();
        float rotate = whichArrow(startView, endView, digitOne, digitTwo);
        matrix.postRotate(rotate, endView.getLeft() + endView.getWidth()/2, endView.getTop() + endView.getHeight()/2);
        path.transform(matrix);

        canvas.drawPath(path, paint);
    }

    public int whichArrow(View view1, View view2, int first, int second) {

        if (first == 0) {
            first = 11;
        }
        if (second == 0) {
            second = 11;
        }

        //Arrow should point from right to left
        if ((((first > second) || (second == 1)) && (view1.getY() == view2.getY())) || (first == 2 || first == 3) && second == 1) {
            System.out.println("Angle: 180");
            return 180;
        }
        // Arrow to the right
        if ((view1.getY() == view2.getY())) {
            System.out.println("Angle: 0");
            return 0;
        }
        // Arrow should point downwards
        if (((first < second)) && (view1.getX() == view2.getX())) {
            System.out.println("Angle: 90");
            return 90;
        }
        // Arrow should point upwards
        if ((view1.getX() == view2.getX())) {
            System.out.println("Angle: 270");
            return 270;
        }
        //Arrow should point down from left to right
        if (((first < second)) && (((second - first == 4) || second - first == 8) || first - second == 7)) {
            System.out.println("Angle: 45");
            return 45;
        }
        //Arrow should point down from right to left
        if (((first < second)) && (second - first == 4 || second - first == 2) || first - second == 9) {
            System.out.println("Angle: 135");
            return 135;
        }
        //Arrow should point up from left to right
        if (((first > second)) && ((first - second == 4 && first == 7) || first - second == 2) || second - first == 9) {
            System.out.println("Angle: 45");
            return -45;
        }
        //Arrow should point up from right to left
        if ((((first > second)) && ((first - second == 4 || first - second == 8) || second - first == 7))) {
            System.out.println("Angle: -135");
            return -135;
        }
        //Arrow should point down angularly from left to right
        if (((first < second) && (second - first == 7)) || (first == 4 )) {
            System.out.println("Angle: 67");
            return 67;
        }
        //Arrow should point down angularly from right to left
        if (((first < second) && ((second - first == 5) && first !=1 )) || (first == 6 )) {
            System.out.println("Angle: 112");
            return 112;
        }
        //Arrow should point up angularly from left to right
        if (((first > second) && (first - second == 7)) || (second == 4 )) {
            System.out.println("Angle: -112");
            return -112;
        }
        //Arrow should point up angularly from right to left
        if (((first > second) && ((first - second == 5)) && first != 1) || (second == 6)) {
            System.out.println("Angle: -67");
            return -67;
        }
        //Arrow should point up flatly from left to right
        if (((first > second) && (first - second == 1))) {
            System.out.println("Angle: -22");
            return -22;
        }
        //Arrow should point down flatly from right to left
        if (((first < second) && (second - first == 1))) {
            System.out.println("Angle: -202");
            return -202;
        }
        //Arrow should point down flatly from left to right
        if (((first < second) && (second - first == 5))) {
            System.out.println("Angle: 22");
            return 22;
        }
        //Arrow should point up flatly from right to left
        if (((first > second) && (first - second == 5))) {
            System.out.println("Angle: 202");
            return 202;
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
