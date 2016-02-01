package music.com.br.musicgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onButtonClick(View v){
        if(v.getId() == R.id.level1Btn){
            Intent intent = new Intent(this, QuartasGameActivity.class);
            startActivity(intent);
        }
    }
}
