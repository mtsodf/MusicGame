package music.com.br.musicgame;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import music.com.br.musicgame.entities.Note;
import music.com.br.musicgame.entities.NoteGame;

public class GameActivity extends Activity {

    NoteGame noteGame;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        noteGame = new NoteGame();

    }

    @Override
    protected void onStart() {
        super.onStart();

        noteGame.newPhase();

        TextView textView = new TextView(this);

        textView.setText(noteGame.currentNote.toString());

        questionView().addView(textView);

        for(Note n : noteGame.currentAlternatives) {
            TextView textView1 = new TextView(this);
            textView1.setText(n.toString());

            answerView().addView(textView1);

        }
    }

    public LinearLayout questionView(){
        return (LinearLayout) findViewById(R.id.QuestionView);
    }
    public LinearLayout answerView(){
        return (LinearLayout) findViewById(R.id.AnswerView);
    }
}
