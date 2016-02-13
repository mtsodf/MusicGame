package music.com.br.musicgame.entities;

/**
 * Created by Mateus on 01/02/2016.
 */
public class QuartasGame extends Game {


    @Override
    protected Note generateAnswer() {
        return currentNote.quarta();
    }

    @Override
    protected String generateQuestion() {
        return "Qual a quarta da nota?";
    }
}
