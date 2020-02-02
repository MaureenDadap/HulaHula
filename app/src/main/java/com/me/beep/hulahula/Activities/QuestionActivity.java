package com.me.beep.hulahula.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.me.beep.hulahula.Fragments.QuestionFragment;
import com.me.beep.hulahula.R;

public class QuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Intent intent = getIntent();
        final String category = intent.getStringExtra("category");

        final TextView tv1 = findViewById(R.id.tv1);
        final Button btn1 = findViewById(R.id.letsGoBtn);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv1.setVisibility(View.GONE);
                btn1.setVisibility(View.GONE);

                Bundle bundle = new Bundle();
                bundle.putString("category", category);

                Fragment qFrag = new QuestionFragment();
                qFrag.setArguments(bundle);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                        .add(R.id.qActivityLanding, qFrag);
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
