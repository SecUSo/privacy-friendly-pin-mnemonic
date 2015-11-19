package org.secuso.privacyfriendlypinmnenomic;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.secuso.privacyfriendlypin.R;

public class EnterPinFragment extends Fragment {

    private String pin;
    private String visiblePin;
    EditText pinEditText;
    Activity activity;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_enter_pin, container, false);

        ((ActionBarActivity) getActivity()).getSupportActionBar().setSubtitle(R.string.action_enter_pin);

        pinEditText = (EditText) rootView.findViewById(R.id.displayPin);
        pinEditText.setInputType(InputType.TYPE_NULL);


        resetPins();

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

        Button doneButton = (Button) rootView.findViewById(R.id.button_done);
        doneButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                clickDoneButton();
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


    public void clickDoneButton() {

        if (pinEditText.getText().length() == 4) {

            Bundle bundle = new Bundle();
            bundle.putString("pin", visiblePin);
            bundle.putString("pinHyphen", pin);

            ShowHintFragment showHintFragment = new ShowHintFragment();
            showHintFragment.setArguments(bundle);

            final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, showHintFragment, "ShowHintFragment");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

            doFirstRun();
        }
    }

    public String deletePinDigits(String visiblePin) {

        if (visiblePin.length() > 0) {
            return visiblePin = visiblePin.substring(0, visiblePin.length() - 1);
        }
        return visiblePin;
    }

    public void resetPins() {
        visiblePin = pin = "";
    }

    private void doFirstRun() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        sharedPreferences.edit().putString("versionname", "").commit();
        SharedPreferences settings = activity.getSharedPreferences("versionname", activity.getBaseContext().MODE_PRIVATE);
        if (settings.getBoolean("isFirstRun", true)) {
            Toast toast = Toast.makeText(activity.getApplicationContext(), getString(R.string.show_help_hint), Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("isFirstRun", false);
            editor.commit();
        }
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
