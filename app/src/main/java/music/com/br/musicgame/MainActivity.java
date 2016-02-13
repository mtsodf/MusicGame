package music.com.br.musicgame;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String GAME_NAME = "music.com.br.musicgame.MainActivity.gamename";


    public static final String QUARTAS_GAME = "QUARTAS_GAME";

    public static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
    }

    public static Context getContext(){
        return mContext;
    }

    public void onButtonClick(View v){
        if(v.getId() == R.id.level1Btn){
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra(GAME_NAME, QUARTAS_GAME);
            startActivity(intent);
        }
    }
}
