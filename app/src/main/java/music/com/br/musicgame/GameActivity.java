package music.com.br.musicgame;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import music.com.br.musicgame.entities.Jogo;
import music.com.br.musicgame.entities.Note;
import music.com.br.musicgame.entities.NoteGame;

public class GameActivity extends Activity {

    Jogo noteGame;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        noteGame = new NoteGame();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        getReferenceToViews();


        criarNovoJogo();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Game Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://music.com.br.musicgame/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    private void criarNovoJogo() {
        noteGame.newPhase();

        questionView.setText(noteGame.question);
        noteView.setText(noteGame.currentNote.toString());


        setAnswers();
    }

    private void setAnswers() {
        answer1View.setText(noteGame.currentAlternatives.get(0).toString());
        answer2View.setText(noteGame.currentAlternatives.get(1).toString());
        answer3View.setText(noteGame.currentAlternatives.get(2).toString());
    }

    private void getReferenceToViews() {
        questionView = (TextView) findViewById(R.id.questionsView);
        noteView = (TextView) findViewById(R.id.noteView);
        answer1View = (TextView) findViewById(R.id.answer1View);
        answer2View = (TextView) findViewById(R.id.answer2View);
        answer3View = (TextView) findViewById(R.id.answer3View);
    }

    public void onButtonClick(View v) {

        if (v.getId() == R.id.answer1View) {
            if (noteGame.rightAnswer(noteGame.currentAlternatives.get(0))) {
                setTextColor(v, 0);
            } else {
                setTextColor(v, 1);
            }
            criarNovoJogo();
            return;
        }
        if (v.getId() == R.id.answer2View) {
            if (noteGame.rightAnswer(noteGame.currentAlternatives.get(1))) {
                setTextColor(v, 0);
            } else {
                setTextColor(v, 1);
            }
            criarNovoJogo();
            return;

        }
        if (v.getId() == R.id.answer3View) {
            if (noteGame.rightAnswer(noteGame.currentAlternatives.get(2))) {
                setTextColor(v, 0);
            } else {
                setTextColor(v, 1);
            }
            criarNovoJogo();
            return;

        }


    }

    private void setTextColor(View v, int i) {
        TextView t = (TextView) v;

        if (i == 0)
            t.setTextColor(Color.RED);
        else
            t.setTextColor(Color.GREEN);


        t.setTextColor(Color.BLACK);

    }

    TextView questionView, noteView, answer1View, answer2View, answer3View;

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Game Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://music.com.br.musicgame/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
