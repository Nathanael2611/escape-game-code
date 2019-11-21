package fr.nathanael2611.openclassrooms.escapegame.code.core;

import fr.nathanael2611.openclassrooms.escapegame.code.core.config.GameConfig;
import fr.nathanael2611.openclassrooms.escapegame.code.core.exception.GameException;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Main class
 */
public class Game
{

    /* variables */
    private File gameDir;
    private GameConfig gameConfig;
    private String gameName;

    /* The LogManager */
    public final Logger LOGGER;

    /* Constructor */
    public Game(File gameDir, Logger logger) throws GameException
    {
        /* define the game working-dir */
        this.gameDir = gameDir;
        /* define the game-name */
        this.gameName = gameName;
        /* define the game-config */
        this.gameConfig = new GameConfig(gameDir);
        /* Read the config */
        try {
            this.gameConfig.read();
        } catch (IOException e) {
            throw new GameException("Impossible de lire la configuration du jeu !");
        }
        this.LOGGER = logger;

    }

    /**
     * Returns the game-config
     */
    public GameConfig getGameConfig()
    {
        return gameConfig;
    }

    /**
     * Returns the game-dir as File object
     */
    public File getGameDir()
    {
        return gameDir;
    }

    public String getGameName() {
        return gameName;
    }

}
