package music.com.br.musicgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import music.com.br.musicgame.entities.StringRecordHandle;

public class RecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
    }

    @Override
    protected void onStart() {
        super.onStart();

        addRecords();

    }

    public void addRecords(){
        ListView listView = (ListView) findViewById(R.id.listView);


        SharedPreferences sharedPref = this.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        String records = sharedPref.getString(getString(R.string.high_scores_string), "");

        Log.d("RecordActivity", "Record strings " + records);

        List<Integer> scores = new StringRecordHandle(records).SplitRecords();
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_list_item_1, scores);
        listView.setAdapter(adapter);

    }

}
