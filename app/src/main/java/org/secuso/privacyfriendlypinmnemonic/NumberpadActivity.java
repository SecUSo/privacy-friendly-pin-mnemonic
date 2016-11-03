package org.secuso.privacyfriendlypinmnemonic;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.widget.Button;

public class NumberpadActivity extends BaseActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numberpad);

        //Buttons
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

        SpannableString[] spannables = createSetSpannables();

        for (int i = 0; i < spannables.length; i++) {
            spannables[i].setSpan(new RelativeSizeSpan(1.8f), 0, 2, 0);
            numpad[i].setText(spannables[i]);
        }

        Button deleteButton = (Button) findViewById(R.id.button_delete);
        SpannableString delete = new SpannableString("  \n DEL");
        delete.setSpan(new RelativeSizeSpan(1.8f), 0, 1, 0);
        deleteButton.setTextColor(Color.TRANSPARENT);
        deleteButton.setText(delete);

        Button resetButton = (Button) findViewById(R.id.button_reset);
        SpannableString reset = new SpannableString("  \n RESET");
        reset.setSpan(new RelativeSizeSpan(1.8f), 0, 2, 0);
        resetButton.setTextColor(Color.TRANSPARENT);
        resetButton.setText(reset);

        overridePendingTransition(0, 0);
    }

    protected int getNavigationDrawerID() {
        return R.id.nav_numberpad;
    }

    public SpannableString[] createSetSpannables() {

        SpannableString[] spannables = new SpannableString[10];

        spannables[0] = new SpannableString(" 0 \n +");
        spannables[1] = new SpannableString(" 1 \n ");
        spannables[2] = new SpannableString(" 2 \n abc");
        spannables[3] = new SpannableString(" 3 \n def");
        spannables[4] = new SpannableString(" 4 \n ghi");
        spannables[5] = new SpannableString(" 5 \n jkl");
        spannables[6] = new SpannableString(" 6 \n mno");
        spannables[7] = new SpannableString(" 7 \n pqrs");
        spannables[8] = new SpannableString(" 8 \n tuv");
        spannables[9] = new SpannableString(" 9 \n wxyz");

        return spannables;
    }

}
