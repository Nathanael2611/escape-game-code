package fr.nathanael2611.openclassrooms.escapegame.code.core.modes;

import fr.nathanael2611.openclassrooms.escapegame.code.core.Game;

public enum EnumModes
{
    CHALLENGER, DEFENDER, DUEL;
    public Mode create(Game game)
    {
        switch (this)
        {
            case CHALLENGER: return new ChallengerMode(game);
            case DEFENDER: return new DefenderMode(game);
            case DUEL: return new DuelMode(game);
        }
        return null;
    }
}
