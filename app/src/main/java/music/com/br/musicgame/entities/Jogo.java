package music.com.br.musicgame.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by mtsodf on 01/02/16.
 */
public abstract class Jogo {

    public Jogo() {
        random = new Random();
    }

    Random random;

    public Note currentNote;

    public Note currentAnswer;

    public List<Note> currentAlternatives;

    int answerIndex;

    public String question;

    public void newPhase() {
        currentNote = new Note(random.nextInt(12));

        currentAlternatives = new ArrayList<Note>();

        question = generateQuestion();

        currentAnswer = generateAnswer();

        while (currentAlternatives.size() < 3) {
            int randomInt = random.nextInt(12);
            Note newNote = new Note(randomInt);
            if (randomInt != currentNote.note && randomInt != currentAnswer.note && !currentAlternatives.contains(newNote)) {
                currentAlternatives.add(newNote);
            }

        }

        currentAlternatives.add(random.nextInt(3), currentAnswer);

    }

    protected abstract Note generateAnswer();

    public boolean rightAnswer(Note answer) {
        return answer.note == currentAnswer.note;
    }


    protected abstract String generateQuestion();

}