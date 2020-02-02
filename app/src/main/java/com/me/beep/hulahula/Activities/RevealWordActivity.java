package com.me.beep.hulahula.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.me.beep.hulahula.Database.Player;
import com.me.beep.hulahula.Database.PlayerDatabase;
import com.me.beep.hulahula.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RevealWordActivity extends AppCompatActivity {
    private PlayerDatabase playerDatabase;
    private Player player;

    public FrameLayout wordLanding;
    private ImageView imageView;
    private Button revealBtn;
    private Button nextBtn;
    private CardView cardview;
    private TextView playerTv;
    private TextView wordTv;
    private CheckBox correctPlayer;
    private FragmentTransaction transaction;

    private String category;
    private String[] wordList;
    private List<String> playerList;
    private List<String> playerWordList;
    private String magicWord;

    private Random random;
    private int randomInt;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal_word);


        playerDatabase = PlayerDatabase.getInstance(this);
        playerList = playerDatabase.playerDao().getNames();

        wordLanding = (FrameLayout) findViewById(R.id.wordLanding);
        correctPlayer = (CheckBox) findViewById(R.id.checkPlayerCb);
        revealBtn = (Button) findViewById(R.id.confirmBtn);
        nextBtn = (Button) findViewById(R.id.nextBtn);
        cardview = (CardView) findViewById(R.id.wordCv);
        playerTv = (TextView) findViewById(R.id.playerNameTf);
        wordTv = (TextView) findViewById(R.id.wordTv);

        Intent intent = getIntent();
        category = intent.getStringExtra("category");

        if (category.equals("Food"))
            wordList = getResources().getStringArray(R.array.foodArray);
        else if (category.equals("Animal"))
            wordList = getResources().getStringArray(R.array.animalsArray);

        random = new Random();
        randomInt = random.nextInt(wordList.length);
        magicWord = wordList[randomInt];

        randomInt = random.nextInt(playerList.size());
        assignWordToPlayer();

        revealWord();

        correctPlayer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (correctPlayer.isChecked()) {
                    revealBtn.setVisibility(View.VISIBLE);
                } else {
                    revealBtn.setVisibility(View.INVISIBLE);
                }
            }
        });

        revealBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correctPlayer.setVisibility(View.GONE);
                imageView.setVisibility(View.GONE);
                revealBtn.setVisibility(View.GONE);
                nextBtn.setVisibility(View.VISIBLE);
                cardview.setVisibility(View.VISIBLE);
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index++;
                if (index < playerList.size())
                    revealWord();
                else {
                    Intent goToQuestion = new Intent(getApplicationContext(), QuestionActivity.class);
                    goToQuestion.putExtra("category", category);
                    startActivity(goToQuestion);
                    finish();
                }
            }
        });
    }

    private void revealWord() {
        setCover();
        playerTv.setText(playerList.get(index));
        String itsMe = "I'm " + playerList.get(index);
        correctPlayer.setText(itsMe);
        playerWordList = playerDatabase.playerDao().getWords();
        wordTv.setText(playerWordList.get(index));

        correctPlayer.setChecked(false);

        nextBtn.setVisibility(View.GONE);
        cardview.setVisibility(View.GONE);
        correctPlayer.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.VISIBLE);
        revealBtn.setVisibility(View.INVISIBLE);
    }

    private void setCover() {
        imageView = new ImageView(getApplicationContext());
        imageView.setImageDrawable(getDrawable(R.drawable.ic_question_mark));

        cardview.setVisibility(View.GONE);

        imageView.setVisibility(View.VISIBLE);
        wordLanding.addView(imageView);
    }

    private void assignWordToPlayer() {
        for (int i = 0; i < playerList.size(); i++) {
            if (i == randomInt) {
                //playerWordList.set(i, "You Don't Know");
                player = new Player(playerList.get(i), 0, "Wala Kang Alam");
            } else {
                //playerWordList.set(i, magicWord);
                player = new Player(playerList.get(i), 0, magicWord);
            }
            playerDatabase.playerDao().updatePlayer(player);
        }
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
