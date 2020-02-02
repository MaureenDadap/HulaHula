package com.me.beep.hulahula.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.me.beep.hulahula.Database.Player;
import com.me.beep.hulahula.Database.PlayerDatabase;
import com.me.beep.hulahula.R;
import com.me.beep.hulahula.Activities.VotingActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class QuestionFragment extends Fragment {
    private PlayerDatabase playerDatabase;
    private Player player;
    private int playerNumber;

    private String[] questionList;
    private List<String> playerList;
    private ArrayList<String> questionsAsked;
    private ArrayList<String> askedList;
    private Random random;
    private int randomInt;

    private TextView questionTv;
    private TextView askerTv;
    private TextView askedTv;
    private String category;
    private Button okBtn;

    private int index = 0;
    private int counter = 1;
    private int randomPlayer;

    public QuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        category = getArguments().getString("category");
        return inflater.inflate(R.layout.fragment_question, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        playerDatabase = PlayerDatabase.getInstance(getContext());

        if (category.equals("Food"))
            questionList = getResources().getStringArray(R.array.foodQuestions);
        else if (category.equals("Animal"))
            questionList = getResources().getStringArray(R.array.animalQuestions);

        random = new Random();
        askedTv = view.findViewById(R.id.askedTv);
        askerTv = view.findViewById(R.id.askerTv);
        questionTv = view.findViewById(R.id.questionTv);
        okBtn = view.findViewById(R.id.okBtn);

        questionsAsked = new ArrayList<>();
        askedList = new ArrayList<>();
        playerNumber = playerDatabase.playerDao().countPlayer();
        playerList = playerDatabase.playerDao().getNames();

        showPlayers();

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //counter++;

                if (counter == (playerNumber+1)) {
                    //Log.d(Integer.toString(index), Integer.toString(index));
                    Intent intent = new Intent(getContext(), VotingActivity.class);
                    startActivity(intent);
                    Objects.requireNonNull(getActivity()).finish();
                } else
                    showPlayers();
            }
        });
    }

    private int getRandomInt() {
        return randomInt = random.nextInt(questionList.length);
    }

    private void getRandomPlayer() {
        randomPlayer = random.nextInt(playerNumber);
    }

    private void showQuestion() {
        int randomQuestion = getRandomInt();

        if (!questionsAsked.contains(questionList[randomQuestion])) {
            questionTv.setText(questionList[randomQuestion]);
            questionsAsked.add(questionList[randomQuestion]);
        } else
            showQuestion();
    }

    private void showPlayers() {
        getRandomPlayer();

        if (index < playerList.size()) {


            if (!askedList.contains(playerList.get(randomPlayer)) && !playerList.get(index).equals(playerList.get(randomPlayer))) {
                askedTv.setText(playerList.get(randomPlayer));
                askedList.add(playerList.get(randomPlayer));

                askerTv.setText(playerList.get(index));
                showQuestion();
                counter++;
                index++;
            } else {
                showPlayers();
            }

        }
    }
}
