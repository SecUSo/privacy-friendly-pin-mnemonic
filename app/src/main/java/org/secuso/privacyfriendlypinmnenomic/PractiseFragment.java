package org.secuso.privacyfriendlypinmnenomic;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.secuso.privacyfriendlypin.R;

/**
 * Created by yonjuni on 10.11.15.
 */
public class PractiseFragment extends Fragment{

    View rootView;
    EditText pinEditText;

    String visiblePin;
    String pin;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_practise, container, false);
        ((ActionBarActivity)getActivity()).getSupportActionBar().setSubtitle(R.string.action_practise);

        this.rootView = rootView;

        visiblePin = "";
        pin = "";

        pinEditText = (EditText) rootView.findViewById(R.id.displayPin);
        pinEditText.setInputType(InputType.TYPE_NULL);

        //Buttons
        Button[] numpad = new Button[10];
        numpad[0] = (Button) rootView.findViewById(R.id.button_zero);
        numpad[1] = (Button) rootView.findViewById(R.id.button_one);
        numpad[2] = (Button) rootView.findViewById(R.id.button_two);
        numpad[3] = (Button) rootView.findViewById(R.id.button_three);
        numpad[4] = (Button) rootView.findViewById(R.id.button_four);
        numpad[5] = (Button) rootView.findViewById(R.id.button_five);
        numpad[6] = (Button) rootView.findViewById(R.id.button_six);
        numpad[7] = (Button) rootView.findViewById(R.id.button_seven);
        numpad[8] = (Button) rootView.findViewById(R.id.button_eight);
        numpad[9] = (Button) rootView.findViewById(R.id.button_nine);

        for (int i = 0; i < numpad.length; i++) {
            final Button temp = numpad[i];
            final int tempInt = i;
            temp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (pinEditText.getText().length() < 4) {
                        visiblePin = visiblePin += tempInt;
                        pin = pin += tempInt;
                        if (pin.length() == 2) {
                            pin += "-";
                        }
                        Log.d("PIN:", visiblePin);
                        pinEditText.setText(visiblePin, TextView.BufferType.EDITABLE);
                    }
                }
            });
        }

        Button deleteButton = (Button) rootView.findViewById(R.id.button_delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                pinEditText.setText(deletePinDigits(visiblePin));
                visiblePin = deletePinDigits(visiblePin);
                pin = deletePinDigits(visiblePin);
            }
        });

        Button resetButton = (Button) rootView.findViewById(R.id.button_reset);
        resetButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                pinEditText.getText().clear();
                resetPins();
            }
        });

        return rootView;
    }

    public String deletePinDigits(String visiblePin) {

        if (visiblePin.length() > 0) {
            visiblePin = visiblePin.substring(0, visiblePin.length() - 1);
        }

        return visiblePin;
    }

    public void resetPins() {
        visiblePin = "";
        pin = "";
    }
}
