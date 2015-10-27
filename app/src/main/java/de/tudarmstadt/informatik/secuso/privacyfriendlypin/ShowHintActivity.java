package de.tudarmstadt.informatik.secuso.privacyfriendlypin;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by yonjuni on 26.10.15.
 */
public class ShowHintActivity extends ActionBarActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_hint);

        Intent intent = getIntent();
        String pin = intent.getStringExtra("currentPin");

        TextView pinTextView = (TextView) findViewById(R.id.current_pin);
        pinTextView.setText(pin);

        //Actionbar
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#024265")));

        CheckPin checkPin = new CheckPin(pin);
        checkPin.determineCalculation();
        checkPin.determineDate();
        checkPin.determineWord();

        TextView wordTextView = (TextView) findViewById(R.id.wordTextView);
        TextView dateTextView = (TextView) findViewById(R.id.dateTextView);
        TextView mathTextView = (TextView) findViewById(R.id.mathTextView);

        wordTextView.setText(checkPin.resultWord);
        dateTextView.setText(checkPin.resultDate);
        mathTextView.setText(checkPin.resultCalculation);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
