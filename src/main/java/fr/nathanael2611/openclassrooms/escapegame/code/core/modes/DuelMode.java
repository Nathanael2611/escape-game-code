package fr.nathanael2611.openclassrooms.escapegame.code.core.modes;

import fr.nathanael2611.openclassrooms.escapegame.code.core.Game;
import fr.nathanael2611.openclassrooms.escapegame.code.core.modes.exception.GameException;
import fr.nathanael2611.openclassrooms.escapegame.code.core.modes.exception.InputSizeException;
import fr.nathanael2611.openclassrooms.escapegame.code.core.util.AppHelper;
import fr.nathanael2611.openclassrooms.escapegame.code.core.util.IACodeGuesser;

public class DuelMode extends Mode
{

    private int[] iaCode;
    private IACodeGuesser iaGuesser;
    private int[] userCode = null;

    DuelMode(Game game) {
        super(game);
    }

    @Override
    public String getName()
    {
        return "duel";
    }

    @Override
    public void start()
    {
        LOGGER.info("Démarrage du mode Duel !");
        this.userCode = new int[this.CODE_SIZE];
        this.iaGuesser = new IACodeGuesser(this.CODE_SIZE);

        LOGGER.info("Veuillez choisir un code à " + this.CODE_SIZE + " que l'ordinateur devra deviner.");
        LOGGER.info("De son côté, l'ordinateur a lui-aussi généré un code aléatoire que vous devrez deviner.");
        this.iaCode = this.iaGuesser.tryPossibilities();
        if(GAME.getGameConfig().isDebugEnabled()) LOGGER.debug("Le code de l'ordinateur est: " + AppHelper.assembleOneByOne(this.iaCode));
    }

    @Override
    public String input(int[] entry) throws GameException
    {
        if(entry.length == this.CODE_SIZE)
        {
            if(this.userCode == null)
            {
                this.userCode = entry;
                LOGGER.info("Votre code secret a été définit à " + AppHelper.assembleOneByOne(entry));
            } else {
                if(AppHelper.assembleOneByOne(entry).equalsIgnoreCase(AppHelper.assembleOneByOne(this.iaCode)))
                {
                    finish(true);
                }
            }
            iaInput();
        } else throw new InputSizeException("The code entered is not the same size as the generated code.");
        return null;
    }

    @Override
    public String input(String[] entry) throws GameException
    {
        throw new GameException("Invalid input for mode " + getName());
    }

    public void iaInput()
    {
        int[] entry = iaGuesser.tryPossibilities();
        this.LOGGER.info("L'ordinateur propose " + AppHelper.assembleOneByOne(entry));
        if(AppHelper.assembleOneByOne(entry).equalsIgnoreCase(AppHelper.assembleOneByOne(this.userCode)))
        {
            finish(false);
            return;
        }
        for (int i = 0; i < entry.length; i++)
        {
            if(entry[i] != this.userCode[i])
            {
                this.iaGuesser.isNot(i, entry[i]);
            }
        }
    }

    @Override
    public Class inputArrayType()
    {
        return Integer.class;
    }
}
