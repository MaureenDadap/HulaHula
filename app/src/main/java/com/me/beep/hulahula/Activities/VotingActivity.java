package com.me.beep.hulahula.Activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.me.beep.hulahula.R;
import com.me.beep.hulahula.Fragments.VotingFragment;

public class VotingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);

        final Button btn = findViewById(R.id.letsGoBtn3);
        final TextView tv = findViewById(R.id.tv2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setVisibility(View.GONE);
                btn.setVisibility(View.GONE);


                Fragment vFrag = new VotingFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                        .add(R.id.vActivityLanding, vFrag);
                transaction.commit();
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("DISMISS PLAY?");
        builder.setMessage("Are you sure you want to go back? ");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
