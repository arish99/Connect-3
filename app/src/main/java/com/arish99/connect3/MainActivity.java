package com.arish99.connect3;

import android.os.Bundle;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    int activeplayer=0;
    int[] gamestate={2,2,2,2,2,2,2,2,2};
    int winningpos[][] = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameisActive=true;
    public void dropin(View view){

        ImageView counter=(ImageView) view;

        int tapped= Integer.parseInt(counter.getTag().toString()); 
        counter.setTranslationY(-1000f);
        if(gamestate[tapped]==2 && gameisActive) {
            gamestate[tapped]=activeplayer;
            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.o);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.x);
                activeplayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
            for(int[] winP : winningpos) {
                if(gamestate[winP[0]] == gamestate[winP[1]] &&
                        gamestate[winP[1]] == gamestate[winP[2]] &&
                gamestate[winP[0]] != 2){
                    String win = "X";
                    gameisActive=false;
                    if(gamestate[winP[0]]== 0){
                        win="O";
                    }
                    TextView champ= (TextView)findViewById(R.id.winner);
                    champ.setText(win+" HAS WON!!!");
                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainlayout);
                    layout.setVisibility(view.VISIBLE);
                }
                else{
                    boolean gameisOver=true;
                    for(int counterState:gamestate){
                        if(counterState==2) gameisOver=false;
                    }
                    if(gameisOver){
                    TextView winmess=(TextView)findViewById(R.id.winner);
                    winmess.setText("Its a draw");
                    LinearLayout layout=(LinearLayout)findViewById(R.id.playAgainlayout);
                    layout.setVisibility(view.VISIBLE);
                }
                }
            }
        }

    }
    public void playAgain(View view){
        gameisActive=true;
        LinearLayout layout=(LinearLayout)findViewById(R.id.playAgainlayout);

        layout.setVisibility(view.INVISIBLE);
         activeplayer=0;
       for(int i=0;i<gamestate.length;i++){
           gamestate[i]=2;
       }
        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);
       for (int i=0;i<gridLayout.getChildCount();i++){
           ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

       }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}