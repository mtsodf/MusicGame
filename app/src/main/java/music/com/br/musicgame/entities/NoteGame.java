package music.com.br.musicgame.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Mateus on 01/02/2016.
 */
public class NoteGame extends Jogo{


    @Override
    protected Note generateAnswer() {
        return currentNote.quarta();
    }

    @Override
    protected String generateQuestion() {
        return "Qual a quarta da nota?";
    }
}
