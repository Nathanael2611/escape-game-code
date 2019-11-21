package fr.nathanael2611.openclassrooms.escapegame.code;

import fr.nathanael2611.openclassrooms.escapegame.code.core.Game;
import fr.nathanael2611.openclassrooms.escapegame.code.core.modes.EnumModes;
import fr.nathanael2611.openclassrooms.escapegame.code.core.modes.Mode;
import fr.nathanael2611.openclassrooms.escapegame.code.core.exception.GameException;
import fr.nathanael2611.openclassrooms.escapegame.code.core.util.AppHelper;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Will manage the game launching.
 * It will simply use the core classes for create a basic game.
 */
public class Main
{

    /* Singleton game instance */
    private static Game INSTANCE;

    /**
     * Main launch method
     */
    public static void main(String[] args)
    {
        if(args.length > 0)
        {
            String gameDirPath = args[0];
            File gameDir = new File(gameDirPath);
            if(gameDir.isDirectory())
            {
                try {
                    launch(gameDir);
                } catch (GameException e) {
                    e.printStackTrace();
                }
            } else {
                System.err.println("Erreur d'initialisation ! Le chemin d'accès spécifié n'est pas un dossier !");
            }
        } else {
            System.err.println("Erreur d'initialisation ! Veuillez fournir un chemin d'accès vers le répertoire de votre jeu pour le lancer !");
        }
    }

    /**
     * Simply launch the game in the defined working directory
     */
    private static void launch(File gameDir) throws GameException
    {
        Logger logger = Logger.getLogger("EscapeGameCode");
        DOMConfigurator.configure(new File(gameDir, "log4j.xml").getAbsolutePath());

        INSTANCE = new Game(gameDir, logger);
        new Thread(()-> {
            logger.info("Veuillez choisir un mode !");
            for (EnumModes value : EnumModes.values())
            {
                logger.info(" - " + value.name());
            }
            /* define the mode variable */
            Mode mode = null;
            while (mode == null)
            {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                try {
                    EnumModes modeAsEnum = null;
                    try {
                        modeAsEnum = EnumModes.valueOf(reader.readLine().toUpperCase());
                    } catch (IllegalArgumentException ex)
                    {
                    }
                    if(modeAsEnum == null) throw new IOException();
                    mode = modeAsEnum.create(INSTANCE);
                    if(mode == null) throw new IOException();
                } catch (IOException e) {
                    logger.error("Le mode de jeu entré est invalide!");
                }
            }
            /* Starting the game-mode */
            mode.start();
            /* Retrieve console inputs while mode isn't finished */
            while (!mode.isFinished())
            {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                try {
                    if(mode.inputArrayType() == Integer.class)
                    {
                        int[] code = AppHelper.parseStringToCode(reader.readLine());
                        try {
                            mode.input(code);
                        } catch (GameException e) {
                            e.printStackTrace();
                        }
                    } else {
                        String[] indications = reader.readLine().split("");
                        try {
                            mode.input(indications);
                        } catch (GameException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (NumberFormatException | IOException e) {
                    logger.error("[Error] Veuillez fournir un nombre valide");
                }
            }
            logger.info("Voulez-vous rejouer ?");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                if(reader.readLine().equalsIgnoreCase("oui"))
                {
                    new Thread(()-> {
                        try {
                            launch(gameDir);
                        } catch (GameException e) {
                            e.printStackTrace();
                        }
                    }).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
    }
}
