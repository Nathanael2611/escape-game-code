package fr.nathanael2611.openclassrooms.escapegame.code.core.modes;

import fr.nathanael2611.openclassrooms.escapegame.code.core.Game;
import fr.nathanael2611.openclassrooms.escapegame.code.core.modes.exception.GameException;
import fr.nathanael2611.openclassrooms.escapegame.code.core.modes.exception.InputSizeException;
import fr.nathanael2611.openclassrooms.escapegame.code.core.util.AppHelper;

import java.util.Random;

public class ChallengerMode extends Mode
{

    private int[] code;

    ChallengerMode(Game game) {
        super(game);
    }

    @Override
    public String getName()
    {
        return "challenger";
    }

    @Override
    public void start()
    {
        LOGGER.info("Démarrage du mode ChallengerMode !");
        this.code = new int[this.CODE_SIZE];
        AppHelper.fillWithRandom(this.code);
        LOGGER.info("L'intelligence artificielle a choisi un nombre aléatoire ");
        if(GAME.getGameConfig().isDebugEnabled()) LOGGER.debug("Le code de l'ordinateur est: " + AppHelper.assembleOneByOne(this.code));
        LOGGER.info(String.format("Veuillez proposer des codes à %s chiffres ! Vous avez %s essais.", this.CODE_SIZE, this.MAX_TRIALS));
    }

    @Override
    public String input(int[] entry) throws GameException {
        if (canInput())
        {
            if (entry.length == this.code.length)
            {
                trials--;
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < this.code.length; i++)
                    builder.append(this.code[i] == entry[i] ? "=" : (this.code[i] > entry[i] ? "+" : "-"));
                LOGGER.info(builder.toString());
                if(builder.toString().replace("=", "").length() == 0)
                {
                    LOGGER.info(finish(true));
                } else {
                    LOGGER.info(String.format("Raté ! Il te reste %s/%s essais !", this.trials, this.MAX_TRIALS));
                }
                return builder.toString();
            }
            throw new InputSizeException("The code entered is not the same size as the generated code.");
        }
        throw new GameException("Cannot input now !");
    }

    @Override
    public String input(String[] entry) throws GameException {
        int[] newEntry = new int[entry.length];
        for (int i = 0; i < entry.length; i++) {
            newEntry[i] = Integer.parseInt(entry[i]);
        }
        return input(newEntry);
    }

    @Override
    public Class inputArrayType() {
        return Integer.class;
    }
}
