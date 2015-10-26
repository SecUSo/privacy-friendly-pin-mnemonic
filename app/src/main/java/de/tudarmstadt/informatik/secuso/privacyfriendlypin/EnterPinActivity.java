package de.tudarmstadt.informatik.secuso.privacyfriendlypin;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by yonjuni on 26.10.15.
 */
public class EnterPinActivity extends ActionBarActivity {

    private String pin;
    private int pinLength;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_pin);

        pin = "";
        pinLength = 0;

        Button[] numpad = new Button[10];

        numpad[0] = (Button) findViewById(R.id.button_zero);
        numpad[1] = (Button) findViewById(R.id.button_one);
        numpad[2] = (Button) findViewById(R.id.button_two);
        numpad[3] = (Button) findViewById(R.id.button_three);
        numpad[4] = (Button) findViewById(R.id.button_four);
        numpad[5] = (Button) findViewById(R.id.button_five);
        numpad[6] = (Button) findViewById(R.id.button_six);
        numpad[7] = (Button) findViewById(R.id.button_seven);
        numpad[8] = (Button) findViewById(R.id.button_eight);
        numpad[9] = (Button) findViewById(R.id.button_nine);

        for (int i = 0; i < numpad.length; i++) {
            final Button temp = numpad[i];
            final int tempInt = i;
            temp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (pinLength < 4) {
                        pin = pin += tempInt;
                        Log.d("PIN:", pin);
                        pinLength++;
                    }
                }
            });
        }

    }

}
