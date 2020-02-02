package com.me.beep.hulahula.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.me.beep.hulahula.Activities.RevealWordActivity;
import com.me.beep.hulahula.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFoodFragment extends Fragment {
    private Boolean doubleRound;


    public CategoryFoodFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category_food, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button playBtn;
        CheckBox doubleQuesCheck;
        final String category = "Food";

        playBtn = (Button) view.findViewById(R.id.foodPlayBtn);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RevealWordActivity.class);
                intent.putExtra("category", category);
                startActivity(intent);
            }
        });

        doubleQuesCheck =(CheckBox) view.findViewById(R.id.foodDoubleCb);
        doubleQuesCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doubleRound = true;
            }
        });
    }
}
