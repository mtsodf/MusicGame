package music.com.br.musicgame.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Mateus on 01/02/2016.
 */
public class NoteGame {

    public NoteGame(){
        random = new Random();
    }

    Random random;

    public Note currentNote;

    public List<Note> currentAlternatives;

    int answerIndex;

    public void newPhase(){
        currentNote = new Note(random.nextInt(12));

        currentAlternatives = new ArrayList<Note>();




        while(currentAlternatives.size() < 3){
            int randomInt = random.nextInt(12);
            if(randomInt!=currentNote.note) {
                Note newNote = new Note(randomInt);
                currentAlternatives.add(newNote);
            }

        }

        currentAlternatives.add(random.nextInt(3),currentNote);

    }
}
