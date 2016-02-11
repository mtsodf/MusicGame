package music.com.br.musicgame;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
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

    private static final int WAITING_ANSWER = 0;

    private static final int BUTTON_PRESSED = 1;

    int state = WAITING_ANSWER;

    MediaPlayer correctSound, wrongSound;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    private static final int WRONG = 0;

    private static final int CORRECT = 1;

    private CountDownTimer cronometer;

    private int correctAnswers = 0;
    private int wrongAnswers = 0;
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

        criarNovoJogo();
        correctSound =  MediaPlayer.create(this, R.raw.chime_bell_ding);
        wrongSound = MediaPlayer.create(this, R.raw.beep_short_off);

        AppIndex.AppIndexApi.start(client, viewAction);

        final TextView textViewCronometer = (TextView) findViewById(R.id.cronometerView);


        if(cronometer!=null){
            cronometer.cancel();
        }


        cronometer = new CountDownTimer(20000, 100) {


            @Override
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000;
                long centseconds = (millisUntilFinished % 1000) / 100;

                textViewCronometer.setText(seconds + "." + centseconds);
            }

            @Override
            public void onFinish() {
                textViewCronometer.setText("0.0");

            }
        };

        cronometer.start();
        Log.d("GameActivity", "chamou metodo on start.");

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

    public synchronized void onButtonClick(View v) {

        if(state == WAITING_ANSWER) {
            state = BUTTON_PRESSED;
            if (v.getId() == R.id.answer1View) {
                if (noteGame.rightAnswer(noteGame.currentAlternatives.get(0))) {
                    setTextColor(v, CORRECT);
                } else {
                    setTextColor(v, WRONG);
                }
                return;
            }
            if (v.getId() == R.id.answer2View) {
                if (noteGame.rightAnswer(noteGame.currentAlternatives.get(1))) {
                    setTextColor(v, CORRECT);
                } else {
                    setTextColor(v, WRONG);
                }
                return;

            }
            if (v.getId() == R.id.answer3View) {
                if (noteGame.rightAnswer(noteGame.currentAlternatives.get(2))) {
                    setTextColor(v, CORRECT);
                } else {
                    setTextColor(v, WRONG);
                }
                return;
            }
        }


    }

    private void setTextColor(View v, final int i) {
       final  TextView t = (TextView) v;

        if(i == WRONG){
            wrongSound.start();
            wrongAnswers++;
        } else{
            correctSound.start();
            correctAnswers++;

        }

        setPoints();

        new CountDownTimer(500,100){
            boolean alreadyRun = false;
            @Override
            public void onTick(long millisUntilFinished) {
                if(!alreadyRun) {

                    if (i == WRONG) {
                        Log.d("GameActivity", "Marcar de Vermelho.");
                        t.setTextColor(Color.RED);
                    } else {
                        Log.d("GameActivity", "Marcar de Verde.");
                        t.setTextColor(Color.GREEN);
                    }
                }

                alreadyRun = true;

            }

            @Override
            public void onFinish() {
                state = WAITING_ANSWER;
                Log.d("GameActivity", "Marcar de Branco.");
                t.setTextColor(Color.WHITE);
                criarNovoJogo();
            }
        }.start();

    }

    private void setPoints() {
        TextView pointsView = (TextView) findViewById(R.id.)
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

        Log.d("GameActivity", "chamou on stop.");
    }
}
