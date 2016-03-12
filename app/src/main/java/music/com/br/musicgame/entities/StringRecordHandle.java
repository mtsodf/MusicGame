package music.com.br.musicgame.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Mateus on 12/03/2016.
 */
public class StringRecordHandle {
    String records;
    private static final String separator = ";";
    int maxScores = 10;
    public StringRecordHandle(String records ) {
        this.records = records;

    }


    public List<Integer> SplitRecords(){
        List<Integer> scores = new ArrayList<>();

        for(String s : records.split(separator)) {
            scores.add(Integer.parseInt(s));
        }

        Collections.sort(scores,Collections.reverseOrder());

        return scores;
    }


    public String getRecordString() {
        String s = "";
        List<Integer> scores = SplitRecords();
        for(int i = 0; i < maxScores && i < scores.size();i++){
            s+= scores.get(i)+ separator;
        }

        return s;
    }

    public void addRecord(int points) {
        records += points+separator;
        records = getRecordString();
    }
}
