package fr.nathanael2611.openclassrooms.escapegame.code.core.modes;

import fr.nathanael2611.openclassrooms.escapegame.code.core.Game;
import fr.nathanael2611.openclassrooms.escapegame.code.core.exception.GameException;
import fr.nathanael2611.openclassrooms.escapegame.code.core.exception.InputSizeException;
import fr.nathanael2611.openclassrooms.escapegame.code.core.util.AppHelper;
import fr.nathanael2611.openclassrooms.escapegame.code.core.util.IACodeGuesser;

public class DefenderMode extends Mode
{

    private int[] code;
    private boolean isCodeChoosed = false;

    private int[] prevIaCode;

    private IACodeGuesser guesser;

    DefenderMode(Game game)
    {
        super(game);
    }

    @Override
    public String getName()
    {
        return "defender";
    }

    @Override
    public void start()
    {
        LOGGER.info("Démarrage du mode DefenderMode !");
        this.code = new int[this.CODE_SIZE];
        guesser = new IACodeGuesser(this.CODE_SIZE);

        LOGGER.info("Veuillez choisir un code à " + this.CODE_SIZE + " que l'ordinateur devra deviner.");

        LOGGER.info(String.format("L'ordinateur devra proposer des codes à %s chiffres ! Il a %s essais.", this.CODE_SIZE, this.MAX_TRIALS));
    }

    @Override
    public String input(int[] entry) throws GameException
    {
        if(inputArrayType() == Integer.class)
        {
            if(entry.length == this.code.length)
            {
                this.code = entry;
                LOGGER.info("Votre code secret a été définit " + AppHelper.assembleOneByOne(this.code));
                iaInput();
                this.isCodeChoosed = true;
                return null;
            } else {
                throw new InputSizeException("The code entered is not the same size as the generated code.");
            }
        }
        throw new GameException("Invalid input for mode " + getName());
    }

    @Override
    public String input(String[] entry) throws GameException
    {
        if(canInput())
        {
            if(entry.length == CODE_SIZE)
            {
                String asString = AppHelper.assembleOneByOne(entry);
                if(asString.replace("+", "").replace("-", "").replace("=", "").length() == 0)
                {
                    String[] vals = asString.split("");
                    for (int i = 0; i < vals.length; i++) {
                        if(vals[i].equalsIgnoreCase("+"))
                        {
                            guesser.biggerThan(i, this.prevIaCode[i]);
                        } else if(vals[i].equalsIgnoreCase("-"))
                        {
                            guesser.smallerThan(i, this.prevIaCode[i]);
                        } else if(vals[i].equalsIgnoreCase("="))
                        {
                            guesser.equal(i, this.prevIaCode[i]);
                        }
                    }
                    iaInput();
                } else {
                    throw new GameException("The array has to contain only '+' OR '-' OR '='");
                }
            } else {
                throw new InputSizeException("The array entered is not the same size as the generated code.");
            }
        } else {
            throw new GameException("Cannot input now !");
        }
        return null;
    }

    private void iaInput()
    {
        int[] code = guesser.tryPossibilities();
        LOGGER.info("L'ordinateur propose : " + AppHelper.assembleOneByOne(code));
        LOGGER.info("RAPPEL: Votre code   : " + AppHelper.assembleOneByOne(this.code));
        prevIaCode = code;
        if(AppHelper.assembleOneByOne(this.code).equals(AppHelper.assembleOneByOne(code)))
        {
            LOGGER.info("L'ordinateur a deviné votre code secret !");
            LOGGER.info("Félicitations, vous avez gagné !");
            finish(true);
        } else {
            LOGGER.info("Indiquez si, pour chaque chiffre de la combinaison, si la solution est plus petite ou plus grande que la valeur donnée.");
        }
    }

    @Override
    public Class inputArrayType() {
        return !isCodeChoosed ? Integer.class : String.class;
    }
}
