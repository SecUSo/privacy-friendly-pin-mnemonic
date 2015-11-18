package org.secuso.privacyfriendlypinmnenomic;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.secuso.privacyfriendlypin.R;

/**
 * Created by yonjuni on 10.11.15.
 */
public class PractiseFragment extends Fragment{

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_practise, container, false);
        ((ActionBarActivity)getActivity()).getSupportActionBar().setSubtitle(R.string.action_practise);

        return rootView;
    }
}
