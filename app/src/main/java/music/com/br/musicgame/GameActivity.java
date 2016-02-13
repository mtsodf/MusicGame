package music.com.br.musicgame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import music.com.br.musicgame.entities.Game;
import music.com.br.musicgame.entities.QuartasGame;
import music.com.br.musicgame.entities.TercaGame;

public class GameActivity extends Activity {

    Game noteGame;

    /*
    Constants of screen state.
     */
    private static final int WAITING_ANSWER = 0;
    private static final int BUTTON_PRESSED = 1;
    private static final int GAME_END = 2;

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
    private int points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        noteGame = getGame(getIntent().getStringExtra(MainActivity.GAME_NAME));

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private Game getGame(String stringExtra) {

        if(stringExtra.equals(MainActivity.QUARTAS_GAME)){
            return new QuartasGame();
        } else if(stringExtra.equals(MainActivity.TERCA_GAME)){
            return new TercaGame();
        }

        Log.d(this.getPackageName(), "No Game Returned");
        return null;
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


        correctSound =  MediaPlayer.create(this, R.raw.chime_bell_ding);
        wrongSound = MediaPlayer.create(this, R.raw.beep_short_off);

        AppIndex.AppIndexApi.start(client, viewAction);

        initiateGame();

        Log.d("GameActivity", "chamou metodo on start.");

    }

    private void startCronomoter() {

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

                endOfTheGame();
            }
        };
        cronometer.start();
    }

    private void endOfTheGame() {
        state = GAME_END;
        new AlertDialog.Builder(this).setTitle(getString(R.string.finishDialogTitle)).setMessage(getString(R.string.finishQuestion).replace("{1}", points + "")).setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                initiateGame();
            }
        }).setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                GameActivity.this.finish();
            }
        }).show();
    }

    private void initiateGame(){
        state = WAITING_ANSWER;
        points = 0;
        generateNewQuestion();
        startCronomoter();
    }

    private void generateNewQuestion() {
        noteGame.newPhase();

        questionView.setText(noteGame.question);
        setCurrentNote();

        setPoints();
        setAnswers();
    }

    private void setCurrentNote() {
        noteView.

                setText(noteGame.currentNote.toString());
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
            points = points > 2? points-2:0;

        } else{
            correctSound.start();

            points++;
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
                generateNewQuestion();
            }
        }.start();

    }

    private void setPoints() {
        TextView pointsView = (TextView) findViewById(R.id.pointsCount);
        pointsView.setText("Points: " + points);
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
