/**
 * @author JKA
 * @appname tictactoe
 *
 * */

package com.example.crisscrossgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
  //    0 = yellow , 1 = red
  int activePlayer = 0;

  boolean gameIsActive = true;

  // 2 means unplayed (9 bcz of 9 blocks)
  int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
  int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

  public void dropIn(View view){
    ImageView counter = (ImageView) view;   //when clicks happen on grid

    int tappedCounter = Integer.parseInt(counter.getTag().toString());

    if (gameState[tappedCounter] == 2 && gameIsActive == true) {
      gameState[tappedCounter] = activePlayer;

      counter.setTranslationY(-1000);

      if (activePlayer==0) {
        counter.setImageResource(R.drawable.yellow);
        activePlayer = 1;
      } else {
        counter.setImageResource(R.drawable.red);
        activePlayer = 0;
      }

      counter.animate().translationYBy(1000).rotation(360).setDuration(300);

      for(int[] position: winningPositions ){   //position will be nested array from winngPos 2d array
        if(gameState[position[0]] == gameState[position[1]] && gameState[position[1]]==gameState[position[2]] && gameState[position[0]] != 2){

          gameIsActive = false;

          String winner="Red";
          if(gameState[position[0]]==0){
            winner = "Yellow";
          }

          //show winner
          TextView message = (TextView) findViewById(R.id.winnerMessage);
          message.setText(winner+" has won!");

          //if user wins game then display the play agian layout
          LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
          layout.setVisibility(View.VISIBLE);

        }else{
          //if game draws
          boolean gameIsOver = true;
          for(int i=0;i<gameState.length;i++){
            if(gameState[i] == 2)     //means there is still some steps/turns left
              gameIsOver = false;
          }

          if(gameIsOver){
            TextView message = (TextView) findViewById(R.id.winnerMessage);
            message.setText("it's a Draw!");

            //if game draws then display the play agian layout
            LinearLayout layout = (LinearLayout)  findViewById(R.id.playAgainLayout);
            layout.setVisibility(View.VISIBLE);

          }

        }

      }

    }
  }

  public void playAgain(View view){

    gameIsActive = true;

    LinearLayout layout = (LinearLayout)  findViewById(R.id.playAgainLayout);
    layout.setVisibility(View.INVISIBLE);

    //reset active player and gamestate array

    for(int i=0;i<gameState.length;i++){
      gameState[i] = 2;
    }

    GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

    //gridLayout.getChildCount() = 9 as 9 diff blocks/grids
    for(int i=0;i<gridLayout.getChildCount();i++){
      ((ImageView) gridLayout.getChildAt(i)).setImageResource(0); //empty image on every grid/block
    }

  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }
}
