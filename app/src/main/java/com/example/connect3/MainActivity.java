package com.example.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    final int YELLOW = 0;
    final int RED = 1;
    final int UNPLAYED = 2;

    //0 = yellow, 1 = red
    int activePlayer = 0;

    //2 means unplayed
    int[] gameState = {UNPLAYED,UNPLAYED,UNPLAYED,UNPLAYED,UNPLAYED,UNPLAYED,UNPLAYED,UNPLAYED,UNPLAYED};
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void dropIn(View view){
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == UNPLAYED) {

            gameState[tappedCounter] = activePlayer;
            //move the counter to the top of the screen
            counter.setTranslationY(-1000);

            if (activePlayer == YELLOW) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = RED;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = YELLOW;
            }

            //and then bring it back down again
            counter.animate().translationYBy(1000f).setDuration(300);

            for(int[] winningPos: winningPositions){
                if(gameState[winningPos[0]] == gameState[winningPos[1]] &&
                gameState[winningPos[1]] == gameState[winningPos[2]] &&
                gameState[winningPos[0]] != 2){
                    System.out.println("Winner ==  " + gameState[winningPos[0]]);
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
