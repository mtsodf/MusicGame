package music.com.br.musicgame.entities;

import music.com.br.musicgame.MainActivity;

import music.com.br.musicgame.R;

/**
 * Created by Mateus on 01/02/2016.
 */
public class QuartasGame extends Game {


    @Override
    protected Note getAnswer() {
        return currentNote.quarta();
    }

    @Override
    protected String generateQuestion() {
        return MainActivity.getContext().getString(R.string.QuartaGameQuestion);
    }
}
