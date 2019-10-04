package fr.nathanael2611.openclassrooms.escapegame.code.core.modes;

import fr.nathanael2611.openclassrooms.escapegame.code.core.Game;
import fr.nathanael2611.openclassrooms.escapegame.code.core.modes.exception.GameException;

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
        for (int i = 0; i < this.CODE_SIZE; i++) {
            this.code[i] = new Random().nextInt(9);
        }
        LOGGER.info("L'intelligence artificielle a choisi un nombre aléatoire ");

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
            throw new GameException("The code entered is not the same size as the generated code.");
        }
        throw new GameException("Cannot input now !");
    }

}
