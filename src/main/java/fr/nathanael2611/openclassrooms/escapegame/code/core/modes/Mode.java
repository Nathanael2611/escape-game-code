package fr.nathanael2611.openclassrooms.escapegame.code.core.modes;

import fr.nathanael2611.openclassrooms.escapegame.code.core.Game;
import fr.nathanael2611.openclassrooms.escapegame.code.core.config.GameConfig;
import fr.nathanael2611.openclassrooms.escapegame.code.core.exception.GameException;
import org.apache.log4j.Logger;

public abstract class Mode
{

    final Game GAME;
    final Logger LOGGER;

    private boolean isFinished = false;

    final int CODE_SIZE;
    final int MAX_TRIALS;
    int trials;

    Mode(Game game)
    {
        this.GAME = game;
        this.LOGGER = game.LOGGER;
        this.CODE_SIZE = GAME.getGameConfig().get(GameConfig.CODE_SIZE).getAsInt();
        this.MAX_TRIALS = GAME.getGameConfig().get(GameConfig.TRIALS).getAsInt();
        this.trials = GAME.getGameConfig().get(GameConfig.TRIALS).getAsInt();
    }

    public abstract String getName();

    public abstract void start();

    public String finish(boolean win)
    {
        isFinished = true;
        return "Mode " + getName() + " terminé ! " + (win ? "Vous avez gagné !" : "Vous avez perdu !");
    }

    public abstract String input(int[] entry) throws GameException;
    public abstract String input(String[] entry) throws GameException;

    public boolean isFinished()
    {
        return isFinished;
    }

    public boolean canInput()
    {
        return trials > 0;
    }

    public abstract Class inputArrayType();

}