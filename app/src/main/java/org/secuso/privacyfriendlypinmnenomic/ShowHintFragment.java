package org.secuso.privacyfriendlypinmnenomic;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

        ((ActionBarActivity) getActivity()).getSupportActionBar().setSubtitle(R.string.action_pin_mnenomics);

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
        }

        int[] multiples = containsMultiples(input);

        for (int j = 0; j < input.length; j++) {
            colorNumpad(multiples[j], numpad[input[j]]);
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

        ImageView[] symbolRow = new ImageView[4];
        symbolRow[0] = (ImageView) rootView.findViewById(R.id.symbolImageView1);
        symbolRow[1] = (ImageView) rootView.findViewById(R.id.symbolImageView2);
        symbolRow[2] = (ImageView) rootView.findViewById(R.id.symbolImageView3);
        symbolRow[3] = (ImageView) rootView.findViewById(R.id.symbolImageView4);

        for (int i=0; i<symbolRow.length; i++) {
            assignSymbol(input[i], symbolRow[i]);
        }


        return rootView;

    }

    public void drawArrow(Button first, Button second, int digitOne, int digitTwo) {
        DrawView drawView = new DrawView(activity, first, second, digitOne, digitTwo);
        drawView.setStrokeWidth(10);
        RelativeLayout numpadLayout = (RelativeLayout) rootView.findViewById(R.id.numpadFrame);
        numpadLayout.addView(drawView);
    }

    public int[] containsMultiples(int[] input) {

        int[] multiples = new int[4];

        for (int i = 0; i < multiples.length; i++) {
            multiples[i] = 1;
        }

        for (int j = 0; j < input.length; j++) {
            for (int k = 0; k < input.length; k++) {
                if (k == j) {
                    continue;
                } else if (input[j] == input[k]) {
                    multiples[j]++;
                }
            }
        }
        for (int l = 0; l < input.length; l++) {
            System.out.println("Input Array: " + Integer.toString(input[l]));
            System.out.println("Multiples Array: " + Integer.toString(multiples[l]));
        }
        return multiples;
    }

    public void clickPractiseButton() {

        final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, new PractiseFragment(), "PractiseFragment");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    public void colorNumpad(int multiple, Button button) {

        switch (multiple) {
            case 1:
                button.setBackgroundResource(R.drawable.hint_numpad_highlighted);
                //System.out.println(Integer.toString(multiple) + " BLUE");
                break;
            case 2:
                button.setBackgroundResource(R.drawable.hint_numpad_highlighted_two);
                //System.out.println(Integer.toString(multiple) + " YELLOW");
                break;
            case 3:
                button.setBackgroundResource(R.drawable.hint_numpad_highlighted_three);
                //System.out.println(Integer.toString(multiple) + " BLACK");
                break;
            case 4:
                button.setBackgroundResource(R.drawable.hint_numpad_highlighted_four);
                //System.out.println(Integer.toString(multiple) + " WHITE");
                break;
            default:
                button.setBackgroundResource(R.drawable.hints_numpad);
                break;
        }
    }

    public void assignSymbol(int digit, ImageView imageView) {
        switch (digit) {
            case 0:
                imageView.setImageResource(R.drawable.zero);
                break;
            case 1:
                imageView.setImageResource(R.drawable.one);
                break;
            case 2:
                imageView.setImageResource(R.drawable.two);
                break;
            case 3:
                imageView.setImageResource(R.drawable.three);
                break;
            case 4:
                imageView.setImageResource(R.drawable.four);
                break;
            case 5:
                imageView.setImageResource(R.drawable.five);
                break;
            case 6:
                imageView.setImageResource(R.drawable.six);
                break;
            case 7:
                imageView.setImageResource(R.drawable.seven);
                break;
            case 8:
                imageView.setImageResource(R.drawable.eight);
                break;
            case 9:
                imageView.setImageResource(R.drawable.nine);
                break;
            default:
                imageView.setImageResource(0);
                break;
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
