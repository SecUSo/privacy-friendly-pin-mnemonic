package org.secuso.privacyfriendlypinmnenomic;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Arrays;


import org.secuso.privacyfriendlypin.R;
import org.secuso.privacyfriendlypinmnenomic.pinhelpers.CheckPin;
import org.secuso.privacyfriendlypinmnenomic.pinhelpers.DrawView;

public class ShowHintFragment extends Fragment {

    int[] input = new int[4];
    Activity activity;
    View rootView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_show_hint, container, false);
        this.rootView = rootView;

        ((ActionBarActivity)getActivity()).getSupportActionBar().setSubtitle(R.string.action_pin_tips);

        Bundle bundle = this.getArguments();

        String pin = "";
        String pinHyphen = "";

        if (bundle != null) {
            pin = bundle.getString("pin");
            pinHyphen = bundle.getString("pinHyphen");
        }

        TextView pinTextView = (TextView) rootView.findViewById(R.id.current_pin);
        pinTextView.setText(pinHyphen);

        //secure against Screenshot
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);

        //Buttons
        final Button[] numpad = new Button[10];
        numpad[0] = (Button) rootView.findViewById(R.id.button_zero_hint);
        numpad[1] = (Button) rootView.findViewById(R.id.button_one_hint);
        numpad[2] = (Button) rootView.findViewById(R.id.button_two_hint);
        numpad[3] = (Button) rootView.findViewById(R.id.button_three_hint);
        numpad[4] = (Button) rootView.findViewById(R.id.button_four_hint);
        numpad[5] = (Button) rootView.findViewById(R.id.button_five_hint);
        numpad[6] = (Button) rootView.findViewById(R.id.button_six_hint);
        numpad[7] = (Button) rootView.findViewById(R.id.button_seven_hint);
        numpad[8] = (Button) rootView.findViewById(R.id.button_eight_hint);
        numpad[9] = (Button) rootView.findViewById(R.id.button_nine_hint);

        Button toPractiseButton = (Button) rootView.findViewById(R.id.practiseButton);
        toPractiseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickPractiseButton();
            }
        });

        for (int i = 0; i < 4; i++) {
            input[i] = Integer.parseInt(Character.toString(pin.charAt(i)));
            numpad[input[i]].setBackgroundResource(R.drawable.hint_numpad_highlighted);
        }

        for (int i = 0; i < 4; i++) {
            if (i < 3) {
                drawArrow(numpad[input[i]], numpad[input[i + 1]], input[i], input[i + 1]);
                //System.out.println("STELLE IM INPUT ARRAY " + Integer.toString(input[i]) + " " + Integer.toString(input[i + 1]));
            }
        }


        CheckPin checkPin = new CheckPin(pin, activity.getBaseContext());

        TextView wordTextView = (TextView) rootView.findViewById(R.id.word);
        TextView dateFrameTextView = (TextView) rootView.findViewById(R.id.date);
        TextView mathFrameTextView = (TextView) rootView.findViewById(R.id.math);

        wordTextView.setText(checkPin.determineWord());
        dateFrameTextView.setText(checkPin.determineDate());
        mathFrameTextView.setText(checkPin.determineCalculation());

        return rootView;

    }

    public void drawArrow(Button first, Button second, int digitOne, int digitTwo) {
        DrawView drawView = new DrawView(activity, first, second, digitOne, digitTwo);
        drawView.setStrokeWidth(10);
        RelativeLayout numpadLayout = (RelativeLayout) rootView.findViewById(R.id.numpadFrame);
        numpadLayout.addView(drawView);
    }

    public boolean containsMultiples(int[] input, int i) {
        boolean hasMultiple = false;

        Arrays.sort(input);
        for (int j = 0; j < input.length; j++) {
            if (Arrays.binarySearch(input, input[j]) != -1) {
                hasMultiple = true;
            }
        }
        System.out.println("Hat die PIN doppelte Zahlen?" + hasMultiple);
        return hasMultiple;
    }

    public void clickPractiseButton() {

            final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, new PractiseFragment(), "PractiseFragment");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

}
