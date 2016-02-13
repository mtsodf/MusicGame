package music.com.br.musicgame.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by mtsodf on 13/02/16.
 */
public abstract class ListGame extends Game {


    protected  List<Game> getGameList(){
        if(gameList == null){
            gameList = generateGameList();
        }

        return gameList;
    }

    protected abstract List<Game> generateGameList();

    protected List<Game> gameList;

    protected Game currentGame;

    protected Random random = new Random();

    @Override
    protected Note getAnswer() {
        return currentGame.getAnswer();
    }

    @Override
    protected String generateQuestion() {
        currentGame = getGameList().get(random.nextInt(getGameList().size()));

        return currentGame.generateQuestion();
    }
}
