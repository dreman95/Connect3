package com.example.connect3;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final int YELLOW = 0;
    final int RED = 1;
    final int UNPLAYED = 2;

    //0 = yellow, 1 = red
    int activePlayer = 0;

    //2 means unplayed
    int[] gameState = {UNPLAYED,UNPLAYED,UNPLAYED,UNPLAYED,UNPLAYED,UNPLAYED,UNPLAYED,UNPLAYED,UNPLAYED};
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    boolean gameIsactive = true;

    public void dropIn(View view){
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == UNPLAYED && gameIsactive) {

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

                    //Someone has won
                    gameIsactive = false;
                    //Display play again Icon
                    TextView winnerText = findViewById(R.id.winnerMessage);
                    LinearLayout layout = findViewById(R.id.playAgainLayout);
                    if(gameState[winningPos[0]] == YELLOW){
                        layout.setBackgroundColor(Color.parseColor("#FFEB3B"));
                        winnerText.setText("Yellow has won!");
                    }
                    else{
                        layout.setBackgroundColor(Color.parseColor("#FF0000"));
                        winnerText.setText("Red has won!");
                    }

                    layout.setVisibility(View.VISIBLE);
                }
                else{
                    boolean gameIsOver = true;
                    //if no one has one game is a draw
                    for(int counterState: gameState){
                        if(counterState == UNPLAYED){
                            gameIsOver = false;
                        }
                    }
                    if(gameIsOver){
                        //Display play again Icon
                        TextView winnerText = findViewById(R.id.winnerMessage);
                        LinearLayout layout = findViewById(R.id.playAgainLayout);
                        layout.setBackgroundColor(Color.parseColor("#FF7F00"));
                        winnerText.setText("DRAW");
                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    public void playAgain(View view){
        //Set the Play again layout to invisible
        LinearLayout layout = findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        //Reset the game state
        activePlayer = 0;
        for(int i = 0; i < gameState.length; i++){
            gameState[i] = UNPLAYED;
        }
        //Loop through each image counter on the board and set the image to empty
        GridLayout board = findViewById(R.id.gridLayout);
        for(int i = 0; i < board.getChildCount(); i++){
            ((ImageView) board.getChildAt(i)).setImageResource(0);
        }
        gameIsactive = true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
