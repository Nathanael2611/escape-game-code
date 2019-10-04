package fr.nathanael2611.openclassrooms.escapegame.code.core.modes;

import fr.nathanael2611.openclassrooms.escapegame.code.core.Game;

public enum EnumModes
{
    CHALLENGER;
    public Mode create(Game game)
    {
        switch (this)
        {
            case CHALLENGER:
                return new ChallengerMode(game);
        }
        return null;
    }
}
