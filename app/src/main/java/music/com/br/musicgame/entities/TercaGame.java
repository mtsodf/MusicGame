package music.com.br.musicgame.entities;

import music.com.br.musicgame.MainActivity;
import music.com.br.musicgame.R;

/**
 * Created by mtsodf on 13/02/16.
 */
public class TercaGame extends Game {

    @Override
    protected Note generateAnswer() {
        return currentAnswer.terca();
    }

    @Override
    protected String generateQuestion() {
        return MainActivity.getContext().getString(R.string.TercaGameQuestion);
    }
}
