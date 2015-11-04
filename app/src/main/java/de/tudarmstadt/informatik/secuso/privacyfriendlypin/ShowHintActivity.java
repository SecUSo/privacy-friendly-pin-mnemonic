package de.tudarmstadt.informatik.secuso.privacyfriendlypin;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

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

        CheckPin checkPin = new CheckPin(pin, getBaseContext());
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();
        switch(item.getItemId()){
            case R.id.about:
                intent.setClass(this, AboutActivity.class);
                startActivityForResult(intent, 0);
                return true;
            case R.id.action_help:
                intent.setClass(this, HelpActivity.class);
                startActivityForResult(intent, 0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
