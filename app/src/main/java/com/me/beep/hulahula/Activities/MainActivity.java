package com.me.beep.hulahula.Activities;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.me.beep.hulahula.Database.Player;
import com.me.beep.hulahula.Database.PlayerDatabase;
import com.me.beep.hulahula.Fragments.CategoriesFragment;
import com.me.beep.hulahula.R;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {
    private PlayerDatabase playerDatabase;
    private Player player;

    private List<String> playerList;
    private ArrayList<Integer> deleteBtnIds;
    private ArrayList<Integer> cardViewIds;
    private String deleteBtnId;
    private String cardViewId;

    private CardView cardView;
    private CardView cardView2;
    private LayoutParams layoutParams;
    private LinearLayout linearLayout;

    private LinearLayout fieldsLanding;

    private String playerNameString;
    private EditText playerNameEt;
    private TextView playerNameTv;
    private FloatingActionButton playerDeleteFAB;

    FloatingActionButton addFieldFAB;
    Typeface typefacePorky;
    int id = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button playBtn;
        FloatingActionButton delete;
        int rowCount;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerDatabase = PlayerDatabase.getInstance(this);
        playerList = playerDatabase.playerDao().getNames();
        rowCount = dbRowCounter();

        fieldsLanding = findViewById(R.id.fieldsLanding);

        deleteBtnIds = new ArrayList<>();
        cardViewIds = new ArrayList<>();

        if (rowCount != 0) {
            displayPlayer();
        }

        playBtn = findViewById(R.id.playBtn);
        addFieldFAB = findViewById(R.id.addPlayerFieldFab);

        if (rowCount < 3 )
            playBtn.setEnabled(false);
        else
            playBtn.setEnabled(true);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment categoriesFrag = new CategoriesFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                        .add(R.id.mainLanding, categoriesFrag)
                        .addToBackStack(null);
                transaction.commit();
            }
        });

        addFieldFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPlayerField();
//                if (playerNameEt.getText().toString().trim().isEmpty()) {
//                    Toast.makeText(getApplicationContext(), "Enter a player first", Toast.LENGTH_LONG).show();
//                } else
//                    addPlayerField();
            }
        });
    }

    private int dbRowCounter() {
        return playerDatabase.playerDao().countPlayer();
    }

    private void addPlayerField() {
        createLayoutEnterName();
    }

    private void displayPlayer() {
        for (int i = 0; i < playerList.size(); i++) {
            createLayoutDisplayName();

            cardView2.setId(View.generateViewId());
            playerDeleteFAB.setId(View.generateViewId());
            Log.d(Integer.toString(cardView2.getId()), "Cardview ID " + cardView2.getId());
            //Log.d(Integer.toString(playerDeleteFAB.getId()), "FAB ID " + playerDeleteFAB.getId());

            deleteBtnIds.add(playerDeleteFAB.getId());
            cardViewIds.add(cardView2.getId());
            playerNameTv.setText(playerList.get(i));

            final int index = i;
            playerDeleteFAB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fieldsLanding.removeView(cardView2);
                    //((ViewGroup) findViewById(cardViewIds.get(index)).getParent()).removeView(cardView2);
                    playerNameString = playerNameTv.getText().toString();

                    deletePlayer();
                    Toast.makeText(getApplicationContext(), "Clicked delete", Toast.LENGTH_LONG).show();
                }
            });
        }


    }

    private void submitPlayer() {
        player = new Player(playerNameEt.getText().toString(), 0, "");
        playerDatabase.playerDao().insertPlayer(player);

        fieldsLanding.removeView(cardView);
        //((ViewGroup) findViewById(id).getParent()).removeView(cardView);
        displayPlayer();
    }

    public void deletePlayer() {
        player = new Player(playerNameString, 0, "");
        playerDatabase.playerDao().deletePlayer(player);
    }

    private void createLayoutDisplayName() {
        cardView2 = new CardView(this);
        layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(convertDpToPixel(8), 0, convertDpToPixel(8), convertDpToPixel(3));
        cardView2.setLayoutParams(layoutParams);
        cardView2.setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));
        cardView2.setRadius(convertDpToPixel(28));
        cardView2.setPadding(convertDpToPixel(5), convertDpToPixel(5), convertDpToPixel(5), convertDpToPixel(5));
        cardView2.setMaxCardElevation(convertDpToPixel(4));
        cardView2.setUseCompatPadding(true);


        linearLayout = new LinearLayout(this);
        layoutParams.setMargins(0, 0, 0, 0);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        cardView2.addView(linearLayout);

        playerNameTv = new TextView(this);
        layoutParams = new LayoutParams(convertDpToPixel(280), LayoutParams.WRAP_CONTENT);
        layoutParams.setMarginStart(convertDpToPixel(5));
        layoutParams.weight = 0;
        playerNameTv.setLayoutParams(layoutParams);
        typefacePorky = ResourcesCompat.getFont(getApplicationContext(), R.font.porkys_regular);
        playerNameTv.setTypeface(typefacePorky);
        playerNameTv.setPadding(convertDpToPixel(15), convertDpToPixel(15), convertDpToPixel(15), convertDpToPixel(15));
        playerNameTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        playerNameTv.setText("PLAYER NAME");
        linearLayout.addView(playerNameTv);

        playerDeleteFAB = new FloatingActionButton(this);
        layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, convertDpToPixel(8), convertDpToPixel(8), convertDpToPixel(8));
        layoutParams.weight = 1;
        layoutParams.gravity = Gravity.CENTER;
        playerDeleteFAB.setLayoutParams(layoutParams);
        playerDeleteFAB.setImageDrawable(getDrawable(R.drawable.ic_delete_black_24dp));
        playerDeleteFAB.setCustomSize(convertDpToPixel(50));
        linearLayout.addView(playerDeleteFAB);

        fieldsLanding.addView(cardView2);
    }

    private void createLayoutEnterName() {
        cardView = new CardView(this);
        layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(convertDpToPixel(8), 0, convertDpToPixel(8), convertDpToPixel(3));
        cardView.setLayoutParams(layoutParams);
        cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));
        cardView.setRadius(convertDpToPixel(28));
        cardView.setPadding(convertDpToPixel(5), convertDpToPixel(5), convertDpToPixel(5), convertDpToPixel(5));
        cardView.setUseCompatPadding(true);


        linearLayout = new LinearLayout(this);
        layoutParams.setMargins(0, convertDpToPixel(8), 0, convertDpToPixel(8));
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        cardView.addView(linearLayout);

        playerNameEt = new EditText(this);
        layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        layoutParams.setMarginStart(convertDpToPixel(19));
        layoutParams.setMarginEnd(convertDpToPixel(19));
        playerNameEt.setLayoutParams(layoutParams);
        typefacePorky = ResourcesCompat.getFont(getApplicationContext(), R.font.porkys_regular);
        playerNameEt.setTypeface(typefacePorky);
        playerNameEt.setSingleLine(true);
        playerNameEt.setEms(10);
        playerNameEt.setHint("PLAYER NAME");
        playerNameEt.setTextColor(getResources().getColor(R.color.colorAccent));
        linearLayout.addView(playerNameEt);

        fieldsLanding.addView(cardView);

        playerNameEt.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    submitPlayer();
                    return true;
                }
                return false;
            }
        });
    }

    public static int convertDpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }


}
