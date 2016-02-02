package music.com.br.musicgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import music.com.br.musicgame.entities.Jogo;
import music.com.br.musicgame.entities.Note;
import music.com.br.musicgame.entities.NoteGame;

public class GameActivity extends Activity {

    Jogo noteGame;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        noteGame = new NoteGame();

    }

    @Override
    protected void onStart() {
        super.onStart();
        getReferenceToViews();


        criarNovoJogo();
    }

    private void criarNovoJogo() {
        noteGame.newPhase();


        questionView.setText(noteGame.currentNote.toString());


        questionView.setText(noteGame.question);

        answer1View.setText(noteGame.currentAlternatives.get(0).toString());
        answer2View.setText(noteGame.currentAlternatives.get(1).toString());
        answer3View.setText(noteGame.currentAlternatives.get(2).toString());
    }

    private void getReferenceToViews() {
        questionView = (TextView) findViewById(R.id.questionsView);
        answer1View = (TextView) findViewById(R.id.answer1View);
        answer2View = (TextView) findViewById(R.id.answer2View);
        answer3View = (TextView) findViewById(R.id.answer3View);
    }

    public void onButtonClick(View v){

        if(v.getId() == R.id.answer1View){
            if(noteGame.rightAnswer(noteGame.currentAlternatives.get(0))){

            } else{

            }
            criarNovoJogo();
            return;
        }
        if(v.getId() == R.id.answer2View){
            if(noteGame.rightAnswer(noteGame.currentAlternatives.get(0))){

            } else{

            }
            criarNovoJogo();
            return;

        }
        if(v.getId() == R.id.answer3View){
            if(noteGame.rightAnswer(noteGame.currentAlternatives.get(0))){

            } else{

            }
            criarNovoJogo();
            return;

        }



    }

    TextView questionView, answer1View, answer2View, answer3View;

}
