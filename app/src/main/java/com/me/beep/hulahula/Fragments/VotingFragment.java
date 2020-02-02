package com.me.beep.hulahula.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.beep.hulahula.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class VotingFragment extends Fragment {


    public VotingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_voting, container, false);
    }

}
