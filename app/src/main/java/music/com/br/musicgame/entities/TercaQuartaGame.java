package music.com.br.musicgame.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mtsodf on 13/02/16.
 */
public class TercaQuartaGame extends ListGame{


    @Override
    protected List<Game> generateGameList() {
        gameList = new ArrayList<>();
        gameList.add(new TercaGame());
        gameList.add(new QuartasGame());
        return gameList;
    }
}
