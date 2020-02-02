package com.me.beep.hulahula.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.me.beep.hulahula.Activities.MainActivity;
import com.me.beep.hulahula.R;

public class CategoriesFragment extends Fragment {
    private FragmentTransaction transaction;

    public CategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Fragment foodFrag = new CategoryFoodFragment();
        final Fragment animalFrag =  new CategoryAnimalFragment();

        Button foodBtn = (Button) view.findViewById(R.id.foodBtn);
        foodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = getFragmentManager().beginTransaction()
                        .add(R.id.mainLanding, foodFrag)
                        .addToBackStack(null);
                transaction.commit();

            }
        });

        Button animalBtn = (Button) view.findViewById(R.id.animalBtn);
        animalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = getFragmentManager().beginTransaction()
                        .add(R.id.mainLanding, animalFrag)
                        .addToBackStack(null);
                transaction.commit();
            }
        });


    }
}
