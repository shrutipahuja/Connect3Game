package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int currentPlayer = 0; //0 -> yellow_tile
    //1 -> red_tile
    //2 -> empty


    int[] originalGame = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winnerGames = {{0, 1, 2, 3, 4, 5}, {6, 7, 8, 9, 10, 11}, {12, 13, 14, 15, 16, 17},
                           {18, 19, 20, 21, 22, 23}, { 24, 25, 26, 27, 28, 29}, {30, 31, 32, 33, 34, 35},
                           {0, 6, 12, 18, 24, 30} , {1, 7, 13, 19, 25, 31}, {2, 8, 14, 20, 26, 32},
                           {3, 9, 15, 21, 27, 33}, { 4, 10, 16, 22, 28, 34}, {5, 11, 17, 23, 29, 35},
                           {0, 7, 14, 21, 28, 35}, {5, 10, 15, 20, 25, 30}};
    boolean currentGame = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropin(View view) {
        ImageView tile = (ImageView) view;

//        Log.i("TAG", counter.getTag().toString());
        int selectedTile = Integer.parseInt(tile.getTag().toString());

        if (originalGame[selectedTile] == 2 && currentGame) {
            originalGame[selectedTile] = currentPlayer;

            tile.setTranslationY(-1500);

            if (currentPlayer == 0) {
                tile.setImageResource(R.drawable.yellow_tile);
                currentPlayer = 1;
            } else {
                tile.setImageResource(R.drawable.red_tile);
                currentPlayer = 0;
            }

            tile.animate().translationYBy(1500).rotation(80).setDuration(300);

            for (int[] winnerPositions : winnerGames) {

                if ((originalGame[winnerPositions[0]] == originalGame[winnerPositions[1]])
                        && (originalGame[winnerPositions[1]] == originalGame[winnerPositions[2]])
                        && (originalGame[winnerPositions[2]] == originalGame[winnerPositions[3]])
                        && (originalGame[winnerPositions[3]] == originalGame[winnerPositions[4]])
                        && (originalGame[winnerPositions[4]] == originalGame[winnerPositions[5]])
                        && (originalGame[winnerPositions[2]] != 2)) {

                    currentGame = false;
                    String winner = "";
                    if (currentPlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }

                    TextView winnerTitle = findViewById(R.id.winnerTitle);
                    Button newGameButton = findViewById(R.id.newGameButton);

                    winnerTitle.setText(winner + " has won");
                    winnerTitle.setVisibility(View.VISIBLE);
                    newGameButton.setVisibility(View.VISIBLE);

//                    Toast.makeText(this, "Somebody has won!", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

    public void newGame(View view) {

        TextView winnerTitle = findViewById(R.id.winnerTitle);
        Button newGameButton = findViewById(R.id.newGameButton);
        winnerTitle.setVisibility(View.VISIBLE);
        newGameButton.setVisibility(View.VISIBLE);

        GridLayout gameLayout = findViewById(R.id.gameLayout);
        for (int i = 0; i < gameLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gameLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        currentPlayer = 0; //0 -> yellow_tile
        //1 -> red_tile
        //2 -> empty

        for (int i = 0; i < 35; i++) {
            originalGame[i] = 2;
        }

        currentGame = true;


    }


}
