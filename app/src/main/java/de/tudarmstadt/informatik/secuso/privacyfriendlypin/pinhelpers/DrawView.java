package de.tudarmstadt.informatik.secuso.privacyfriendlypin.pinhelpers;

import android.content.Context;
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
    }

    public void computeLinePoints() {

    }
}
