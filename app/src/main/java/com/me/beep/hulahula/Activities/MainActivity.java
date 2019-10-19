package com.me.beep.hulahula.Activities;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.me.beep.hulahula.Fragments.CategoriesFragment;
import com.me.beep.hulahula.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button playBtn;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        playBtn = (Button) findViewById(R.id.playBtn);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment categoriesFrag = new CategoriesFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .add(R.id.mainLanding, categoriesFrag)
                        .addToBackStack(null);
                transaction.commit();
            }
        });
    }
}
