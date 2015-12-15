package org.secuso.privacyfriendlypinmnenomic;

import android.app.Activity;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.secuso.privacyfriendlypin.R;

public class HelpFragment extends PreferenceFragment {

    Activity activity;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((ActionBarActivity)getActivity()).getSupportActionBar().setSubtitle(R.string.action_help);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = super.onCreateView(inflater, container, savedInstanceState);
        container.removeAllViews();
        addPreferencesFromResource(R.xml.preferences);

        return root;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }


}
