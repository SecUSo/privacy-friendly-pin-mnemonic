package de.tudarmstadt.informatik.secuso.privacyfriendlypin;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

/**
 * Created by yonjuni on 26.10.15.
 */
public class ShowHintActivity extends ActionBarActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_hint);

        Intent intent = getIntent();
        String tempPin = intent.getStringExtra("currentPin");

        TextView pinTextView = (TextView) findViewById(R.id.current_pin);
        pinTextView.setText(tempPin);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#024265")));
    }

}
