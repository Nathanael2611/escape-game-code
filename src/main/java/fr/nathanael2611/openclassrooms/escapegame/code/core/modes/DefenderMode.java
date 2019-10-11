package fr.nathanael2611.openclassrooms.escapegame.code.core.modes;

import fr.nathanael2611.openclassrooms.escapegame.code.core.Game;
import fr.nathanael2611.openclassrooms.escapegame.code.core.modes.exception.GameException;
import fr.nathanael2611.openclassrooms.escapegame.code.core.util.AppHelper;

public class DefenderMode extends Mode
{

    private int[] code;

    DefenderMode(Game game) {
        super(game);
    }

    @Override
    public void start() {
        LOGGER.info("Démarrage du mode DefenderMode !");
        this.code = new int[this.CODE_SIZE];

        LOGGER.info("Veuillez choisir un code à " + this.CODE_SIZE + " que l'ordinateur devra deviner.");

        LOGGER.info(String.format("L'ordinateur devra proposer des codes à %s chiffres ! Il a %s essais.", this.CODE_SIZE, this.MAX_TRIALS));
    }

    @Override
    public String input(int[] entry) throws GameException {
        return null;
    }

    private void iaInput()
    {
        LOGGER.info("");
    }
}
