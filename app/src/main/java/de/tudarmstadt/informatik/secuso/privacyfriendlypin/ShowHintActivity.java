package de.tudarmstadt.informatik.secuso.privacyfriendlypin;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Arrays;

import de.tudarmstadt.informatik.secuso.privacyfriendlypin.pinhelpers.CheckPin;

public class ShowHintActivity extends ActionBarActivity {

    int[] input = new int[4];

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_hint);

        Intent intent = getIntent();
        String pin = intent.getStringExtra("currentVisiblePin");
        String pinHyphen = intent.getStringExtra("currentPin");

        TextView pinTextView = (TextView) findViewById(R.id.current_pin);
        pinTextView.setText(pinHyphen);

        //secure against Screenshot
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);

        //Actionbar
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#024265")));

        //Buttons
        final Button[] numpad = new Button[10];
        numpad[0] = (Button) findViewById(R.id.button_zero_hint);
        numpad[1] = (Button) findViewById(R.id.button_one_hint);
        numpad[2] = (Button) findViewById(R.id.button_two_hint);
        numpad[3] = (Button) findViewById(R.id.button_three_hint);
        numpad[4] = (Button) findViewById(R.id.button_four_hint);
        numpad[5] = (Button) findViewById(R.id.button_five_hint);
        numpad[6] = (Button) findViewById(R.id.button_six_hint);
        numpad[7] = (Button) findViewById(R.id.button_seven_hint);
        numpad[8] = (Button) findViewById(R.id.button_eight_hint);
        numpad[9] = (Button) findViewById(R.id.button_nine_hint);

        drawArrow(numpad[1], numpad[9]);

        for (int i = 0; i < 4; i++) {
            input[i] = Integer.parseInt(Character.toString(pin.charAt(i)));
            //if (!containsMultiples(input)) {
                numpad[input[i]].setBackgroundResource(R.drawable.hint_numpad_highlighted);
            //}
        }

        CheckPin checkPin = new CheckPin(pin, getBaseContext());

        Button wordFrame = (Button) findViewById(R.id.showWordButton);
        Button dateFrame = (Button) findViewById(R.id.showDateButton);
        Button mathFrame = (Button) findViewById(R.id.showMathButton);

        wordFrame.setText(checkPin.determineWord());
        dateFrame.setText(checkPin.determineDate());
        mathFrame.setText(checkPin.determineCalculation());

        //Frames
        if (!checkPin.isWord) {
            wordFrame.setBackgroundResource(R.drawable.hints_display_no_hint);
            wordFrame.setTextColor(Color.parseColor("#A8A8A8"));
        }

        if (!checkPin.isDate) {
            dateFrame.setBackgroundResource(R.drawable.hints_display_no_hint);
            dateFrame.setTextColor(Color.parseColor("#A8A8A8"));
        }

        if (!checkPin.isCalculatable) {
            mathFrame.setBackgroundResource(R.drawable.hints_display_no_hint);
            mathFrame.setTextColor(Color.parseColor("#A8A8A8"));
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();
        switch (item.getItemId()) {
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

    public void drawArrow(Button first, Button second) {

    }

    public boolean containsMultiples(int[] input) {
        boolean hasMultiple = false;
        Arrays.sort(input);
        for (int i = 0; i < input.length; i++) {
            if (Arrays.binarySearch(input, input[i]) != -1) {
                hasMultiple = true;
            }
        }
        System.out.println("Hat die PIN doppelte Zahlen?" + hasMultiple);
        return hasMultiple;
    }

}
