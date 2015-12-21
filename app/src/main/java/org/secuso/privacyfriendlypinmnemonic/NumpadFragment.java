package org.secuso.privacyfriendlypinmnemonic;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.secuso.privacyfriendlypin.R;

/**
 * Created by yonjuni on 21.12.15.
 */
public class NumpadFragment extends Fragment {

        View rootView;

        Activity activity;

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            container.removeAllViews();
            View rootView = inflater.inflate(R.layout.fragment_numpad, container, false);
            ((ActionBarActivity) getActivity()).getSupportActionBar().setSubtitle(R.string.action_numpad);

            this.rootView = rootView;

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

            SpannableString[] spannables = createSetSpannables();

            for (int i = 0; i < spannables.length; i++) {
                spannables[i].setSpan(new RelativeSizeSpan(1.8f), 0, 2, 0);
                numpad[i].setText(spannables[i]);
            }

            Button deleteButton = (Button) rootView.findViewById(R.id.button_delete);
            SpannableString delete = new SpannableString("  \n DEL");
            delete.setSpan(new RelativeSizeSpan(1.8f), 0, 1, 0);
            deleteButton.setTextColor(Color.TRANSPARENT);
            deleteButton.setText(delete);

            Button resetButton = (Button) rootView.findViewById(R.id.button_reset);
            SpannableString reset = new SpannableString("  \n RESET");
            reset.setSpan(new RelativeSizeSpan(1.8f), 0, 2, 0);
            resetButton.setTextColor(Color.TRANSPARENT);
            resetButton.setText(reset);

            return rootView;
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

        public void onAttach(Activity activity) {
            super.onAttach(activity);
            this.activity = activity;
        }




}
